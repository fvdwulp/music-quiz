package com.example.music_quiz.service;

import com.example.music_quiz.domain.User;
import com.example.music_quiz.service.user.UserService;

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
