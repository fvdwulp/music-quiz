package com.example.afix.service.authority;

import com.example.afix.repository.AuthorityRepository;
import com.example.afix.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(
            AuthorityRepository authorityRepository
    ) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Authority findByUsername(String userName) {
        return authorityRepository.findByUsername(userName);
    }

    @Override
    public void deleteByUsername(String username) {
        authorityRepository.deleteByUsername(username);
    }
}
