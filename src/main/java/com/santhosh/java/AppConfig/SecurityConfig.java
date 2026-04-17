package com.santhosh.java.AppConfig;

import com.santhosh.java.filter.JwtFilter;
import com.santhosh.java.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    JwtService jwtService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/list","/swagger-ui/**","/swagger-resources/**","/v3/api-docs/**","/webjars/**","/v1/api","/api/users/authentication").permitAll()
                        .anyRequest().authenticated())
                // OAuth2 login
//                .oauth2Login(oauth -> oauth
//                        .successHandler((request, response, authentication) -> {
//
//                            String username = authentication.getName();
//
//                            // 🔥 Generate your JWT here
//                            String token = jwtService.genearateToken(username);
//
//                            response.setContentType("application/json");
//                            response.getWriter().write(
//                                    "{\"token\": \"" + token + "\"}"
//                            );
//                        })
//                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authmanager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager() ;
    }


}
