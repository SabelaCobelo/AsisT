package com.asist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot AsisT.
 * 
 * Esta aplicación proporciona la API REST para el sistema AsisT,
 * incluyendo funcionalidades para la gestión de reportes, usuarios
 * y autenticación.
 * 
 * @author AsisT Team
 * @version 1.0
 * @since 2025-09-22
 */
@SpringBootApplication
public class AsistApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     * 
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(AsistApplication.class, args);
        System.out.println("\n==================================");
        System.out.println("   AsisT Backend API Started");
        System.out.println("   http://localhost:8080");
        System.out.println("==================================");
    }
}
