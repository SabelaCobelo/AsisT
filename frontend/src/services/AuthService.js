import axios from 'axios';

// Configuración base de Axios
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para añadir automáticamente el token de acceso
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('accessToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Interceptor para manejar respuestas y refresh token automático
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      
      const refreshToken = localStorage.getItem('refreshToken');
      if (refreshToken) {
        try {
          const response = await axios.post(`${API_BASE_URL}/auth/refresh-token`, {
            refreshToken
          });
          
          const { accessToken } = response.data;
          localStorage.setItem('accessToken', accessToken);
          
          // Reintentar la petición original con el nuevo token
          originalRequest.headers.Authorization = `Bearer ${accessToken}`;
          return api(originalRequest);
        } catch (refreshError) {
          // Si el refresh token falla, limpiar tokens y redirigir al login
          AuthService.logout();
          window.location.href = '/login';
          return Promise.reject(refreshError);
        }
      } else {
        // No hay refresh token, redirigir al login
        AuthService.logout();
        window.location.href = '/login';
      }
    }
    
    return Promise.reject(error);
  }
);

class AuthService {
  /**
   * Registra un nuevo usuario
   * @param {Object} userData - Datos del usuario {username, email, password}
   * @returns {Promise} Promesa con la respuesta del servidor
   */
  static async register(userData) {
    try {
      const response = await api.post('/auth/register', userData);
      const { accessToken, refreshToken, user } = response.data;
      
      // Almacenar tokens de forma segura
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      localStorage.setItem('user', JSON.stringify(user));
      
      return {
        success: true,
        data: response.data,
        message: 'Usuario registrado exitosamente'
      };
    } catch (error) {
      return {
        success: false,
        error: error.response?.data?.message || 'Error en el registro',
        status: error.response?.status
      };
    }
  }

  /**
   * Inicia sesión con email y contraseña
   * @param {Object} credentials - Credenciales {email, password}
   * @returns {Promise} Promesa con la respuesta del servidor
   */
  static async login(credentials) {
    try {
      const response = await api.post('/auth/login', credentials);
      const { accessToken, refreshToken, user } = response.data;
      
      // Almacenar tokens y datos del usuario
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      localStorage.setItem('user', JSON.stringify(user));
      
      return {
        success: true,
        data: response.data,
        message: 'Inicio de sesión exitoso'
      };
    } catch (error) {
      return {
        success: false,
        error: error.response?.data?.message || 'Error en el inicio de sesión',
        status: error.response?.status
      };
    }
  }

  /**
   * Cierra la sesión del usuario
   */
  static logout() {
    // Limpiar todos los datos del localStorage
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('user');
    
    // Opcional: Notificar al servidor sobre el logout (si implementas blacklist de tokens)
    // api.post('/auth/logout').catch(() => {});
  }

  /**
   * Refresca el token de acceso usando el refresh token
   * @returns {Promise} Promesa con el nuevo access token
   */
  static async refreshToken() {
    try {
      const refreshToken = localStorage.getItem('refreshToken');
      if (!refreshToken) {
        throw new Error('No refresh token available');
      }

      const response = await axios.post(`${API_BASE_URL}/auth/refresh-token`, {
        refreshToken
      });
      
      const { accessToken } = response.data;
      localStorage.setItem('accessToken', accessToken);
      
      return {
        success: true,
        accessToken,
        message: 'Token refrescado exitosamente'
      };
    } catch (error) {
      this.logout();
      return {
        success: false,
        error: error.response?.data?.message || 'Error al refrescar token',
        status: error.response?.status
      };
    }
  }

  /**
   * Obtiene el token de acceso actual
   * @returns {string|null} Token de acceso o null si no existe
   */
  static getAccessToken() {
    return localStorage.getItem('accessToken');
  }

  /**
   * Obtiene el refresh token actual
   * @returns {string|null} Refresh token o null si no existe
   */
  static getRefreshToken() {
    return localStorage.getItem('refreshToken');
  }

  /**
   * Obtiene los datos del usuario almacenados
   * @returns {Object|null} Datos del usuario o null si no existe
   */
  static getCurrentUser() {
    try {
      const userStr = localStorage.getItem('user');
      return userStr ? JSON.parse(userStr) : null;
    } catch (error) {
      console.error('Error parsing user data:', error);
      return null;
    }
  }

  /**
   * Verifica si el usuario está autenticado
   * @returns {boolean} true si está autenticado, false en caso contrario
   */
  static isAuthenticated() {
    const token = this.getAccessToken();
    const user = this.getCurrentUser();
    return !!(token && user);
  }

  /**
   * Verifica si un token JWT ha expirado
   * @param {string} token - Token JWT a verificar
   * @returns {boolean} true si ha expirado, false en caso contrario
   */
  static isTokenExpired(token) {
    if (!token) return true;
    
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const currentTime = Date.now() / 1000;
      return payload.exp < currentTime;
    } catch (error) {
      console.error('Error parsing JWT:', error);
      return true;
    }
  }

  /**
   * Actualiza los datos del usuario almacenados
   * @param {Object} userData - Nuevos datos del usuario
   */
  static updateUser(userData) {
    try {
      localStorage.setItem('user', JSON.stringify(userData));
    } catch (error) {
      console.error('Error updating user data:', error);
    }
  }

  /**
   * Obtiene headers de autorización para peticiones manuales
   * @returns {Object} Headers con autorización o objeto vacío
   */
  static getAuthHeaders() {
    const token = this.getAccessToken();
    return token ? { Authorization: `Bearer ${token}` } : {};
  }

  /**
   * Realiza una petición autenticada usando el interceptor configurado
   * @param {string} method - Método HTTP
   * @param {string} url - URL de la petición
   * @param {Object} data - Datos a enviar (opcional)
   * @returns {Promise} Promesa con la respuesta
   */
  static async authenticatedRequest(method, url, data = null) {
    try {
      const config = { method, url };
      if (data) {
        config.data = data;
      }
      
      const response = await api(config);
      return {
        success: true,
        data: response.data
      };
    } catch (error) {
      return {
        success: false,
        error: error.response?.data?.message || 'Error en la petición',
        status: error.response?.status
      };
    }
  }
}

export default AuthService;
export { api };
