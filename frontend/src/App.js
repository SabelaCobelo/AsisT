import React from 'react';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>AsisT - Sistema de Asistencia Inteligente</h1>
        <p>
          Bienvenido al sistema de asistencia inteligente AsisT.
        </p>
        <p>
          Funcionalidades principales:
        </p>
        <ul style={{ textAlign: 'left', maxWidth: '400px' }}>
          <li>Gestión de usuarios</li>
          <li>Sistema de autenticación</li>
          <li>Panel de control</li>
          <li>Reportes y estadísticas</li>
          <li>Configuración personalizable</li>
        </ul>
      </header>
    </div>
  );
}

export default App;
