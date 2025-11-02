package com.example.sosy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SosyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SosyApplication.class, args);
    }

    // CORS configuration to allow frontend requests
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*") // For testing, allow all. For production, specify your frontend URL.
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}