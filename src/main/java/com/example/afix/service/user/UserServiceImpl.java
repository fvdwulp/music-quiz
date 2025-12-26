package com.example.afix.service.user;

import com.example.afix.repository.AuthorityRepository;
import com.example.afix.repository.UserRepository;
import com.example.afix.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder; // Spring Security bean

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            AuthorityRepository authorityRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User save(User theUser) {
        if (!theUser.getPassword().startsWith("$2a$") &&
                !theUser.getPassword().startsWith("$2b$") &&
                !theUser.getPassword().startsWith("$2y$")) {
            // Not yet encoded → hash it now
            theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        }

        return userRepository.save(theUser);
    }

    @Transactional
    @Override
    public void deleteByUsername(String username) {
        authorityRepository.deleteByUsername(username);
        userRepository.deleteByUsername(username);
    }
}
