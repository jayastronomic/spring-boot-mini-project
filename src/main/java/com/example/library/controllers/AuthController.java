package com.example.library.controllers;

import com.example.library.models.User;
import com.example.library.services.AuthService;
import com.example.library.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * The AuthController class provides authentication-related endpoints for user registration and login
 * in the library application.
 */
@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {
    private final AuthService authService;

    @Autowired
    public  AuthController(AuthService authService){
        this.authService = authService;
    }

    /**
     * Handles user registration by creating a new user account if one with the same email does not already exist.
     *
     * @param payload  The user data to create.
     * @param response The HttpServletResponse to set an HttpOnly cookie with the JWT token.
     * @return ResponseEntity containing a success message and user details if created successfully,
     * or an error message if the user already exists.
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<APIResponse> createAccount(@Valid @RequestBody User payload, HttpServletResponse response){
        return authService.createIfNotExists(payload, response);
    }

    /**
     * Handles user login by validating the provided username and password.
     *
     * @param payload  The user data containing email and password for login.
     * @param response The HttpServletResponse to set an HttpOnly cookie with the JWT token.
     * @return ResponseEntity containing a success message and user details if logged in successfully,
     * or an error message if the login fails.
     */
    @PostMapping(path = "/login")
    public ResponseEntity<APIResponse> login(@RequestBody User payload, HttpServletResponse response) {
        return authService.login(payload, response);
    }
}
