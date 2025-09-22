# AsisT - Sistema de Gestión de Reportes Sociales
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen.svg)](https://spring.io/projects/spring-boot) [![React](https://img.shields.io/badge/React-18.2-blue.svg)](https://reactjs.org/) [![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/projects/jdk/17/) [![JWT](https://img.shields.io/badge/JWT-Authentication-success.svg)](https://jwt.io/) [![TFC](https://img.shields.io/badge/TFC-Memoria%20Acad%C3%A9mica-purple.svg)](#memoria-tfc)

Sistema de gestión de reportes sociales desarrollado con React en el frontend y Spring Boot en el backend. Proyecto desarrollado como Trabajo de Fin de Carrera (TFC) enfocado en la gestión profesional de reportes sociales con autenticación JWT avanzada.

## 📋 Estado del Proyecto - Checklist Visual
### 🔧 Backend (Spring Boot)
- ✅ **Arquitectura base implementada**
  - ✅ Configuración Spring Boot con Java 17
  - ✅ Estructura MVC (Controller, Service, Repository, Model)
  - ✅ Base de datos JPA configurada
- ✅ **Sistema de Autenticación JWT**
  - ✅ JwtService (generación, validación, refresh tokens)
  - ✅ SecurityConfig (filtros, CORS, stateless)
  - ✅ AuthController (registro, login, refresh-token)
  - ✅ Encriptación BCrypt con factor 12
  - ✅ Validación de entrada con Bean Validation
- ✅ **Controladores REST**
  - ✅ AuthController (registro/login/refresh)
  - ✅ UserController (gestión de usuarios)
  - ✅ ReportController (gestión de reportes)
- 🚧 **Funcionalidades de Negocio**
  - ✅ CRUD básico de reportes
  - ✅ Gestión de usuarios
  - 🚧 Lógica avanzada de reportes sociales
  - ⏳ Sistema de notificaciones
  - ⏳ Reportes y estadísticas
- ✅ **Documentación y Testing**
  - ✅ Swagger/OpenAPI configurado
  - ✅ Documentación de endpoints JWT
  - 🚧 Tests unitarios
  - ⏳ Tests de integración

### 🎨 Frontend (React)
- ✅ **Configuración base**
  - ✅ Aplicación React 18.2 creada
  - ✅ Estructura de componentes
  - ✅ Routing configurado
- 🚧 **Integración con Backend**
  - ✅ Servicios HTTP (axios/fetch)
  - 🚧 Gestión de tokens JWT
  - 🚧 Interceptores para Authorization header
  - ⏳ Manejo de refresh tokens automático
- 🚧 **Interfaz de Usuario**
  - 🚧 Formularios de login/registro
  - 🚧 Dashboard principal
  - ⏳ Gestión de reportes (CRUD)
  - ⏳ Perfil de usuario
  - ⏳ Componentes reutilizables
- ⏳ **UX/UI Avanzado**
  - ⏳ Diseño responsivo
  - ⏳ Validación de formularios en tiempo real
  - ⏳ Estados de carga y error
  - ⏳ Notificaciones toast

### 🏗️ Infraestructura y Despliegue
- ✅ **Desarrollo Local**
  - ✅ Configuración de desarrollo
  - ✅ Variables de entorno documentadas
  - ✅ Scripts de inicio
- ⏳ **Base de Datos**
  - ✅ H2 para desarrollo
  - ⏳ PostgreSQL para producción
  - ⏳ Migraciones de esquema
  - ⏳ Backup y recovery
- ⏳ **Despliegue**
  - ⏳ Dockerización (backend + frontend)
  - ⏳ CI/CD pipeline
  - ⏳ Configuración de producción
  - ⏳ Monitoreo y logs

## 🚀 Guía de Inicio Rápido
### Requisitos Previos
```bash
# Instalar dependencias del sistema
- Java 17+ (OpenJDK recomendado)
- Node.js 18+ y npm
- Maven 3.8+
- Git
```

### Backend (Spring Boot)
```bash
# 1. Clonar repositorio
git clone https://github.com/SabelaCobelo/AsisT.git
cd AsisT/backend

# 2. Configurar variables de entorno
export JWT_SECRET_BASE64="tu_clave_base64_de_al_menos_256_bits"

# 3. Instalar dependencias y ejecutar
mvn clean install
mvn spring-boot:run

# ✅ Backend disponible en: http://localhost:8080
# ✅ Swagger UI: http://localhost:8080/swagger-ui.html
```

### Frontend (React)
```bash
# 1. Navegar al directorio frontend
cd AsisT/frontend

# 2. Instalar dependencias
npm install

# 3. Configurar variables de entorno
echo "REACT_APP_API_URL=http://localhost:8080/api" > .env.local

# 4. Ejecutar aplicación
npm start

# ✅ Frontend disponible en: http://localhost:3000
```

## 🧪 Testing y Validación
### Testing con Postman
Importa la colección de pruebas incluida o usa estos endpoints:

1. Registro de Usuario
```http
POST http://localhost:8080/api/auth/register
Content-Type: application/json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "SecurePass123!"
}
```

2. Login
```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json
{
  "email": "test@example.com",
  "password": "SecurePass123!"
}
```

3. Acceso a Endpoint Protegido
```http
GET http://localhost:8080/api/users
Authorization: Bearer {tu_access_token}
```

### Scripts de Prueba Automatizada
```bash
# Backend - Tests unitarios
cd backend
mvn test

# Frontend - Tests de componentes
cd frontend
npm test

# Tests de integración (cuando estén implementados)
npm run test:integration
```

## 📚 Estructura del Proyecto
```
AsisT/
├── backend/                    # 🔧 Spring Boot Application
│   ├── src/main/java/com/asist/
│   │   ├── AsistApplication.java       # 🚀 Main Spring Boot class
│   │   ├── controller/                 # 🎯 REST Controllers
│   │   │   ├── AuthController.java     # 🔐 Authentication endpoints
│   │   │   ├── UserController.java     # 👤 User management
│   │   │   └── ReportController.java   # 📊 Report management
│   │   ├── model/                      # 📋 JPA Entities
│   │   │   ├── User.java              # 👤 User entity
│   │   │   └── Report.java            # 📊 Report entity
│   │   ├── repository/                 # 💾 Data Access Layer
│   │   │   ├── UserRepository.java    # 👤 User repository
│   │   │   └── ReportRepository.java  # 📊 Report repository
│   │   ├── service/                    # 🛠️ Business Logic
│   │   │   ├── UserService.java       # 👤 User business logic
│   │   │   └── JwtService.java        # 🔐 JWT management
│   │   └── config/                     # ⚙️ Configuration
│   │       └── SecurityConfig.java    # 🔒 Security configuration
│   ├── src/main/resources/
│   │   └── application.yml            # ⚙️ App configuration
│   └── pom.xml                        # 📦 Maven dependencies
│
├── frontend/                   # 🎨 React Application
│   ├── public/                # 🌐 Static files
│   ├── src/                   # 💻 Source code
│   │   ├── components/        # 🧩 React components
│   │   ├── services/          # 🔗 API integration
│   │   ├── hooks/             # 🎣 Custom hooks
│   │   ├── context/           # 🌍 State management
│   │   └── utils/             # 🛠️ Utilities
│   ├── package.json          # 📦 npm dependencies
│   └── README.md             # 📖 Frontend documentation
│
├── docs/                      # 📚 Documentation
│   ├── api/                   # 📖 API documentation
│   ├── deployment/            # 🚀 Deployment guides
│   └── testing/               # 🧪 Testing guides
│
└── README.md                  # 📖 This file
```

## 🔐 Seguridad y Autenticación
### Flujo JWT Implementado
1. Registro: Validación + hash BCrypt + generación de tokens
2. Login: Autenticación + emisión de access/refresh tokens
3. Acceso: Validación JWT en cada request protegido
4. Refresh: Renovación automática de access tokens

### Buenas Prácticas Aplicadas
- ✅ Tokens con expiración corta (access: 15min, refresh: 7 días)
- ✅ Claves de al menos 256 bits para firmas JWT
- ✅ BCrypt con factor de coste 12
- ✅ CORS configurado por entorno
- ✅ Sesiones stateless
- ✅ Validación de entrada robusta

### Configuración de Seguridad
```yaml
jwt:
  secret: ${JWT_SECRET_BASE64}          # Variable de entorno requerida
  expiration: 900000                   # 15 minutos
  refresh-expiration: 604800000        # 7 días
spring:
  security:
    filter:
      dispatcher-types: REQUEST
```

## 📖 Memoria TFC
Este proyecto forma parte de un Trabajo de Fin de Carrera enfocado en:
- 🎯 Objetivo: Desarrollo de sistema de gestión de reportes sociales
- 🏗️ Arquitectura: Aplicación full-stack con separación clara frontend/backend
- 🔐 Seguridad: Implementación de autenticación JWT profesional
- 📊 Funcionalidad: Gestión completa de reportes y usuarios
- 🚀 Despliegue: Preparación para entornos de producción

### Enlaces Académicos
- 📚 [Memoria Completa del TFC](docs/memoria-tfc.pdf) (pendiente de subir)
- 📋 [Documentación Técnica](docs/technical-documentation.md)
- 🎥 [Video Demostración](docs/demo-video.md) (pendiente)

---

## ✅ Checklist de Requisitos DAW
Resumen de cumplimiento de los requisitos habituales de Desarrollo de Aplicaciones Web (DAW) para TFC/Proyecto Integrado.

### Tabla Resumen de Cumplimiento
| Requisito | Estado | Ubicación/Entrega | Notas/Acciones |
|---|---|---|---|
| Diagrama E-R | 🚧 Pendiente | docs/arquitectura/er/er-diagrama.png | Adjuntar PNG y fuente (drawio). |
| Casos de uso | 🚧 Pendiente | docs/analisis/casos-de-uso.md | Añadir diagramas UML y descripciones. |
| Wireframes | 🚧 Pendiente | docs/ux/wireframes/ | Exportar vistas clave (Login, Dashboard, CRUD). |
| Manual de usuario | 🚧 Borrador | docs/manuales/manual-usuario.md | Completar flujos e imágenes. |
| Manual de administrador | 🚧 Borrador | docs/manuales/manual-admin.md | Añadir gestión usuarios/roles y despliegue. |
| Viabilidad tecno-económica | 🚧 Pendiente | docs/estudios/viabilidad.md | Estimación de costes/tiempos y riesgos. |
| Control de versiones | ✅ Cumplido | GitHub (commits, ramas, PRs) | Seguir Conv. Commits y Git Flow simplificado. |
| Accesibilidad | 🚧 En progreso | docs/ux/accesibilidad.md | WCAG AA: contraste, focus, labels, ARIA. |

> Convenciones de estado: ✅ Cumplido · 🚧 En progreso/Borrador · ⏳ Pendiente

### Entregables y Estructura Propuesta
```
docs/
├─ arquitectura/
│  ├─ er/
│  │  ├─ er-diagrama.drawio
│  │  └─ er-diagrama.png
│  └─ uml/
│     └─ casos-de-uso.drawio
├─ analisis/
│  └─ casos-de-uso.md
├─ ux/
│  ├─ wireframes/
│  │  ├─ login.png
│  │  ├─ dashboard.png
│  │  └─ reporte-crud.png
│  └─ accesibilidad.md
├─ manuales/
│  ├─ manual-usuario.md
│  └─ manual-admin.md
└─ estudios/
   └─ viabilidad.md
```

### Guía para cada requisito (plantillas sugeridas)

#### 1) Diagrama E-R (🚧 Pendiente)
- Objetivo: modelar entidades principales (User, Report, Role, Attachment) y relaciones.
- Herramienta sugerida: draw.io (.drawio + export .png).
- Ubicación: docs/arquitectura/er/er-diagrama.drawio|png.
- Checklist:
  - [ ] Cardinalidades correctas (1-N, N-N con tabla intermedia si aplica).
  - [ ] Atributos clave (PK, FK) definidos.
  - [ ] Normalización básica y justificación de decisiones.

#### 2) Casos de uso (🚧 Pendiente)
- Contenido mínimo en docs/analisis/casos-de-uso.md:
  - [ ] Diagrama UML de alto nivel (login, gestionar reportes, gestionar usuarios).
  - [ ] Descripciones detalladas: actores, precondiciones, flujo principal, alternativos y postcondiciones.

#### 3) Wireframes (🚧 Pendiente)
- Vistas mínimas en docs/ux/wireframes/:
  - [ ] Login, Registro, Dashboard, Lista de reportes, Detalle/Edición.
  - [ ] Indicaciones de estados de carga y error.
  - [ ] Notas de responsive (mobile/desktop).

#### 4) Manual de Usuario (🚧 Borrador)
Archivo: docs/manuales/manual-usuario.md

Ejemplo de estructura y texto:
```
# Manual de Usuario – AsisT
## Introducción
AsisT permite registrar y gestionar reportes sociales...
## Acceso
1) Abra http://localhost:3000
2) Inicie sesión con su usuario o regístrese...
## Navegación
- Menú principal: Dashboard, Reportes, Perfil
## Operaciones frecuentes
- Crear reporte: Dashboard > Nuevo reporte > Completar formulario > Guardar
- Editar/Eliminar: Lista de reportes > Acciones
## Soporte
Email: soporte@asist.local
```

#### 5) Manual de Administrador (🚧 Borrador)
Archivo: docs/manuales/manual-admin.md

Ejemplo de estructura y texto:
```
# Manual de Administrador – AsisT
## Gestión de usuarios y roles
- Crear usuario, asignar rol (ADMIN/USER)
## Configuración del sistema
- Variables entorno (JWT_SECRET_BASE64, DB URL)
## Mantenimiento
- Respaldo BD (H2/PostgreSQL), logs, monitoreo
```

#### 6) Estudio de Viabilidad Tecno-Económica (🚧 Pendiente)
Archivo: docs/estudios/viabilidad.md
- Estimación de esfuerzo (personas·mes), costes (IaaS/PaaS), riesgos y mitigaciones.
- Plan de hitos y cronograma (Gantt resumido).

#### 7) Accesibilidad (🚧 En progreso)
Archivo: docs/ux/accesibilidad.md
- Criterios WCAG 2.2 AA abordados: contraste, navegación por teclado, foco visible, labels en formularios, uso de roles/ARIA.
- Pruebas sugeridas: Lighthouse, axe DevTools, NVDA/VoiceOver.

### Cómo adjuntar imágenes/diagramas en el README
Ejemplos de inclusión una vez subidos los archivos:
```md
![Diagrama E-R](docs/arquitectura/er/er-diagrama.png)
![Wireframe Dashboard](docs/ux/wireframes/dashboard.png)
```

---

## 🛣️ Roadmap de Desarrollo
### Fase Actual (🚧 En desarrollo)
- Completar integración frontend-backend
- Implementar gestión avanzada de reportes
- Mejorar interfaz de usuario

### Próximas Fases
1. 🎨 UX/UI Mejorado (Sprint 2)
   - Diseño responsivo completo
   - Componentes de UI avanzados
   - Validaciones en tiempo real
2. 📊 Analytics y Reportes (Sprint 3)
   - Dashboard con métricas
   - Exportación de datos
   - Filtros avanzados
3. 🚀 Producción (Sprint 4)
   - Dockerización completa
   - CI/CD automatizado
  
