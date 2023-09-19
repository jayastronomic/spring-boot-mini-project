package com.example.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public AuthUserDetailsService(UserService userService){
        this.userService = userService;
    }
}
