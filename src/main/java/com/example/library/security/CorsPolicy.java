package com.example.library.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The CorsPolicy class configures Cross-Origin Resource Sharing (CORS) settings for the library application.
 */
@Configuration
@EnableWebMvc
public class CorsPolicy implements WebMvcConfigurer {

    /**
     * Configures CORS mappings to allow requests from a specified origin, with specified HTTP methods, headers,
     * and credentials support.
     *
     * @param registry The CorsRegistry used to configure CORS settings.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
