package com.example.music_quiz.service.user;

import com.example.music_quiz.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    User newUser();
    User findById(Integer id);
    Optional<User> findByUsername(String username);
    User save(User theUser);
    User getCurrentUser();
    void deleteById(Integer id);

}
