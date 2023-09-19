package com.example.library.security;

import org.springframework.security.core.userdetails.UserDetails;

public record AuthUserDetails implements UserDetails(User user) {
}