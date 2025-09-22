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
│                       │   ├── UserService.java     # Servicio de usuarios
│                       │   └── JwtService.java      # Servicio para generación/validación de JWT
│                       └── config/                  # Configuraciones
│                           └── SecurityConfig.java  # Configuración de seguridad avanzada (JWT + CORS)
└── frontend/         # Aplicación React
    ├── public/
    └── src/
```

## Backend - Spring Boot

### Seguridad y Autenticación (JWT)
El backend implementa autenticación basada en JWT con configuración de seguridad avanzada.

Componentes clave:
- JwtService: generación, validación, expiración y refresh tokens
- SecurityConfig: HTTP stateless, filtro JwtAuthenticationFilter, CORS, CSRF disabled
- AuthController: registro, login y refresh-token. Devuelve accessToken, refreshToken y detalles de usuario

Endpoints públicos:
- POST /api/auth/register
- POST /api/auth/login
- POST /api/auth/refresh-token
- Swagger: /v3/api-docs/**, /swagger-ui/**, /swagger-ui.html

Resto de endpoints: protegidos con JWT (Authorization: Bearer <token>)

### Flujo de Autenticación
1) Registro
- Request: POST /api/auth/register { username, email, password }
- Acciones: valida datos, hashea contraseña (BCrypt 12), persiste usuario
- Respuesta: 201 Created con { accessToken, refreshToken, user: { id, email, username } }

2) Login
- Request: POST /api/auth/login { email, password }
- Acciones: autentica con AuthenticationManager, genera tokens vía JwtService
- Respuesta: 200 OK con { accessToken, refreshToken, user }

3) Refresh Token
- Request: POST /api/auth/refresh-token { refreshToken }
- Acciones: valida refresh, emite nuevo accessToken
- Respuesta: 200 OK { accessToken }

4) Acceso a endpoints protegidos
- Header: Authorization: Bearer <accessToken>
- El filtro JwtAuthenticationFilter valida firma, expiración y setea el contexto de seguridad

### Buenas prácticas de seguridad
- No almacenar secretos en el código: usar variables de entorno o vault (jwt.secret)
- Usar llaves de al menos 256 bits para HS256 (base64)
- Rotación de claves y expiraciones cortas (access ~15m, refresh ~7d) según necesidades
- HTTP Strict Transport Security (HSTS) y solo HTTPS en producción
- CORS restringido por entorno (dominios permitidos específicos)
- Validación de entrada con Bean Validation y sanitización de datos
- BCrypt con factor de coste >= 12
- Sesiones stateless y CSRF deshabilitado para APIs REST
- Limitar exposición de datos de usuario en respuestas
- Logs seguros: no registrar tokens completos ni contraseñas

### Pruebas con Postman
Colección de pruebas sugerida:
- Auth - Register
  - POST http://localhost:8080/api/auth/register
  - Body (JSON): { "username":"alice", "email":"alice@example.com", "password":"Secreta123!" }
- Auth - Login
  - POST http://localhost:8080/api/auth/login
  - Body (JSON): { "email":"alice@example.com", "password":"Secreta123!" }
  - Tests: guardar accessToken y refreshToken en variables de entorno de Postman
- Auth - Refresh Token
  - POST http://localhost:8080/api/auth/refresh-token
  - Body (JSON): { "refreshToken":"{{refreshToken}}" }
- Users - Protected (ejemplo)
  - GET http://localhost:8080/api/users
  - Header: Authorization: Bearer {{accessToken}}

Sugerencias de scripts Postman:
- Tests de login: pm.environment.set("accessToken", pm.response.json().accessToken)
- Tests de login: pm.environment.set("refreshToken", pm.response.json().refreshToken)

### Cómo proteger endpoints con JWT
- En SecurityConfig, los endpoints no listados como permitAll requieren autenticación
- Añade @PreAuthorize("hasRole('ADMIN')") en métodos que requieran rol
- En controladores, no es necesario extraer manualmente el usuario; usar SecurityContextHolder

### Configuración (application.yml ejemplo)
```
jwt:
  secret: ${JWT_SECRET_BASE64}
  expiration: 900000        # 15 minutos
  refresh-expiration: 604800000  # 7 días
spring:
  security:
    filter:
      dispatcher-types: REQUEST
```

### Dependencias (pom.xml)
- Spring Security
- jjwt-api, jjwt-impl (runtime), jjwt-jackson (runtime)
- spring-boot-starter-validation
- spring-boot-starter-web, spring-boot-starter-data-jpa

## Frontend - React
- Consumir endpoints de autenticación
- Guardar tokens en memoria/secure httpOnly cookie (preferible httpOnly cookies para refresh)
- Enviar Authorization: Bearer accessToken en peticiones protegidas

## Licencia
[Especificar licencia]
