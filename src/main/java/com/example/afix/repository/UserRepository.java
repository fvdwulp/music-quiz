package com.example.afix.repository;

import com.example.afix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    void deleteByUsername(String username);
}
