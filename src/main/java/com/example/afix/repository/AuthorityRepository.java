package com.example.afix.repository;

import com.example.afix.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Authority findByUsername(String username);
    void deleteByUsername(String username);
}
