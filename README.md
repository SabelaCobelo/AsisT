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
│                       │   └── UserController.java   # Controlador de Users
│                       ├── model/                   # Entidades JPA
│                       │   └── Report.java         # Entidad Report
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

### Entidades

#### Report Entity

La entidad Report representa un reporte social en el sistema.

**Ubicación:** `backend/src/main/java/com/asist/model/Report.java`

**Campos:**
- `id` (Long) - Identificador único, generado automáticamente
- `title` (String) - Título del reporte (obligatorio)
- `description` (String) - Descripción detallada del reporte
- `location` (String) - Ubicación donde ocurrió el incidente (obligatorio)
- `date` (LocalDateTime) - Fecha y hora del reporte (obligatorio)
- `userId` (Long) - ID del usuario que creó el reporte (obligatorio)

**Características:**
- Anotada con `@Entity` para mapeo JPA
- Tabla de base de datos: `reports`
- Incluye constructores, getters, setters y método `toString()`
- Validaciones de campos obligatorios con `@Column(nullable = false)`

### Repositorios

#### ReportRepository

Interfaz de repositorio para la entidad Report que extiende `JpaRepository<Report, Long>`.

**Ubicación:** `backend/src/main/java/com/asist/repository/ReportRepository.java`

**Métodos personalizados:**
- `findByUserId(Long userId)` - Buscar reportes por ID de usuario
- `findByLocation(String location)` - Buscar reportes por ubicación
- `findByTitleContainingIgnoreCase(String title)` - Buscar reportes por título (búsqueda parcial, insensible a mayúsculas)
- `findByDateBetween(LocalDateTime startDate, LocalDateTime endDate)` - Buscar reportes en un rango de fechas
- `findByUserIdAndDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate)` - Buscar reportes por usuario y rango de fechas
- `findByLocationAndDateBetween(String location, LocalDateTime startDate, LocalDateTime endDate)` - Buscar reportes por ubicación y rango de fechas

**Características:**
- Extiende JpaRepository proporcionando operaciones CRUD básicas
- Anotada con `@Repository`
- Métodos de consulta automáticos basados en convenciones de Spring Data JPA
- Soporte para consultas complejas con múltiples criterios

### Servicios

#### UserService

Servicio de negocio para la gestión de usuarios con funcionalidades de seguridad.

**Ubicación:** `backend/src/main/java/com/asist/service/UserService.java`

**Métodos principales:**
- `registerUser(User user)` - Registra un nuevo usuario con contraseña hasheada
- `findByEmail(String email)` - Busca usuario por email
- `validateUserCredentials(String email, String password)` - Valida credenciales de login
- `changePassword(Long userId, String oldPassword, String newPassword)` - Cambio seguro de contraseña
- `existsByEmail(String email)` - Verifica existencia de usuario por email

**Características:**
- Integración con BCryptPasswordEncoder para hashing seguro de contraseñas
- Validación de credenciales con comparación de hash
- Métodos para gestión completa del ciclo de vida del usuario
- Inyección de dependencias con `@Autowired`

### Controladores REST

#### UserController

Controlador REST para la gestión de usuarios del sistema.

**Ubicación:** `backend/src/main/java/com/asist/controller/UserController.java`

**Base URL:** `/api/users`

**Endpoints disponibles:**

##### GET /api/users
- **Descripción:** Obtener todos los usuarios registrados
- **Método HTTP:** GET
- **Respuesta exitosa:** 200 OK con lista de usuarios
- **Respuesta vacía:** 204 No Content si no hay usuarios
- **Respuesta de error:** 500 Internal Server Error

##### GET /api/users/{id}
- **Descripción:** Obtener un usuario específico por su ID
- **Método HTTP:** GET
- **Parámetros:**
  - `id` (Long) - ID del usuario a buscar
- **Respuesta exitosa:** 200 OK con datos del usuario
- **Respuesta no encontrado:** 404 Not Found
- **Respuesta de error:** 500 Internal Server Error

##### POST /api/users
- **Descripción:** Registrar un nuevo usuario en el sistema
- **Método HTTP:** POST
- **Body:** Objeto User en formato JSON
- **Respuesta exitosa:** 201 Created con el usuario creado
- **Respuesta de error:** 500 Internal Server Error

##### PUT /api/users/{id}
- **Descripción:** Actualizar un usuario existente
- **Método HTTP:** PUT
- **Parámetros:**
  - `id` (Long) - ID del usuario a actualizar
- **Body:** Objeto User con los nuevos datos en formato JSON
- **Respuesta exitosa:** 200 OK con el usuario actualizado
- **Respuesta no encontrado:** 404 Not Found
- **Respuesta de error:** 500 Internal Server Error

##### DELETE /api/users/{id}
- **Descripción:** Eliminar un usuario del sistema
- **Método HTTP:** DELETE
- **Parámetros:**
  - `id` (Long) - ID del usuario a eliminar
- **Respuesta exitosa:** 204 No Content
- **Respuesta no encontrado:** 404 Not Found
- **Respuesta de error:** 500 Internal Server Error

