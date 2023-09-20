package com.example.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The SecurityConfiguration class defines the security configuration for the library application.
 * It specifies the authentication and authorization rules, filters, and session management settings.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    /**
     * Configures a BCryptPasswordEncoder bean for password hashing.
     *
     * @return BCryptPasswordEncoder instance for password encoding.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures a JWTRequestFilter bean for handling JWT authentication.
     *
     * @return JWTRequestFilter instance for processing JWT tokens.
     */
    @Bean
    public JWTRequestFilter authJWTRequestFilter(){
        return new JWTRequestFilter();
    }

    /**
     * Configures the security filter chain for HTTP requests.
     *
     * @param http HttpSecurity object to configure security rules.
     * @return SecurityFilterChain instance specifying the security rules.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/v1/auth/signup","/api/v1/auth/login").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .headers().frameOptions().disable();
        http.addFilterBefore(authJWTRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    /**
     * Configures the authentication manager for handling user authentication.
     *
     * @param authConfig AuthenticationConfiguration object for authentication configuration.
     * @return AuthenticationManager instance for user authentication.
     * @throws Exception If an exception occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
