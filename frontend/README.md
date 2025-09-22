# 🌆 AsisT Frontend - Interfaz Web Intuitiva

> Construyendo ciudades más inteligentes, participativas y humanas. 💡🤝🏙️

### Plataforma colaborativa para gestión urbana con React moderno

![React](https://img.shields.io/badge/React-18.x-61DAFB?style=for-the-badge&logo=react&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![Axios](https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6+-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge)
![Version](https://img.shields.io/badge/Version-1.0.0-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

## 🚀 Descripción
AsisT Frontend es una **interfaz web moderna e intuitiva** desarrollada con React que permite a ciudadanos y administraciones gestionar reportes urbanos de forma colaborativa. Diseñada con **UX/UI centrado en el usuario** para maximizar la participación ciudadana y eficiencia administrativa.

### ✨ Características Destacadas
- 🎯 **Interfaz Intuitiva** - Diseño limpio y accesible para todos los usuarios
- 📱 **Responsive Design** - Adaptado para móviles, tablets y escritorio
- ⚡ **Performance Optimizado** - Carga rápida y navegación fluida
- 🎨 **Diseño Moderno** - Componentes visuales atractivos y profesionales
- 🔄 **Real-time Updates** - Actualizaciones dinámicas sin recarga
- 🌐 **PWA Ready** - Preparado para Progressive Web App

## ⚡ Instalación Rápida

### Prerrequisitos
✅ Node.js 16+

✅ npm 8+ o yarn

✅ Git

### 🚀 Inicio en 3 pasos
```bash
# 1️⃣ Clonar y navegar
git clone https://github.com/SabelaCobelo/AsisT.git
cd AsisT/frontend

# 2️⃣ Instalar dependencias
npm install

# 3️⃣ Ejecutar en desarrollo
npm start
```
🌐 **La aplicación estará disponible en:** `http://localhost:3000`

### 📋 Comandos Adicionales
| Comando       | Descripción                 | Estado |
|---------------|-----------------------------|--------|
| `npm start`   | 🔧 Servidor de desarrollo   | ✅     |
| `npm test`    | 🧪 Ejecutar pruebas         | ✅     |
| `npm run build` | 📦 Build de producción    | ✅     |
| `npm run eject` | ⚙️ Exponer configuración  | ⚠️     |

## 🏗️ Arquitectura del Proyecto
```
frontend/
├── 📁 public/
│   ├── index.html          # 🌐 Plantilla HTML principal
│   ├── favicon.ico         # 🎨 Icono de la aplicación
│   └── manifest.json       # 📱 Configuración PWA
├── 📁 src/
│   ├── 📁 components/      # 🧩 Componentes reutilizables
│   │   ├── Header/         # 🎯 Navegación principal
│   │   ├── ReportCard/     # 📄 Tarjetas de reportes
│   │   └── UserPanel/      # 👤 Panel de usuario
│   ├── 📁 pages/           # 📑 Páginas principales
│   │   ├── Dashboard/      # 📊 Panel de control
│   │   ├── Reports/        # 📋 Gestión de reportes
│   │   └── Profile/        # 👨‍💻 Perfil de usuario
│   ├── 📁 services/        # 🔌 Servicios API
│   ├── 📁 styles/          # 🎨 Estilos globales
│   ├── App.js              # 🚀 Componente raíz
│   └── index.js            # 🎯 Punto de entrada
├── package.json            # 📦 Dependencias
└── README.md               # 📖 Esta documentación
```

## 🎯 Funcionalidades Esenciales
| Funcionalidad         | Descripción                                 | Prioridad | Estado |
|-----------------------|---------------------------------------------|----------|--------|
| 🏠 Dashboard          | Panel principal con métricas y resumen      | Alta     | ✅     |
| 📋 Gestión de Reportes| CRUD completo de incidencias urbanas        | Alta     | ✅     |
| 👤 Autenticación      | Login/registro con JWT                      | Alta     | 🚧     |
| 🗺️ Mapas Interactivos | Geolocalización de reportes                 | Media    | 📋     |
| 📊 Analytics          | Estadísticas y métricas visuales            | Media    | 📋     |
| 🔔 Notificaciones     | Alertas en tiempo real                      | Baja     | 📋     |

## 🖼️ Demo Visual (Ejemplos y Guía)
> Próximamente se añadirán capturas y GIFs reales del producto. Mientras tanto, usa estos placeholders y guía para contribuir.

### 📸 Placeholders de Imágenes/GIFs
- Dashboard (desktop): `docs/demo/dashboard-desktop.png`
- Flujo de creación de reporte (GIF): `docs/demo/crear-reporte.gif`
- Vista móvil: `docs/demo/dashboard-mobile.png`

Muestra rápida en Markdown:
```md
![📸 Dashboard (Desktop)](docs/demo/dashboard-desktop.png)
![🎬 Crear Reporte (Flujo)](docs/demo/crear-reporte.gif)
![📱 Vista Móvil](docs/demo/dashboard-mobile.png)
```

### ⬆️ ¿Cómo subir imágenes/GIFs?
1. Crea las carpetas si no existen: `mkdir -p docs/demo`.
2. Arrastra y suelta los archivos en GitHub o añade por Git:
   ```bash
   git add docs/demo/dashboard-desktop.png docs/demo/crear-reporte.gif docs/demo/dashboard-mobile.png
   git commit -m "docs(demo): agrega capturas y gif de demo"
   git push origin main
   ```
3. Usa rutas relativas en el README como en los ejemplos anteriores.
4. Para GIFs optimizados, usa 800px de ancho máximo y comprímelos (por ejemplo, ezgif.com o gifski).

### 🧩 Ejemplo de Componentes (con Emojis)
A continuación, snippets representativos manteniendo el estilo visual:

- Header (🎯 navegación principal):
```jsx
// src/components/Header/index.jsx
export default function Header() {
  return (
    <header className="app-header">
      <h1>AsisT 🏙️</h1>
      <nav>
        <a href="/">🏠 Inicio</a>
        <a href="/reports">📋 Reportes</a>
        <a href="/profile">👤 Perfil</a>
      </nav>
    </header>
  );
}
```

- ReportCard (📄 tarjeta de reporte):
```jsx
// src/components/ReportCard/index.jsx
export function ReportCard({ title, status, location }) {
  const statusIcon = {
    open: "🟢",
    in_progress: "🟡",
    closed: "🔵",
  }[status] ?? "⚪";

  return (
    <article className="report-card">
      <h3>{statusIcon} {title}</h3>
      <p>📍 {location}</p>
      <button>👁️ Ver detalle</button>
    </article>
  );
}
```

- UserPanel (👤 acciones rápidas):
```jsx
// src/components/UserPanel/index.jsx
export function UserPanel({ user }) {
  return (
    <aside className="user-panel">
      <p>Hola, {user.name} 👋</p>
      <button>➕ Crear reporte</button>
      <button>🧾 Mis reportes</button>
      <button>🚪 Cerrar sesión</button>
    </aside>
  );
}
```

## 🧭 Casos de Uso: Ciudadanía vs. Administración
| Escenario real | Ciudadanía (👥) | Administración (🏛️) |
|---|---|---|
| Bache en la vía | Reporta con foto y ubicación en 30s. | Recibe ticket, prioriza por severidad y asigna cuadrilla. |
| Farola averiada | Sigue el estado del arreglo en tiempo real. | Reagrupa incidencias por zona y planifica ruta nocturna. |
| Residuos en parque | Vota y comenta para aumentar prioridad. | Programa limpieza y comunica resolución al ciudadano. |

## 🎨 Demo Visual
> 🚧 Capturas de pantalla y GIFs coming soon!
- 📸 Screenshots del dashboard principal
- 🎬 GIF demostrando el flujo de creación de reportes
- 📱 Capturas de la versión móvil

## 💡 UX/UI y Motivación Social

### 🎯 Experiencia de Usuario
"Diseñado para empoderar la participación ciudadana"
Nuestro enfoque UX se basa en:
- 🧠 Psicología Cognitiva - Interfaces que reducen la carga mental
- 🎨 Design System - Consistencia visual en toda la aplicación
- ♿ Accesibilidad - Cumplimiento WCAG 2.1 AA
- 📊 Data-Driven - Decisiones basadas en analytics de usuario

### 🌍 Impacto Social
"Construyendo puentes digitales entre ciudadanía y administración"

Para Ciudadanos:
- 📱 Reportar incidencias desde cualquier dispositivo
- 👁️ Seguimiento transparente del estado de reportes
- 🤝 Participación activa en la mejora urbana

Para Administraciones:
- 📊 Centralización y priorización eficiente
- 📈 Métricas de gestión urbana
- 💬 Comunicación directa con ciudadanos

## 🗓️ Roadmap de Componentes

### ✅ Completado
- 🏗️ Estructura base del proyecto React
- 🎨 Sistema de estilos CSS moderno
- 📦 Configuración de dependencias
- 🧩 Componentes básicos (App, Header)
- 📋 Documentación técnica

### 🚧 En Desarrollo
- 🎯 Dashboard principal con métricas
- 📄 Componentes de gestión de reportes
- 🔐 Sistema de autenticación JWT
- 📱 Optimización para móviles
- 🧪 Suite de testing (Jest + Testing Library)

### 📋 Próximas Versiones
- 🗺️ Integración con mapas (Google Maps/OpenStreet)
- 🔔 Sistema de notificaciones push
- 📊 Dashboard de analytics avanzado
- 🎨 Tema oscuro/claro
- 🌐 Internacionalización (i18n)
- 📱 Aplicación móvil nativa (React Native)

## 🤝 Guía de Contribución
¡Tu contribución es bienvenida! Sigue estos pasos:

### 🛠️ Desarrollo Local
```bash
# 1️⃣ Fork del repositorio
git clone https://github.com/TU-USUARIO/AsisT.git

# 2️⃣ Crear rama feature
git checkout -b feature/nueva-funcionalidad

# 3️⃣ Hacer cambios y commit
git commit -m "✨ Add: nueva funcionalidad increíble"

# 4️⃣ Push y Pull Request
git push origin feature/nueva-funcionalidad
```

### 📏 Estándares de Código
- ✅ ESLint configurado para consistency
- 🎨 Prettier para formateo automático
- 📝 Conventional Commits para mensajes claros
- 🧪 Tests requeridos para nuevas features

### 🎯 Áreas de Contribución
- 🐛 Bug fixes - Siempre bienvenidos
- ✨ Nuevas features - Consultar roadmap
- 🎨 Mejoras UX/UI - Diseño y usabilidad
- 📚 Documentación - Guías y tutoriales
- 🧪 Testing - Cobertura y calidad

## 📞 Contacto y Soporte

### 👨‍💻 Desarrolladora Principal
Sabela Cobelo

🎓 Ingeniera Informática

🏫 [Universidad/Centro de Estudios]

[![GitHub](https://img.shields.io/badge/GitHub-SabelaCobelo-181717?logo=github)](https://github.com/SabelaCobelo)
[![Email](https://img.shields.io/badge/Email-Contacto-informational?logo=gmail)](mailto:tu-email@ejemplo.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Perfil-0A66C2?logo=linkedin&logoColor=white)](https://linkedin.com/in/tu-perfil)

---

> Juntos, transformamos reportes en resultados y ciudades en comunidades mejores. ✨🏙️

[![Stars](https://img.shields.io/github/stars/SabelaCobelo/AsisT?style=social)](https://github.com/SabelaCobelo/AsisT/stargazers)
[![Forks](https://img.shields.io/github/forks/SabelaCobelo/AsisT?style=social)](https://github.com/SabelaCobelo/AsisT/network/members)
[![Issues](https://img.shields.io/github/issues/SabelaCobelo/AsisT?style=social)](https://github.com/SabelaCobelo/AsisT/issues)

© 2024 AsisT Project | MIT License — Parte del Trabajo Final de Carrera - Ingeniería Informática
