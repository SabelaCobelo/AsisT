# Backend - AsisT

## Descripción

Este módulo contiene la API REST del proyecto AsisT desarrollada con **Spring Boot**.

## Tecnologías Utilizadas

- Spring Boot 3.x
- Java 17+
- Spring Data JPA
- Spring Security
- MySQL/PostgreSQL
- Maven
- Spring Boot Starter Web
- Spring Boot Starter Validation

## Estructura del Proyecto

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/asist/
│   │   │       ├── controller/  # Controladores REST
│   │   │       ├── service/     # Lógica de negocio
│   │   │       ├── repository/  # Acceso a datos
│   │   │       ├── model/       # Entidades y DTOs
│   │   │       ├── config/      # Configuraciones
│   │   │       └── exception/   # Manejo de excepciones
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/               # Tests unitarios e integración
├── pom.xml
└── README.md
```

## Instalación y Ejecución

### Prerrequisitos

- Java 17 o superior
- Maven 3.8+
- Base de datos (MySQL/PostgreSQL)

### Pasos

```bash
# Clonar el repositorio y navegar al backend
cd backend

# Instalar dependencias
mvn clean install

# Configurar base de datos en application.properties

# Ejecutar la aplicación
mvn spring-boot:run

# La API estará disponible en http://localhost:8080
```

## Entidades del Modelo

### 👤 User - Entidad de Usuario

La entidad **User** representa a los usuarios del sistema AsisT.

**Ubicación:** `src/main/java/com/asist/model/User.java`

#### Campos:
- **id** (Long): Identificador único del usuario (clave primaria, autoincremental)
- **username** (String): Nombre de usuario único y no nulo
- **password** (String): Contraseña del usuario (no nulo)
- **email** (String): Correo electrónico único y no nulo
- **roles** (Set<String>): Conjunto de roles asignados al usuario

#### Características:
- **@Entity**: Marca la clase como entidad JPA
- **@Table(name = "users")**: Especifica el nombre de la tabla en la base de datos
- **@Id** y **@GeneratedValue**: Clave primaria autoincremental
- **@Column**: Validaciones de unicidad y nulabilidad
- **@ElementCollection**: Manejo de la colección de roles
- Constructores por defecto y con parámetros
- Getters y setters completos
- Método toString() para debugging

### 📊 Report - Entidad de Reporte

La entidad **Report** representa los reportes de incidencias en el sistema.

**Ubicación:** `src/main/java/com/asist/model/Report.java`

## Repositorios

### 👤 UserRepository - Repositorio de Usuarios

Interface que extiende **JpaRepository** para operaciones CRUD sobre usuarios.

**Ubicación:** `src/main/java/com/asist/repository/UserRepository.java`

#### Métodos disponibles:

##### Métodos básicos heredados de JpaRepository:
- `save(User user)` - Guardar/actualizar usuario
- `findById(Long id)` - Buscar usuario por ID
- `findAll()` - Obtener todos los usuarios
- `deleteById(Long id)` - Eliminar usuario por ID
- `count()` - Contar usuarios
- Y más métodos estándar...

##### Métodos personalizados:
- **findByUsername(String username)**: Busca un usuario por nombre de usuario
  - Retorna: `Optional<User>`
- **findByEmail(String email)**: Busca un usuario por email
  - Retorna: `Optional<User>`
- **existsByUsername(String username)**: Verifica si existe un usuario con el username dado
  - Retorna: `boolean`
- **existsByEmail(String email)**: Verifica si existe un usuario con el email dado
  - Retorna: `boolean`

#### Características técnicas:
- **@Repository**: Anotación que marca la interface como repositorio Spring
- **Extends JpaRepository<User, Long>**: Hereda funcionalidades CRUD básicas
- **Query Methods**: Spring Data JPA genera automáticamente las consultas basándose en los nombres de los métodos
- **Documentación completa**: JavaDoc en todos los métodos personalizados

### 📊 ReportRepository - Repositorio de Reportes

Interface para operaciones CRUD sobre reportes.

**Ubicación:** `src/main/java/com/asist/repository/ReportRepository.java`

## API Endpoints

### 📋 ReportController - Gestión de Reportes

El controlador ReportController proporciona endpoints REST para realizar operaciones CRUD sobre los reportes del sistema.

**Base URL:** `/api/reports`

#### 📖 Obtener Todos los Reportes
```http
GET /api/reports
```

**Descripción:** Obtiene una lista de todos los reportes en el sistema.

**Respuestas:**
- `200 OK` - Lista de reportes obtenida exitosamente
- `204 NO_CONTENT` - No hay reportes en el sistema
- `500 INTERNAL_SERVER_ERROR` - Error interno del servidor

**Ejemplo de respuesta:**
```json
[
  {
    "id": 1,
    "title": "Reporte de incidente",
    "description": "Descripción detallada del incidente",
    "location": "Calle Principal 123",
    "date": "2025-09-22T10:30:00",
    "userId": 1
  }
]
```

#### 🔍 Obtener Reporte por ID
```http
GET /api/reports/{id}
```

**Parámetros:**
- `id` (Long) - ID único del reporte

**Descripción:** Obtiene un reporte específico por su identificador único.

**Respuestas:**
- `200 OK` - Reporte encontrado exitosamente
- `404 NOT_FOUND` - Reporte no encontrado
- `500 INTERNAL_SERVER_ERROR` - Error interno del servidor

**Ejemplo de respuesta:**
```json
{
  "id": 1,
  "title": "Reporte de incidente",
  "description": "Descripción detallada del incidente",
  "location": "Calle Principal 123",
  "date": "2025-09-22T10:30:00",
  "userId": 1
}
```

#### ➕ Crear Nuevo Reporte
```http
POST /api/reports
```

**Descripción:** Crea un nuevo reporte en el sistema.

**Request Body:**
```json
{
  "title": "Título del reporte",
  "description": "Descripción del reporte (opcional)",
  "location": "Ubicación del incidente",
  "date": "2025-09-22T10:30:00",
  "userId": 1
}
```

**Campos obligatorios:**
- `title` - Título del reporte
- `location` - Ubicación del incidente
- `userId` - ID del usuario que crea el reporte

**Respuestas:**
- `201 CREATED` - Reporte creado exitosamente
- `400 BAD_REQUEST` - Datos de entrada inválidos
- `500 INTERNAL_SERVER_ERROR` - Error interno del servidor

#### ✏️ Actualizar Reporte
```http
PUT /api/reports/{id}
```

**Parámetros:**
- `id` (Long) - ID único del reporte a actualizar

**Descripción:** Actualiza un reporte existente.

**Request Body:**
```json
{
  "title": "Nuevo título",
  "description": "Nueva descripción",
  "location": "Nueva ubicación",
  "date": "2025-09-22T11:00:00",
  "userId": 1
}
```

**Notas:**
- Solo se actualizarán los campos proporcionados
- Los campos vacíos o null se ignorarán

**Respuestas:**
- `200 OK` - Reporte actualizado exitosamente
- `404 NOT_FOUND` - Reporte no encontrado
- `500 INTERNAL_SERVER_ERROR` - Error interno del servidor

#### 🗑️ Eliminar Reporte
```http
DELETE /api/reports/{id}
```

**Parámetros:**
- `id` (Long) - ID único del reporte a eliminar

**Descripción:** Elimina un reporte específico del sistema.

**Respuestas:**
- `204 NO_CONTENT` - Reporte eliminado exitosamente
- `404 NOT_FOUND` - Reporte no encontrado
- `500 INTERNAL_SERVER_ERROR` - Error interno del servidor

#### 🚨 Eliminar Todos los Reportes
```http
DELETE /api/reports
```

**Descripción:** Elimina todos los reportes del sistema. ¡Usar con precaución!

**Respuestas:**
- `204 NO_CONTENT` - Todos los reportes eliminados exitosamente
- `500 INTERNAL_SERVER_ERROR` - Error interno del servidor

### 📝 Ejemplos de Uso

#### Crear un reporte con curl:
```bash
curl -X POST http://localhost:8080/api/reports \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Incidente en el parque",
    "description": "Daños en el mobiliario urbano",
    "location": "Parque Central",
    "date": "2025-09-22T14:30:00",
    "userId": 1
  }'
