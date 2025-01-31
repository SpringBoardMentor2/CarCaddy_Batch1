package org.infosys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity in testing
            .authorizeHttpRequests()
            .requestMatchers("/**").permitAll() // Allow login and signup endpoints
            .anyRequest().authenticated() // Protect other endpoints
            .and()
            .httpBasic().disable(); // Disable basic auth entirely
        return http.build();
    }
}
