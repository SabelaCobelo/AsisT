package com.asist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean para el encoder de contraseñas usando BCrypt
     * BCrypt es un algoritmo de hashing seguro con salt automático
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuración básica de seguridad
     * Esta es una configuración inicial que puede ser extendida según las necesidades
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitar CSRF para APIs REST (puede habilitarse si es necesario)
            .csrf(csrf -> csrf.disable())
            
            // Configuración de autorización de requests
            .authorizeHttpRequests(authz -> authz
                // Permitir acceso público a endpoints de registro y login
                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                
                // Permitir acceso a documentación de API (Swagger/OpenAPI)
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                
                // Permitir acceso a recursos estáticos
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                
                // Todos los demás endpoints requieren autenticación
                .anyRequest().authenticated()
            )
            
            // Configuración de sesión - STATELESS para APIs REST
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // Configuración de login básico HTTP (puede cambiarse por JWT)
            .httpBasic(httpBasic -> {});
            
        return http.build();
    }

    /**
     * Configuración adicional de CORS si es necesaria
     * Descomentar y configurar según las necesidades del frontend
     */
    /*
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    */
}
