package com.example.afix.service.user;

import com.example.afix.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findByUsername(String userName);
    User save(User theUser);
    void deleteByUsername(String username);

}
