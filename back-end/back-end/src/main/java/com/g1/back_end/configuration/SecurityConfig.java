package com.g1.back_end.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Value("${allowed.origin.url}")
    private String allowedOriginUrl;

    @Value("${allowed.origin.url.local}")
    private String allowedOriginUrlLocal;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(allowedOriginUrl, allowedOriginUrlLocal)); // Define as URLs permitidas
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Define os métodos permitidos
        config.setAllowedHeaders(Arrays.asList("*")); // Permite todos os cabeçalhos
        config.setAllowCredentials(true); // Permite cookies/sessões de cliente

        source.registerCorsConfiguration("/**", config); // Aplica a configuração para todos os endpoints
        return new CorsFilter(source); // Retorna a instância correta do CorsFilter do Spring
    }
}
