package com.example.library.services;

import com.example.library.models.User;
import com.example.library.security.AuthUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * The ApplicationService class provides common utility methods for accessing information about the current user
 * within the library application.
 */
@Service
public class ApplicationService {

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The User object representing the currently authenticated user.
     */
    public static User currentUser() {
        AuthUserDetails authUserDetails = (AuthUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal(); // Current User
        return authUserDetails.user();
    }
}
