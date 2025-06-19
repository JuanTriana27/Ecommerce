import React, { useState } from 'react';
import axios from 'axios';
import '@fortawesome/fontawesome-free/css/all.min.css';
import './Register.css';
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const navigate = useNavigate();
    const [nombre, setNombre] = useState('');
    const [apellido, setApellido] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [mensaje, setMensaje] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/usuario/guardar-nuevo', {
                nombre,
                apellido,
                email,
                passwordHash: password,
                fechaCreacion: new Date().toISOString(),
                idRol: 1
            });
            console.log('Usuario creado:', response.data);
            setMensaje('Usuario registrado exitosamente');
            setError('');
        } catch (err) {
            console.error('Error al registrar:', err);
            setError('Error al registrar usuario');
            setMensaje('');
        }
    };

    return (
        <div className="login-container">
            <div className="register-container">
                <div className="register-image" />
                <div className="register-form">
                    <h2>Registro</h2>
                    <p>Crea tu cuenta</p>
                    {mensaje && <div className="alert alert-success">{mensaje}</div>}
                    {error && <div className="alert alert-danger">{error}</div>}

                    <form onSubmit={handleSubmit} autoComplete="off">
                        <div className="input-container">
                            <span className="input-icon"><i className="fas fa-user" /></span>
                            <input
                                type="text"
                                className="input-field"
                                placeholder="Nombre"
                                value={nombre}
                                onChange={(e) => setNombre(e.target.value)}
                                required
                            />
                        </div>

                        <div className="input-container">
                            <span className="input-icon"><i className="fas fa-user" /></span>
                            <input
                                type="text"
                                className="input-field"
                                placeholder="Apellido"
                                value={apellido}
                                onChange={(e) => setApellido(e.target.value)}
                                required
                            />
                        </div>

                        <div className="input-container">
                            <span className="input-icon"><i className="fas fa-envelope" /></span>
                            <input
                                type="email"
                                className="input-field"
                                placeholder="Correo electrónico"
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
                                placeholder="Contraseña"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>

                        <div className='btn'>
                            <button type="submit">Registrarse</button>
                        </div>

                        <div>
                            <button
                                type="button"
                                className="login-btn"
                                onClick={() => navigate('/')}
                            >
                                Volver a Login
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Register;