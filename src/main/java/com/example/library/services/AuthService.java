package com.example.library.services;

import com.example.library.models.User;
import com.example.library.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthService {
    Logger logger = Logger.getLogger(AuthService.class.getName());
    private final UserRepository userRepository;

}