**Características:**
- Anotado con `@RestController` para crear endpoints REST
- Usa `@RequestMapping("/api/users")` para la ruta base
- Incluye `@CrossOrigin(origins = "*", maxAge = 3600)` para soporte CORS
- Utiliza UserRepository para operaciones de persistencia
- Implementa manejo de errores y respuestas HTTP apropiadas
- Retorna ResponseEntity para control completo de la respuesta HTTP
- Documentación completa en JavaDoc

#### ReportController

Controlador REST para la gestión de reportes sociales.

**Ubicación:** `backend/src/main/java/com/asist/controller/ReportController.java`

**Base URL:** `/api/reports`

**Características:**
- Controlador REST para operaciones CRUD sobre reportes
- Implementa endpoints para crear, leer, actualizar y eliminar reportes
- Soporte para filtros y búsquedas avanzadas
- Manejo de errores y validaciones

## Seguridad y Autenticación

### Configuración de Seguridad

El sistema implementa Spring Security con las siguientes características:

**Ubicación:** `backend/src/main/java/com/asist/config/SecurityConfig.java`

#### BCryptPasswordEncoder

- **Algoritmo de hashing:** BCrypt con salt automático
- **Bean configurado:** `@Bean BCryptPasswordEncoder passwordEncoder()`
- **Fortaleza:** Algoritmo adaptativo que se vuelve más lento con el tiempo
- **Uso:** Hashing de contraseñas en registro y validación en login

#### Configuración de Endpoints

**Endpoints públicos (sin autenticación):**
- `/api/auth/register` - Registro de nuevos usuarios
- `/api/auth/login` - Login de usuarios
- `/v3/api-docs/**` - Documentación de API (Swagger/OpenAPI)
- `/swagger-ui/**` - Interfaz de Swagger
- `/css/**`, `/js/**`, `/images/**` - Recursos estáticos

**Endpoints protegidos:**
- Todos los demás endpoints requieren autenticación

#### Características de Seguridad

- **Gestión de sesiones:** STATELESS (sin estado) para APIs REST
- **CSRF:** Deshabilitado para APIs REST
- **Autenticación:** HTTP Basic (configurable para JWT)
- **CORS:** Configuración disponible para desarrollo frontend

### Flujo de Autenticación

1. **Registro de Usuario:**
   - El usuario envía datos al endpoint `/api/auth/register`
   - La contraseña se hashea con BCrypt antes del almacenamiento
   - Se almacena el usuario con contraseña segura

2. **Login de Usuario:**
   - El usuario envía credenciales al endpoint `/api/auth/login`
   - Se valida el email y se compara la contraseña con BCrypt
   - Se retorna confirmación de autenticación

3. **Cambio de Contraseña:**
   - Se valida la contraseña actual con BCrypt
   - Se hashea la nueva contraseña antes del almacenamiento
   - Se actualiza de forma segura

### Mejores Prácticas Implementadas

- **Hashing seguro:** BCrypt con salt automático
- **Validación de contraseñas:** Comparación de hash sin almacenar texto plano
- **Configuración modular:** Separación de responsabilidades en SecurityConfig
- **Endpoints protegidos:** Control granular de acceso
- **Preparado para escalabilidad:** Base para implementar JWT tokens

## API REST - Resumen de Endpoints

### Usuarios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/users` | Obtener todos los usuarios |
| GET | `/api/users/{id}` | Obtener usuario por ID |
| POST | `/api/users` | Registrar nuevo usuario |
| PUT | `/api/users/{id}` | Actualizar usuario |
| DELETE | `/api/users/{id}` | Eliminar usuario |

### Reportes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/reports` | Obtener todos los reportes |
| GET | `/api/reports/{id}` | Obtener reporte por ID |
| POST | `/api/reports` | Crear nuevo reporte |
| PUT | `/api/reports/{id}` | Actualizar reporte |
| DELETE | `/api/reports/{id}` | Eliminar reporte |

## Frontend - React

Aplicación React para la interfaz de usuario del sistema de gestión de reportes.

## Tecnologías

### Backend

- **Java** - Lenguaje de programación principal
- **Spring Boot** - Framework de aplicación
- **Spring Security** - Framework de seguridad y autenticación
- **Spring Data JPA** - Acceso a datos y persistencia
- **BCrypt** - Algoritmo de hashing para contraseñas
- **Jakarta Persistence API (JPA)** - Especificación de mapeo objeto-relacional

### Frontend

- **React** - Biblioteca de JavaScript para UI
- **CSS** - Estilos
- **HTML** - Estructura
- **JavaScript** - Lógica de aplicación

## Instalación y Configuración

### Requisitos Previos

- Java 17 o superior
- Node.js y npm
- Base de datos (H2, MySQL, PostgreSQL, etc.)

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm start
```

## Contribución

Este proyecto es parte de un Trabajo de Fin de Carrera (TFC) centrado en la gestión de reportes sociales.

## Licencia

[Especificar licencia según corresponda]
