package com.example.afix.service.role;

import com.example.afix.model.Role;
import com.example.afix.repository.AnswerRepository;
import com.example.afix.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    public RoleServiceImpl(
            RoleRepository roleRepository
    ){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElseThrow();
    }


}
