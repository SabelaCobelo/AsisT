# AsisT - Sistema de Gestión de Reportes Sociales
Sistema de gestión de reportes sociales desarrollado con React en el frontend y Spring Boot en el backend.

## Estructura del Proyecto
```
AsisT/
├── backend/          # Aplicación Spring Boot
│   └── src/
│       └── main/
│           └── java/
│               └── com/
│                   └── asist/
│                       ├── AsistApplication.java    # Clase principal de Spring Boot
│                       ├── controller/              # Controladores REST
│                       │   ├── ReportController.java # Controlador de Reports
│                       │   ├── UserController.java   # Controlador de Users
│                       │   └── AuthController.java   # Autenticación (registro/login + JWT)
│                       ├── model/                   # Entidades JPA
│                       │   └── Report.java          # Entidad Report
│                       ├── repository/              # Repositorios JPA
│                       │   ├── ReportRepository.java # Repositorio de Report
│                       │   └── UserRepository.java   # Repositorio de User
│                       ├── service/                 # Servicios de negocio
│                       │   └── UserService.java     # Servicio de usuarios
│                       └── config/                  # Configuraciones
│                           └── SecurityConfig.java  # Configuración de seguridad
└── frontend/         # Aplicación React
    ├── public/
    └── src/
```

## Backend - Spring Boot

### Seguridad y Autenticación (JWT)
Esta aplicación implementa endpoints de autenticación básicos con generación de JSON Web Tokens (JWT) usando la librería jjwt.

- Controlador: backend/src/main/java/com/asist/controller/AuthController.java
- Librerías: jjwt (io.jsonwebtoken:jjwt-api/jjwt-impl/jjwt-jackson) y BCrypt para hashing de contraseñas.

#### Endpoints
- POST /api/auth/register
  - Descripción: Registrar un nuevo usuario.
  - Body JSON: { "username": "string", "email": "string", "password": "string" }
  - Lógica: Hashea la contraseña con BCrypt y almacena el usuario.
  - Respuestas: 200 OK (registro exitoso) | 400 Bad Request (validación) | 500 Error.

- POST /api/auth/login
  - Descripción: Autenticar un usuario y devolver un JWT.
  - Body JSON: { "username": "string", "password": "string" }
  - Lógica: Valida credenciales, compara contraseña con BCrypt y genera un JWT HS512 con expiración de 24h.
  - Respuestas: 200 OK { token, tokenType: "Bearer", username, email } | 401 Unauthorized | 500 Error.

#### Flujo de Autenticación
1) Registro
   - Cliente envía POST /api/auth/register con username, email, password.
   - Backend hashea password con BCrypt y registra el usuario.
2) Login
   - Cliente envía POST /api/auth/login con username y password.
   - Backend valida credenciales y responde con un JWT Bearer.
3) Acceso a endpoints protegidos
   - El cliente incluye el header Authorization: Bearer <JWT> en cada petición.
   - El backend valida el token (firma y expiración) antes de procesar.

Nota: En esta versión de ejemplo, AuthController usa un almacenamiento en memoria para usuarios y un SecretKey generado en tiempo de ejecución (Keys.secretKeyFor). En producción, usa una base de datos para usuarios y configura la clave secreta vía propiedades/entorno.

#### Configuración recomendada de jjwt (pom.xml)
Dependencias típicas:
- io.jsonwebtoken:jjwt-api
- io.jsonwebtoken:jjwt-impl (scope runtime)
- io.jsonwebtoken:jjwt-jackson (scope runtime)

#### Headers de ejemplo
- Autenticación: Authorization: Bearer <token>
- Content-Type: application/json

### Entidades
#### Report Entity
La entidad Report representa un reporte social en el sistema.
Ubicación: `backend/src/main/java/com/asist/model/Report.java`
Campos:
- id (Long) - Identificador único, generado automáticamente
- title (String) - Título del reporte (obligatorio)
- description (String) - Descripción detallada del reporte
- location (String) - Ubicación donde ocurrió el incidente (obligatorio)
- date (LocalDateTime) - Fecha y hora del reporte (obligatorio)
- userId (Long) - ID del usuario que creó el reporte (obligatorio)

### Repositorios
... (contenido existente) ...

## API REST - Resumen de Endpoints
### Usuarios
- GET /api/users — Obtener todos los usuarios
- GET /api/users/{id} — Obtener usuario por ID
- POST /api/users — Registrar nuevo usuario
- PUT /api/users/{id} — Actualizar usuario
- DELETE /api/users/{id} — Eliminar usuario

### Reportes
- GET /api/reports — Obtener todos los reportes
- GET /api/reports/{id} — Obtener reporte por ID
- POST /api/reports — Crear nuevo reporte
- PUT /api/reports/{id} — Actualizar reporte
- DELETE /api/reports/{id} — Eliminar reporte

## Frontend - React
Aplicación React para la interfaz de usuario del sistema de gestión de reportes.

## Tecnologías
### Backend
- Java, Spring Boot, Spring Security, Spring Data JPA, BCrypt, jjwt
### Frontend
- React, CSS, HTML, JavaScript

## Instalación y Configuración
### Requisitos Previos
- Java 17 o superior
- Node.js y npm
- Base de datos (H2, MySQL, PostgreSQL, etc.)

### Backend
```
cd backend
./mvnw spring-boot:run
```

### Frontend
```
cd frontend
npm install
npm start
```

## Contribución
Este proyecto es parte de un Trabajo de Fin de Carrera (TFC) centrado en la gestión de reportes sociales.

## Licencia
[Especificar licencia según corresponda]
