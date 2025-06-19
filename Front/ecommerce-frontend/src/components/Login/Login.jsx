import React, { useState } from 'react';
import axios from 'axios';
import '@fortawesome/fontawesome-free/css/all.min.css';
import './Login.css';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        'http://localhost:8080/auth/login',
        { email, password }
      );
      console.log('Token:', response.data.token);
      setError('');
      // Redirigir o guardar token
    } catch (err) {
      console.error('Error al iniciar sesión:', err);
      setError('Correo o contraseña incorrecta.');
    }
  };

  return (
    <div className="login-container">
      <div className="login-image" />
      <div className="login-form">
        <h2>Iniciar sesión</h2>
        <p>Ingresa tu nombre de usuario y contraseña</p>
        {error && <div className="alert alert-danger">{error}</div>}
        <form onSubmit={handleSubmit} autoComplete="off">
          <div className="input-container">
            <span className="input-icon"><i className="fas fa-user" /></span>
            <input
              type="email"
              className="input-field"
              placeholder="Ingresa tu Correo"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="input-container">
            <span className="input-icon"><i className="fas fa-lock" /></span>
            <input
              type="password"
              className="input-field"
              placeholder="Ingresa tu Contraseña"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <span className="input-icon password-toggle" onClick={togglePassword}>
              <i className="fas fa-eye-slash" />
            </span>
          </div>
          <div className="form-options">
            <label className="checkbox-label">
              <input type="checkbox" name="remember" />
              <span>Recuérdame</span>
            </label>
            <a href="#" className="forgot-password">Olvidé mi contraseña</a>
          </div>
          <div className='btn'>
            <button type="submit">Iniciar sesión</button>
          </div>
          <div>
            <button type="button" className="register-btn" onClick={() => window.location.href = '/register'}>
              Registrarse
            </button>
          </div>
        </form>
      </div>
    </div>
  );

  function togglePassword() {
    const input = document.querySelector('.password-toggle').previousElementSibling;
    const icon = document.querySelector('.password-toggle i');
    if (input.type === 'password') {
      input.type = 'text';
      icon.classList.replace('fa-eye-slash', 'fa-eye');
    } else {
      input.type = 'password';
      icon.classList.replace('fa-eye', 'fa-eye-slash');
    }
  }
};

export default Login;