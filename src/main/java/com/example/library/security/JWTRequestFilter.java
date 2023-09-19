package com.example.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Component
public class JWTRequestFilter {
    Logger logger = Logger.getLogger(JWTRequestFilter.class.getName());
    private final AuthUserDetailsService authUserDetailsService;
    private final JWTUtils jwtUtils;

    @Autowired
    public JWTRequestFilter(AuthUserDetailsService authUserDetailsService, JWTUtils jwtUtils) {
        this.authUserDetailsService = authUserDetailsService;
        this.jwtUtils = jwtUtils;
    }

    private String parseJWT(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasLength(headerAuth) && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7);
        }

        logger.info("No header");
        return null;
    }
}
