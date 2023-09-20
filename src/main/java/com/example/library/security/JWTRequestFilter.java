package com.example.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * The JWTRequestFilter class is responsible for filtering incoming HTTP requests and authenticating users
 * based on the JWT token present in the request's cookies.
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    Logger logger = Logger.getLogger(JWTRequestFilter.class.getName());
    private AuthUserDetailsService authUserDetailsService;
    private JWTUtils jwtUtils;

    @Autowired
    public void setAuthUserDetailsService(AuthUserDetailsService authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    @Autowired
    public void setJwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Parses the JWT token from the request's cookies.
     *
     * @param request The HttpServletRequest containing the cookies.
     * @return The JWT token if found in the cookies, or null if not found.
     */
    private String parseJWT(HttpServletRequest request) {
        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Check if the cookie is the one containing JWT token
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        logger.info("No JWT cookie found");
        return null;
    }

    /**
     * Filters incoming HTTP requests, validates the JWT token, and sets the user's authentication details if valid.
     *
     * @param request     The HttpServletRequest to filter.
     * @param response    The HttpServletResponse.
     * @param filterChain The FilterChain to continue processing the request.
     * @throws ServletException If an error occurs during servlet processing.
     * @throws IOException      If an I/O error occurs.
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJWT(request);
            if (jwt != null && jwtUtils.validateJWT(jwt)) {
                String username = jwtUtils.getUserNameFromJWT(jwt);
                UserDetails userDetails = this.authUserDetailsService.loadUserByUsername(username); // email address
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (UsernameNotFoundException unfe) {
            logger.info("username not found");
        } catch (Exception e) {
            logger.info("cannot set user authentication token");
        }
        filterChain.doFilter(request, response);
    }}
