package com.example.afix.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("activePage")
    public String getActivePage(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("username")
    public String addUsernameToModel() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return auth.getName();
        }
        return null;
    }

    @ModelAttribute("authority")
    public String addAuthorityToModel() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getAuthorities().iterator().next().toString();
            switch (username) {
                case "ROLE_ADMIN":
                    return "Admin";
                case "ROLE_USER":
                    return "User";
            }
        }
        return null;
    }

}