```

#### Obtener todos los reportes:
```bash
curl -X GET http://localhost:8080/api/reports
```

#### Actualizar un reporte:
```bash
curl -X PUT http://localhost:8080/api/reports/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Incidente resuelto",
    "description": "El incidente ha sido solucionado"
  }'
```

### 🔧 Características Técnicas

- **CORS habilitado:** Permite peticiones desde cualquier origen (`@CrossOrigin(origins = "*")`)
- **Validaciones:** Campos obligatorios validados automáticamente
- **Manejo de errores:** Try-catch en todos los endpoints para captura de excepciones
- **ResponseEntity:** Uso de ResponseEntity para control preciso de códigos de estado HTTP
- **Inyección de dependencias:** Uso de `@Autowired` para el repositorio
- **Documentación:** JavaDoc completo en todos los métodos

### 🏗️ Arquitectura

El ReportController sigue el patrón de arquitectura en capas:
- **Controlador (Controller):** Maneja las peticiones HTTP y respuestas
- **Repositorio (Repository):** Acceso a datos mediante Spring Data JPA
- **Entidad (Model):** Representación de los datos (Report.java)

### 🧪 Testing

Para probar los endpoints, puedes usar:
- **Postman** - Interfaz gráfica para pruebas de API
- **curl** - Línea de comandos
- **Swagger UI** - (si está configurado) interfaz web para documentación interactiva

## Estado del Proyecto

✅ **Completado:**
- Modelo Report creado
- ReportRepository implementado
- ReportController con endpoints CRUD básicos
- **Modelo User creado con campos completos (id, username, password, email, roles)**
- **UserRepository implementado con métodos de búsqueda personalizados**
- Documentación completa de la API
- **Documentación completa del modelo User y UserRepository**

🚧 **En desarrollo:**
- Autenticación y autorización
- Validaciones avanzadas
- Paginación y filtrado
- Testing unitario e integración
- **UserController para gestión de usuarios**

📋 **Pendiente:**
- Implementación de servicios (Service layer)
- Manejo de excepciones personalizado
- Logging y monitoreo
- Despliegue en producción
- **Endpoints CRUD para gestión de usuarios**
- **Implementación de autenticación con JWT**
- **Hash de contraseñas con BCrypt**
