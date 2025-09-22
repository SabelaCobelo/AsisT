import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import AuthService from '../services/AuthService';
import { AuthContext } from '../context/AuthContext';
import './RegisterPage.css';

const RegisterPage = () => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  const [errors, setErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const validateForm = () => {
    const newErrors = {};
    
    if (!formData.username.trim()) {
      newErrors.username = 'El nombre de usuario es requerido';
    } else if (formData.username.length < 3) {
      newErrors.username = 'El nombre de usuario debe tener al menos 3 caracteres';
    }

    if (!formData.email.trim()) {
      newErrors.email = 'El email es requerido';
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
      newErrors.email = 'Formato de email inválido';
    }

    if (!formData.password) {
      newErrors.password = 'La contraseña es requerida';
    } else if (formData.password.length < 8) {
      newErrors.password = 'La contraseña debe tener al menos 8 caracteres';
    } else if (!/[A-Z]/.test(formData.password) || !/[a-z]/.test(formData.password) || !/[0-9]/.test(formData.password)) {
      newErrors.password = 'Debe incluir mayúsculas, minúsculas y números';
    }

    if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = 'Las contraseñas no coinciden';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
    if (errors[name]) setErrors(prev => ({ ...prev, [name]: '' }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
    setIsLoading(true);
    setErrors({});
    try {
      const response = await AuthService.register({
        username: formData.username,
        email: formData.email,
        password: formData.password
      });
      if (response.success) {
        // Iniciar sesión automáticamente tras registrar
        login(response.data.user, response.data.accessToken);
        navigate('/dashboard');
      } else {
        setErrors({ general: response.error || 'Error en el registro' });
      }
    } catch (error) {
      console.error('Error durante el registro:', error);
      setErrors({ general: 'Error de conexión. Inténtalo de nuevo.' });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="register-page">
      <div className="register-container">
        <div className="register-header">
          <h1>Crear Cuenta</h1>
          <p>Únete a AsisT en pocos pasos</p>
        </div>

        <form className="register-form" onSubmit={handleSubmit}>
          {errors.general && (
            <div className="error-message general-error">
              <i className="error-icon">⚠️</i>
              {errors.general}
            </div>
          )}

          <div className="form-group">
            <label htmlFor="username" className="form-label">Nombre de usuario *</label>
            <div className="input-wrapper">
              <input
                type="text"
                id="username"
                name="username"
                value={formData.username}
                onChange={handleChange}
                className={`form-input ${errors.username ? 'input-error' : ''}`}
                placeholder="tu_usuario"
                disabled={isLoading}
                autoComplete="username"
                required
              />
              <i className="input-icon user-icon">👤</i>
            </div>
            {errors.username && <span className="error-text">{errors.username}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="email" className="form-label">Email *</nlabel>
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
            {errors.email && <span className="error-text">{errors.email}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="password" className="form-label">Contraseña *</label>
            <div className="input-wrapper">
              <input
                type={showPassword ? 'text' : 'password'}
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                className={`form-input ${errors.password ? 'input-error' : ''}`}
                placeholder="Tu contraseña segura"
                disabled={isLoading}
                autoComplete="new-password"
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
            {errors.password && <span className="error-text">{errors.password}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="confirmPassword" className="form-label">Confirmar contraseña *</label>
            <div className="input-wrapper">
              <input
                type={showPassword ? 'text' : 'password'}
                id="confirmPassword"
                name="confirmPassword"
                value={formData.confirmPassword}
                onChange={handleChange}
                className={`form-input ${errors.confirmPassword ? 'input-error' : ''}`}
                placeholder="Repite la contraseña"
                disabled={isLoading}
                autoComplete="new-password"
                required
              />
            </div>
            {errors.confirmPassword && <span className="error-text">{errors.confirmPassword}</span>}
          </div>

          <button type="submit" className={`register-button ${isLoading ? 'loading' : ''}`} disabled={isLoading}>
            {isLoading ? (<><span className="spinner"></span>Creando cuenta...</>) : 'Crear Cuenta'}
          </button>

          <div className="policy-note">
            <small>
              Al registrarte, aceptas nuestros <Link to="/terms">Términos</Link> y <Link to="/privacy">Política de Privacidad</Link>.
            </small>
          </div>
        </form>

        <div className="signin-link">
          <p>
            ¿Ya tienes cuenta? <Link to="/login" className="login-link">Inicia sesión</Link>
          </p>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;
