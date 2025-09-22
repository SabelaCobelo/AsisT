<div align="center">

# 🚀 AsisT Backend API

### *Plataforma digital para la gestión colaborativa de incidencias urbanas*

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

![Status](https://img.shields.io/badge/Status-En%20Desarrollo-yellow?style=flat-square)
![Version](https://img.shields.io/badge/Version-1.0.0--SNAPSHOT-blue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

---

</div>

## 📖 Descripción

**AsisT** es una API REST robusta desarrollada con **Spring Boot** que facilita la gestión colaborativa de reportes de incidencias urbanas. La plataforma permite a ciudadanos y administraciones locales trabajar juntos para identificar, reportar y gestionar problemas en el entorno urbano de manera eficiente.

### ✨ Características Principales

- 🔐 **Autenticación segura** con JWT y Spring Security
- 📊 **Gestión completa de reportes** (CRUD operations)
- 👥 **Sistema de usuarios** con roles diferenciados
- 🗃️ **Persistencia de datos** con JPA/Hibernate
- 🌐 **API RESTful** bien documentada
- ⚡ **Respuestas optimizadas** con códigos HTTP estándar

---

## ⚡ Instalación Rápida

### Prerrequisitos
```bash
☑️ Java 17+
☑️ Maven 3.8+
☑️ MySQL/PostgreSQL
```

### 🚀 Inicio Rápido (3 pasos)

```bash
# 1️⃣ Clonar y navegar al proyecto
git clone https://github.com/SabelaCobelo/AsisT.git
cd AsisT/backend

# 2️⃣ Instalar dependencias
mvn clean install

# 3️⃣ Ejecutar la aplicación
mvn spring-boot:run
```

> 🌐 **La API estará disponible en:** `http://localhost:8080`

### ⚙️ Configuración Base de Datos

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/asist_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

---

## 🏗️ Arquitectura del Proyecto

```
backend/
├── 📁 src/main/java/com/asist/
│   ├── 🎮 controller/     # Controladores REST
│   ├── ⚙️ service/        # Lógica de negocio
│   ├── 💾 repository/     # Acceso a datos
│   ├── 📋 model/          # Entidades JPA
│   ├── 🔧 config/         # Configuraciones
│   └── ⚠️ exception/      # Manejo de excepciones
├── 📁 src/main/resources/
│   ├── application.properties
│   └── data.sql
├── 📁 src/test/           # Tests unitarios
├── pom.xml
└── README.md
```

---

## 🌍 Casos de Uso Social

### 👤 Para Ciudadanos
- 📱 **Reportar incidencias** urbanas de forma rápida
- 📍 **Geolocalizar problemas** específicos
- 👀 **Seguir el estado** de reportes realizados
- 🤝 **Colaborar** con la administración local

### 🏛️ Para Administraciones
- 📊 **Centralizar reportes** ciudadanos
- ⏱️ **Priorizar intervenciones** urgentes
- 📈 **Analizar tendencias** de incidencias
- 🔄 **Comunicar** actualizaciones a ciudadanos

### 🎯 Impacto Social
> *"Promoviendo ciudades más inteligentes y participativas a través de la tecnología"*

---

## 📊 API Endpoints - Referencia Rápida

| Método | Endpoint | Descripción | Status |
|--------|----------|-------------|--------|
| `GET` | `/api/reports` | 📋 Listar todos los reportes | ✅ |
| `GET` | `/api/reports/{id}` | 🔍 Obtener reporte específico | ✅ |
| `POST` | `/api/reports` | ➕ Crear nuevo reporte | ✅ |
| `PUT` | `/api/reports/{id}` | ✏️ Actualizar reporte | ✅ |
| `DELETE` | `/api/reports/{id}` | 🗑️ Eliminar reporte | ✅ |
| `GET` | `/api/users` | 👥 Gestión de usuarios | 🚧 |
| `POST` | `/auth/login` | 🔐 Autenticación JWT | 🚧 |
| `POST` | `/auth/register` | 📝 Registro de usuarios | 🚧 |

### 💡 Ejemplo de Uso
```bash
# Crear un reporte
curl -X POST http://localhost:8080/api/reports \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Mobiliario urbano dañado",
    "description": "Banco roto en Parque Central",
    "location": "Parque Central - Zona Norte",
    "userId": 1
  }'
```

---

## 🔧 Tecnologías y Dependencias

<table>
<tr>
<td>

### Backend Core
- **Spring Boot 3.x**
- **Java 17+**
- **Maven**

</td>
<td>

### Persistencia
- **Spring Data JPA**
- **Hibernate**
- **MySQL/PostgreSQL**

</td>
<td>

### Seguridad
- **Spring Security**
- **JWT Authentication**
- **BCrypt Password Hash**

</td>
</tr>
</table>

---

## 🎓 Documentación Académica

### 📚 Trabajo Final de Carrera (TFC)

Esta API forma parte del Trabajo Final de Carrera desarrollado para la titulación en **Ingeniería Informática**.

**Temática:** *Sistemas de Información Geográfica aplicados a la gestión urbana colaborativa*

> 📄 **[Enlace a la Memoria del TFC](#)** *(Disponible próximamente)*

### 🎥 Demo en Vivo
> 🚀 **[Ver Demo del Sistema](#)** *(Próximamente disponible)*

---

## 📈 Estado del Desarrollo

### ✅ Completado
- [x] **Modelo de datos** (User, Report)
- [x] **Repositorios JPA** con consultas personalizadas
- [x] **API REST completa** para gestión de reportes
- [x] **Documentación técnica** detallada
- [x] **Estructura de proyecto** escalable

### 🚧 En Desarrollo
- [ ] **Autenticación JWT** y gestión de usuarios
- [ ] **Validaciones avanzadas** de entrada
- [ ] **Sistema de roles** y permisos
- [ ] **Testing unitario** e integración
- [ ] **Paginación y filtrado** de resultados

### 📋 Próximas Versiones
- [ ] **API Gateway** para microservicios
- [ ] **Notificaciones push** en tiempo real
- [ ] **Integración con mapas** (Google Maps API)
- [ ] **Dashboard administrativo** web
- [ ] **API móvil** optimizada

---

## 🤝 Contribución

¿Quieres contribuir al proyecto? ¡Excelente!

1. 🍴 **Fork** el repositorio
2. 🌿 Crea una **rama feature** (`git checkout -b feature/AmazingFeature`)
3. 💾 **Commit** tus cambios (`git commit -m 'Add AmazingFeature'`)
4. 📤 **Push** a la rama (`git push origin feature/AmazingFeature`)
5. 🔄 Abre un **Pull Request**

---

## 📞 Contacto y Soporte

**👨‍💻 Desarrollador:** Sabela Cobelo  
**🎓 Institución:** [Universidad/Centro de Estudios]  
**📧 Email:** [tu-email@ejemplo.com]  
**🐙 GitHub:** [@SabelaCobelo](https://github.com/SabelaCobelo)

---

<div align="center">

### 🌟 ¡Gracias por tu interés en AsisT!

**Construyendo ciudades más inteligentes, una API a la vez** 🏙️✨

[![GitHub stars](https://img.shields.io/github/stars/SabelaCobelo/AsisT?style=social)](https://github.com/SabelaCobelo/AsisT/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/SabelaCobelo/AsisT?style=social)](https://github.com/SabelaCobelo/AsisT/network/members)

---

**© 2024 AsisT Project | MIT License**

</div>
