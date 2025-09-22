import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import AuthService from '../services/AuthService';
import { AuthContext } from '../context/AuthContext';
import './LoginPage.css';

const LoginPage = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });
  const [errors, setErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [rememberMe, setRememberMe] = useState(false);
  
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  // Validación del formulario
  const validateForm = () => {
    const newErrors = {};
    
    // Validación del email
    if (!formData.email.trim()) {
      newErrors.email = 'El email es requerido';
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
      newErrors.email = 'Formato de email inválido';
    }
    
    // Validación de la contraseña
    if (!formData.password) {
      newErrors.password = 'La contraseña es requerida';
    } else if (formData.password.length < 6) {
      newErrors.password = 'La contraseña debe tener al menos 6 caracteres';
    }
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Manejo del cambio en los inputs
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    
    // Limpiar error del campo cuando el usuario comience a escribir
    if (errors[name]) {
      setErrors(prev => ({
        ...prev,
        [name]: ''
      }));
    }
  };

  // Manejo del envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!validateForm()) {
      return;
    }
    
    setIsLoading(true);
    setErrors({});
    
    try {
      const response = await AuthService.login({
        email: formData.email,
        password: formData.password
      });
      
      if (response.success) {
        // Actualizar contexto de autenticación
        login(response.data.user, response.data.accessToken);
        
        // Redirigir al dashboard o página principal
        navigate('/dashboard');
      } else {
        // Manejar errores de autenticación
        setErrors({ 
          general: response.error || 'Error en el inicio de sesión' 
        });
      }
    } catch (error) {
      console.error('Error durante el login:', error);
      setErrors({ 
        general: 'Error de conexión. Por favor, inténtalo de nuevo.' 
      });
    } finally {
      setIsLoading(false);
    }
  };

  // Manejo del inicio de sesión con Google (placeholder)
  const handleGoogleLogin = () => {
    // Implementar integración con Google OAuth
    console.log('Google login not implemented yet');
  };

  return (
    <div className="login-page">
      <div className="login-container">
        <div className="login-header">
          <h1>Iniciar Sesión</h1>
          <p>Accede a tu cuenta de AsisT</p>
        </div>
        
        <form className="login-form" onSubmit={handleSubmit}>
          {/* Error general */}
          {errors.general && (
            <div className="error-message general-error">
              <i className="error-icon">⚠️</i>
              {errors.general}
            </div>
          )}
          
          {/* Campo Email */}
          <div className="form-group">
            <label htmlFor="email" className="form-label">
              Email *
            </label>
            <div className="input-wrapper">
              <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className={`form-input ${errors.email ? 'input-error' : ''}`}
                placeholder="tu@email.com"
                disabled={isLoading}
                autoComplete="email"
                required
              />
              <i className="input-icon email-icon">✉️</i>
            </div>
            {errors.email && (
              <span className="error-text">{errors.email}</span>
            )}
          </div>
          
          {/* Campo Contraseña */}
          <div className="form-group">
            <label htmlFor="password" className="form-label">
              Contraseña *
            </label>
            <div className="input-wrapper">
              <input
                type={showPassword ? 'text' : 'password'}
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                className={`form-input ${errors.password ? 'input-error' : ''}`}
                placeholder="Tu contraseña"
                disabled={isLoading}
                autoComplete="current-password"
                required
              />
              <button
                type="button"
                className="password-toggle"
                onClick={() => setShowPassword(!showPassword)}
                disabled={isLoading}
                aria-label={showPassword ? 'Ocultar contraseña' : 'Mostrar contraseña'}
              >
                {showPassword ? '🙈' : '👁️'}
              </button>
            </div>
            {errors.password && (
              <span className="error-text">{errors.password}</span>
            )}
          </div>
          
          {/* Opciones adicionales */}
          <div className="form-options">
            <label className="checkbox-label">
              <input
                type="checkbox"
                checked={rememberMe}
                onChange={(e) => setRememberMe(e.target.checked)}
                disabled={isLoading}
              />
              <span className="checkmark"></span>
              Recordarme
            </label>
            
            <Link to="/forgot-password" className="forgot-link">
              ¿Olvidaste tu contraseña?
            </Link>
          </div>
          
          {/* Botón de envío */}
          <button
            type="submit"
            className={`login-button ${isLoading ? 'loading' : ''}`}
            disabled={isLoading}
          >
            {isLoading ? (
              <>
                <span className="spinner"></span>
                Iniciando sesión...
              </>
            ) : (
              'Iniciar Sesión'
            )}
          </button>
          
          {/* Divider */}
          <div className="divider">
            <span>o</span>
          </div>
          
          {/* Login con Google */}
          <button
            type="button"
            className="google-login-button"
            onClick={handleGoogleLogin}
            disabled={isLoading}
          >
            <img 
              src="https://developers.google.com/identity/images/g-logo.png" 
              alt="Google" 
              className="google-icon"
            />
            Continuar con Google
          </button>
        </form>
        
        {/* Link a registro */}
        <div className="signup-link">
          <p>
            ¿No tienes cuenta? {' '}
            <Link to="/register" className="register-link">
              Regístrate aquí
            </Link>
          </p>
        </div>
        
        {/* Footer */}
        <div className="login-footer">
          <p>
            Al iniciar sesión, aceptas nuestros {' '}
            <Link to="/terms" className="terms-link">Términos de Servicio</Link>
            {' '} y {' '}
            <Link to="/privacy" className="privacy-link">Política de Privacidad</Link>
          </p>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
