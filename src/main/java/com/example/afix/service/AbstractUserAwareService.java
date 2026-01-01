package com.example.afix.service;

import com.example.afix.model.User;
import com.example.afix.service.user.UserService;

public abstract class AbstractUserAwareService {

    protected final UserService userService;

    protected AbstractUserAwareService(
            UserService userService
    ){
        this.userService = userService;
    }

    protected User getCurrentUser() {
        return userService.getCurrentUser();
    }

}
