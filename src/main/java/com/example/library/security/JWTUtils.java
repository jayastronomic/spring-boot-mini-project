package com.example.library.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The JWTUtils class provides utility methods for handling JSON Web Tokens (JWTs) in the library application.
 */
@Service
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpMS;

    /**
     * Generates a JWT token for the given authenticated user details.
     *
     * @param authUserDetails The authenticated user details for whom the token is generated.
     * @return The generated JWT token.
     */
    public String generateJwtToken(AuthUserDetails authUserDetails){
         return Jwts.builder()
                .setSubject((authUserDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpMS))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }


    /**
     * Retrieves the username from a JWT token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username extracted from the token.
     */
    public String getUserNameFromJWT(String token){
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validates a JWT token for its integrity and expiration.
     *
     * @param token The JWT token to validate.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateJWT(String token){
       try {
           Jwts.parser()
                   .setSigningKey(jwtSecret)
                   .parseClaimsJws(token);
           return true;
       } catch (SecurityException e) {
           logger.log(Level.SEVERE, "Invalid JWT signature: {}", e.getMessage());
       } catch (MalformedJwtException e) {
           logger.log(Level.SEVERE, "Invalid JWT token: {}", e.getMessage());
       } catch (ExpiredJwtException e) {
           logger.log(Level.SEVERE, "JWT token is expired: {}", e.getMessage());
       } catch (UnsupportedJwtException e) {
           logger.log(Level.SEVERE, "JWT token is unsupported: {}", e.getMessage());
       } catch (IllegalArgumentException e) {
           logger.log(Level.SEVERE, "JWT claims string is empty: {}", e.getMessage());
       }
        return false;
    }
}
