package com.example.library.services;

import com.example.library.models.User;
import com.example.library.repositories.UserRepository;
import com.example.library.security.AuthUserDetails;
import com.example.library.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class AuthService {
    Logger logger = Logger.getLogger(AuthService.class.getName());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils, @Lazy AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> createIfNotExists(@Valid User payload, HttpServletResponse response){
        boolean exists = userRepository.existsByEmail(payload.getEmail());
        if(!exists){
            payload.setPassword(passwordEncoder.encode(payload.getPassword()));
            User newUser = userRepository.save(payload);

            // Generate a JWT token
            String jwtToken = jwtUtils.generateJwtToken(new AuthUserDetails(newUser));

//             Create an HttpOnly cookie with the JWT token
            Cookie cookie = new Cookie("jwtToken", jwtToken);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600); // Set the cookie expiration time in seconds (e.g., 1 hour)
            response.addCookie(cookie);


            // Create a response body
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "User created successfully");
            responseBody.put("user", newUser);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseBody);

        }
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "User already exists with email: " + payload.getEmail());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errors);
    }
}
