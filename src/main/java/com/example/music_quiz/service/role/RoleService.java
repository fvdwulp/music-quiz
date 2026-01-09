package com.example.music_quiz.service.role;

import com.example.music_quiz.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(Integer id);
}
