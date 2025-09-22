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

## Endpoints Principales

```
GET    /api/reports          # Listar reportes
POST   /api/reports          # Crear reporte
GET    /api/reports/{id}     # Obtener reporte por ID
PUT    /api/reports/{id}     # Actualizar reporte
DELETE /api/reports/{id}     # Eliminar reporte

GET    /api/users            # Gestión de usuarios
POST   /api/auth/login       # Autenticación
```

## Estado del Proyecto

🚧 **En desarrollo inicial** - Este README será actualizado conforme avance el proyecto.
