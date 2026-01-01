package com.example.afix.service.user;

import com.example.afix.repository.UserRepository;
import com.example.afix.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User theUser) {
        if (!theUser.getPassword().startsWith("$2a$") &&
                !theUser.getPassword().startsWith("$2b$") &&
                !theUser.getPassword().startsWith("$2y$")) {
            theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        }

        return userRepository.save(theUser);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findById(1)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
