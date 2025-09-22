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
│                       ├── model/                   # Entidades JPA
│                       │   └── Report.java         # Entidad Report
│                       └── repository/              # Repositorios JPA
│                           └── ReportRepository.java # Repositorio de Report
└── frontend/         # Aplicación React
    ├── public/
    └── src/
```

## Backend - Spring Boot

### Entidades

#### Report Entity

La entidad `Report` representa un reporte social en el sistema.

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
- Extiende `JpaRepository` proporcionando operaciones CRUD básicas
- Anotada con `@Repository`
- Métodos de consulta automáticos basados en convenciones de Spring Data JPA
- Soporte para consultas complejas con múltiples criterios

## Frontend - React

Aplicación React para la interfaz de usuario del sistema de gestión de reportes.

## Tecnologías

### Backend
- **Java** - Lenguaje de programación principal
- **Spring Boot** - Framework de aplicación
- **Spring Data JPA** - Acceso a datos y persistencia
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
