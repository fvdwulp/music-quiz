package com.example.afix.service.role;

import com.example.afix.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(Integer id);
}
