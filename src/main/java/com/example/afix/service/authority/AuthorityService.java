package com.example.afix.service.authority;

import com.example.afix.model.Authority;
import com.example.afix.model.User;

public interface AuthorityService {

    Authority findByUsername(String userName);
//    User save(User theUser);
    void deleteByUsername(String username);

}
