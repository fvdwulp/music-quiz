package com.example.afix.service.user;

import com.example.afix.audit.Audit;
import com.example.afix.audit.AuditAction;
import com.example.afix.repository.UserRepository;
import com.example.afix.model.User;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@PreAuthorize("hasRole('ADMIN')")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User newUser() {
        return new User();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Audit(action = AuditAction.USER_UPDATED, entity = "User")
    public User save(User theUser) {
        if (!theUser.getPassword().startsWith("$2a$") &&
                !theUser.getPassword().startsWith("$2b$") &&
                !theUser.getPassword().startsWith("$2y$")) {
            theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        }

        return userRepository.save(theUser);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()
                || auth.getPrincipal().equals("anonymousUser")) {
            throw new IllegalStateException("No authenticated user");
        }

        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + auth.getName())
                );
    }

    @Transactional
    @Override
    @Audit(action = AuditAction.USER_DELETED, entity = "User")
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
