package org.buddy.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.env.Environment;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
                // corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                // TODO: cambiar a esto y sacar el *
                corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                return corsConfiguration;
            }
        }))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated());

        // TODO: sacar para produccion
        http.csrf(AbstractHttpConfigurer::disable);

        if (!isTestEnvironment()) {
            http.addFilterBefore(new FirebaseFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        return http.build();
    }

    private boolean isTestEnvironment() {
        for (String profile : env.getActiveProfiles()) {
            if ("test".equals(profile)) {
                return true;
            }
        }
        return false;
    }
}