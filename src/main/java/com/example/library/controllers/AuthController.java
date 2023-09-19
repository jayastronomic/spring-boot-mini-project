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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {
    private final AuthService authService;

    @Autowired
    public  AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<APIResponse> createAccount(@Valid @RequestBody User payload, HttpServletResponse response){
        return authService.createIfNotExists(payload, response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<APIResponse> login(@RequestBody User payload) {
        String jwtToken = authService.login(payload).orElse(null);
        if (jwtToken != null) {
            return ResponseEntity.ok(new APIResponse(jwtToken));
        } else {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "invalid email/password");

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new APIResponse(responseBody));
        }
    }
}
