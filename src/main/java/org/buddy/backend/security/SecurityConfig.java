package org.buddy.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/**").permitAll()
            .anyRequest().authenticated()
        );

        // TODO: sacar para produccion
        http.csrf(AbstractHttpConfigurer::disable);

        if (!isTestEnvironment()) {
            http.addFilterBefore(new FirebaseFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        return http.build();
    }

    private boolean isTestEnvironment() {
        for (String profile : env.getActiveProfiles()) {
            // TODO: sacar dev 
            if ("dev".equals(profile) || "test".equals(profile)) {
                return true;
            }
        }
        return false;
    }
}