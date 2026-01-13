package com.example.scan3d.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomeSecurityConfig {

        private final JwtConverter jwtConverter;
        private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
        private final CustomAccessDeniedHandler customAccessDeniedHandler;

        public CustomeSecurityConfig(JwtConverter jwtConverter,
                        CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                        CustomAccessDeniedHandler customAccessDeniedHandler) {
                this.jwtConverter = jwtConverter;
                this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
                this.customAccessDeniedHandler = customAccessDeniedHandler;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http)
                        throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/test/**").permitAll()
                                                .anyRequest().authenticated())
                                .oauth2ResourceServer(oauth -> oauth
                                                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter))
                                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                                .accessDeniedHandler(customAccessDeniedHandler));
                                

                return http.build();
        }
}
