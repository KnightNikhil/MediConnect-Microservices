package com.mediconnect.service.common_entities.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    /*
        Request
            ↓
        JwtAuthenticationFilter   ← STILL RUNS ❌
            ↓
        SecurityContextHolder
            ↓
        Authorization (permitAll)
            ↓
        Controller
     */

    @Bean
    public SecurityFilterChain webSecurityConfiguration(HttpSecurity httpSecurity) throws Exception {

        /*
            •	Inside SecurityFilterChain
            •	After all filters have already run
            •	Only controls authorization, not filtering

        Meaning
            •	The request is allowed
            •	BUT filters still execute
            •	Your JWT filter still runs
            •	Your JWT filter may reject the request
         */
        httpSecurity.authorizeHttpRequests(
                request -> {
                    request.requestMatchers("/register/**").permitAll().
                            requestMatchers("/login/**").permitAll().
                            requestMatchers("/refreshToken/**").permitAll().
                            anyRequest().permitAll();

                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable
        );

        return httpSecurity.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
