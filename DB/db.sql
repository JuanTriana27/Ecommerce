-- ========================
-- 1. ROLES
-- ========================
CREATE TABLE roles (
    idRol SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (nombre) VALUES
('ADMIN'),
('CLIENTE');

-- ========================
-- 2. USUARIOS
-- ========================
CREATE TABLE usuarios (
    idUsuario SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,  -- Nombre cambiado y sin caracter especial
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    idRol INTEGER NOT NULL REFERENCES roles(idRol)  -- Referencia actualizada
);

-- Usuario de prueba
INSERT INTO usuarios (nombre, email, password_hash, idRol) VALUES
('Juan Pérez', 'juan.perez@example.com', '$2a$10$EjemploHashDeContraseña', 2);

-- ========================
-- 3. CATEGORÍAS
-- ========================
CREATE TABLE categorias (
    idCategoria SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

INSERT INTO categorias (nombre, descripcion) VALUES
('Tecnología', 'Productos tecnológicos y gadgets'),
('Ropa', 'Ropa para hombre y mujer'),
('Hogar', 'Artículos para el hogar');

-- ========================
-- 4. PRODUCTOS
-- ========================
CREATE TABLE productos (
    idProducto SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INTEGER DEFAULT 0,
    imagen_url TEXT,
    idCategoria INTEGER REFERENCES categorias(idCategoria)  -- Referencia actualizada
);

INSERT INTO productos (nombre, descripcion, precio, stock, imagen_url, idCategoria) VALUES
('Laptop Lenovo IdeaPad', 'Laptop de 15 pulgadas con 16GB RAM', 2499.99, 10, 'https://ejemplo.com/laptop.jpg', 1),
('Camiseta Blanca', 'Camiseta de algodón unisex', 29.90, 50, 'https://ejemplo.com/camiseta.jpg', 2),
('Lámpara de Escritorio', 'Lámpara LED con brazo flexible', 89.00, 25, 'https://ejemplo.com/lampara.jpg', 3);

-- ========================
-- 5. DIRECCIONES DE ENVÍO
-- ========================
CREATE TABLE direcciones (
    idDireccion SERIAL PRIMARY KEY,
    idUsuario INTEGER REFERENCES usuarios(idUsuario) ON DELETE CASCADE,  -- Referencia actualizada
    nombre_destinatario VARCHAR(100),
    direccion TEXT NOT NULL,
    ciudad VARCHAR(100),
    departamento VARCHAR(100),
    pais VARCHAR(100),
    codigo_postal VARCHAR(20)
);

INSERT INTO direcciones (idUsuario, nombre_destinatario, direccion, ciudad, departamento, pais, codigo_postal) VALUES
(1, 'Juan Pérez', 'Calle 123 #45-67', 'Cali', 'Valle del Cauca', 'Colombia', '760001');

-- ========================
-- 6. CARRITO DE COMPRAS
-- ========================
CREATE TABLE carrito (
    idCarrito SERIAL PRIMARY KEY,
    idUsuario INTEGER NOT NULL REFERENCES usuarios(idUsuario) ON DELETE CASCADE  -- Referencia actualizada
);

INSERT INTO carrito (idUsuario) VALUES (1);

-- ========================
-- 7. ITEMS DEL CARRITO
-- ========================
CREATE TABLE carrito_items (
    idCarritoItem SERIAL PRIMARY KEY,
    idCarrito INTEGER REFERENCES carrito(idCarrito) ON DELETE CASCADE,  -- Referencia actualizada
    idProducto INTEGER REFERENCES productos(idProducto),  -- Referencia actualizada
    cantidad INTEGER DEFAULT 1
);

INSERT INTO carrito_items (idCarrito, idProducto, cantidad) VALUES
(1, 1, 1),
(1, 3, 2);

-- ========================
-- 8. PEDIDOS
-- ========================
CREATE TABLE pedidos (
    idPedido SERIAL PRIMARY KEY,
    idUsuario INTEGER NOT NULL REFERENCES usuarios(idUsuario),  -- Referencia actualizada
    idDireccion INTEGER REFERENCES direcciones(idDireccion),  -- Referencia actualizada
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) DEFAULT 'pendiente',
    total DECIMAL(10, 2)
);

INSERT INTO pedidos (idUsuario, idDireccion, estado, total) VALUES
(1, 1, 'pendiente', 2677.99);

-- ========================
-- 9. DETALLES DEL PEDIDO
-- ========================
CREATE TABLE pedido_detalles (
    idDetalle SERIAL PRIMARY KEY,
    idPedido INTEGER REFERENCES pedidos(idPedido) ON DELETE CASCADE,  -- Referencia actualizada
    idProducto INTEGER REFERENCES productos(idProducto),  -- Referencia actualizada
    cantidad INTEGER,
    precio_unitario DECIMAL(10, 2)
);

INSERT INTO pedido_detalles (idPedido, idProducto, cantidad, precio_unitario) VALUES
(1, 1, 1, 2499.99),
(1, 3, 2, 89.00);

-- ========================
-- 10. MÉTODOS DE PAGO
-- ========================
CREATE TABLE metodos_pago (
    idMetodoPago SERIAL PRIMARY KEY,
    idUsuario INTEGER NOT NULL REFERENCES usuarios(idUsuario),  -- Referencia actualizada
    tipo VARCHAR(50),
    numero_tarjeta VARCHAR(20),
    nombre_titular VARCHAR(100),
    fecha_expiracion DATE
);

INSERT INTO metodos_pago (idUsuario, tipo, numero_tarjeta, nombre_titular, fecha_expiracion) VALUES
(1, 'Tarjeta Crédito', '4111111111111111', 'Juan Pérez', '2027-08-31');