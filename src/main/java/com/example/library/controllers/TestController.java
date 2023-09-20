package com.example.library.controllers;

import com.example.library.utils.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * The TestController class provides endpoints for testing and demonstration purposes in the library application.
 */
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    /**
     * Handles a GET request to the "/api/v1/test" endpoint.
     *
     * @return ResponseEntity containing a JSON response indicating the status as "active."
     */
    @GetMapping
    public ResponseEntity<APIResponse> test(){
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "active");
        return ResponseEntity.ok(new APIResponse(response));
    }
}
