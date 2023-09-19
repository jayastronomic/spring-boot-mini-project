package com.example.library.services;

import com.example.library.models.User;
import com.example.library.security.AuthUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    public static User currentUser() {
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal(); // Current User
        return authUserDetails.user();
    }
}
