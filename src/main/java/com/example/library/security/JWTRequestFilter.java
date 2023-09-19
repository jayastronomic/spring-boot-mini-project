package com.example.library.security;

import java.util.logging.Logger;

public class JWTRequestFilter {
    Logger logger = Logger.getLogger(JWTRequestFilter.class.getName());
    private final AuthUserDetailsService authUserDetailsService;
    private final JWTUtils jwtUtils;
}
