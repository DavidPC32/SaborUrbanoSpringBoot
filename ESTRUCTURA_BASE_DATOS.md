# ESTRUCTURA_BASE_DATOS.md

## 📊 Estructura de Base de Datos - Sabor Urbano

Esta documentación describe la estructura de la base de datos MySQL para el proyecto Sabor Urbano.

---

## 🔑 Diagrama de Relaciones

```
Usuarios (1) ──→ (∞) Comentarios
    ↑                     ↓
    └─── (1) ──→ (∞) Reacciones Comentario
    
Usuarios (1) ──→ (∞) CalificacionPlatillo

Platillo (∞) ←── (1) Categoria
    ↓
    └─── (∞) CalificacionPlatillo
    └─── (∞) Comentarios
```

---

## 📋 Tablas

### 1. Tabla: `usuarios`

Almacena información de los usuarios del restaurante.

```sql
CREATE TABLE Usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombreCompleto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

**Campos:**
| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| id | INT | PK, AUTO_INCREMENT | Identificador único |
| nombreCompleto | VARCHAR(100) | NOT NULL | Nombre completo del usuario |
| email | VARCHAR(100) | NOT NULL, UNIQUE | Email único |
| CREATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Fecha de creación |
| UPDATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Última actualización |

**Índices:**
```sql
CREATE INDEX idx_usuarios_email ON Usuarios(email);
```

---

### 2. Tabla: `categoria`

Almacena las categorías de platillos.

```sql
CREATE TABLE Categoria (
    idCategoria BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombreCategoria VARCHAR(100) NOT NULL UNIQUE,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Campos:**
| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| idCategoria | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| nombreCategoria | VARCHAR(100) | NOT NULL, UNIQUE | Nombre de categoría |
| CREATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Fecha de creación |

**Índices:**
```sql
CREATE INDEX idx_categoria_nombre ON Categoria(nombreCategoria);
```

---

### 3. Tabla: `platillo`

Almacena información de los platillos del restaurante.

```sql
CREATE TABLE Platillo (
    idPlatillo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    precio DOUBLE NOT NULL,
    id_categoria BIGINT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(idCategoria)
);
```

**Campos:**
| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| idPlatillo | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| nombre | VARCHAR(100) | NOT NULL, UNIQUE | Nombre del platillo |
| precio | DOUBLE | NOT NULL | Precio del platillo |
| id_categoria | BIGINT | FK | Referencia a categoría |
| CREATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Fecha de creación |
| UPDATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Última actualización |

**Índices y Constraints:**
```sql
CREATE INDEX idx_platillo_nombre ON Platillo(nombre);
CREATE INDEX idx_platillo_categoria ON Platillo(id_categoria);
```

---

### 4. Tabla: `comentario`

Almacena comentarios de usuarios sobre platillos.

```sql
CREATE TABLE Comentario (
    idComentario INT PRIMARY KEY AUTO_INCREMENT,
    textoComentario TEXT,
    fechaPublicacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id) ON DELETE CASCADE
);
```

**Campos:**
| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| idComentario | INT | PK, AUTO_INCREMENT | Identificador único |
| textoComentario | TEXT | - | Contenido del comentario |
| fechaPublicacion | DATETIME | NOT NULL | Fecha/hora de publicación |
| id_usuario | INT | FK | Referencia al usuario |
| CREATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Fecha de creación |

**Índices:**
```sql
CREATE INDEX idx_comentario_usuario ON Comentario(id_usuario);
CREATE INDEX idx_comentario_fecha ON Comentario(fechaPublicacion);
```

---

### 5. Tabla: `calificacion_platillo`

Almacena las calificaciones que hacen los usuarios a los platillos.

```sql
CREATE TABLE CalificacionPlatillo (
    idCalificacion BIGINT PRIMARY KEY AUTO_INCREMENT,
    puntuacion INT NOT NULL CHECK (puntuacion >= 1 AND puntuacion <= 5),
    comentarioCorto VARCHAR(255),
    id_usuario INT NOT NULL,
    id_platillo BIGINT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (id_platillo) REFERENCES Platillo(idPlatillo) ON DELETE CASCADE
);
```

**Campos:**
| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| idCalificacion | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| puntuacion | INT | CHECK (1-5) | Calificación del 1 al 5 |
| comentarioCorto | VARCHAR(255) | - | Comentario breve |
| id_usuario | INT | FK | Referencia al usuario |
| id_platillo | BIGINT | FK | Referencia al platillo |
| CREATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Fecha de creación |
| UPDATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Última actualización |

**Índices:**
```sql
CREATE INDEX idx_calificacion_usuario ON CalificacionPlatillo(id_usuario);
CREATE INDEX idx_calificacion_platillo ON CalificacionPlatillo(id_platillo);
CREATE UNIQUE INDEX idx_calificacion_unica ON CalificacionPlatillo(id_usuario, id_platillo);
```

---

### 6. Tabla: `reaccion_comentario`

Almacena las reacciones (likes, dislikes, etc.) a los comentarios.

```sql
CREATE TABLE ReaccionComentario (
    idReaccion BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipoReaccion VARCHAR(50) NOT NULL,
    id_usuario INT NOT NULL,
    id_comentario INT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (id_comentario) REFERENCES Comentario(idComentario) ON DELETE CASCADE
);
```

**Campos:**
| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| idReaccion | BIGINT | PK, AUTO_INCREMENT | Identificador único |
| tipoReaccion | VARCHAR(50) | NOT NULL | Tipo (like, love, etc.) |
| id_usuario | INT | FK | Referencia al usuario |
| id_comentario | INT | FK | Referencia al comentario |
| CREATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Fecha de creación |

**Índices:**
```sql
CREATE INDEX idx_reaccion_usuario ON ReaccionComentario(id_usuario);
CREATE INDEX idx_reaccion_comentario ON ReaccionComentario(id_comentario);
CREATE UNIQUE INDEX idx_reaccion_unica ON ReaccionComentario(id_usuario, id_comentario);
```

---

## 🔄 Relaciones

### Usuarios → Comentarios
- **Tipo**: One-to-Many (1:∞)
- **FK**: `Comentario.id_usuario` → `Usuarios.id`
- **Cascada**: ON DELETE CASCADE
- **Descripción**: Un usuario puede hacer muchos comentarios

### Usuarios → CalificacionPlatillo
- **Tipo**: One-to-Many (1:∞)
- **FK**: `CalificacionPlatillo.id_usuario` → `Usuarios.id`
- **Cascada**: ON DELETE CASCADE
- **Descripción**: Un usuario puede calificar muchos platillos

### Usuarios → ReaccionComentario
- **Tipo**: One-to-Many (1:∞)
- **FK**: `ReaccionComentario.id_usuario` → `Usuarios.id`
- **Cascada**: ON DELETE CASCADE
- **Descripción**: Un usuario puede reaccionar a muchos comentarios

### Categoria → Platillo
- **Tipo**: One-to-Many (1:∞)
- **FK**: `Platillo.id_categoria` → `Categoria.idCategoria`
- **Cascada**: ON DELETE RESTRICT
- **Descripción**: Una categoría contiene muchos platillos

### Platillo → CalificacionPlatillo
- **Tipo**: One-to-Many (1:∞)
- **FK**: `CalificacionPlatillo.id_platillo` → `Platillo.idPlatillo`
- **Cascada**: ON DELETE CASCADE
- **Descripción**: Un platillo puede tener muchas calificaciones

### Comentario → ReaccionComentario
- **Tipo**: One-to-Many (1:∞)
- **FK**: `ReaccionComentario.id_comentario` → `Comentario.idComentario`
- **Cascada**: ON DELETE CASCADE
- **Descripción**: Un comentario puede tener muchas reacciones

---

## 📊 Restricciones de Datos

### CHECK Constraints
```sql
-- Calificaciones solo pueden ser 1-5
ALTER TABLE CalificacionPlatillo 
ADD CONSTRAINT chk_puntuacion 
CHECK (puntuacion >= 1 AND puntuacion <= 5);
```

### UNIQUE Constraints
```sql
-- Un usuario solo puede calificar un platillo una vez
ALTER TABLE CalificacionPlatillo 
ADD UNIQUE KEY uk_usuario_platillo (id_usuario, id_platillo);

-- Un usuario solo puede hacer una reacción por comentario
ALTER TABLE ReaccionComentario 
ADD UNIQUE KEY uk_usuario_comentario (id_usuario, id_comentario);
```

---

## 📈 Script de Inicialización Completo

```sql
-- Crear base de datos
CREATE DATABASE IF NOT EXISTS sabor_urbano;
USE sabor_urbano;

-- Tabla Usuarios
CREATE TABLE Usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombreCompleto VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
);

-- Tabla Categoria
CREATE TABLE Categoria (
    idCategoria BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombreCategoria VARCHAR(100) NOT NULL UNIQUE,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_nombre (nombreCategoria)
);

-- Tabla Platillo
CREATE TABLE Platillo (
    idPlatillo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    precio DOUBLE NOT NULL,
    id_categoria BIGINT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(idCategoria),
    INDEX idx_nombre (nombre),
    INDEX idx_categoria (id_categoria)
);

-- Tabla Comentario
CREATE TABLE Comentario (
    idComentario INT PRIMARY KEY AUTO_INCREMENT,
    textoComentario TEXT,
    fechaPublicacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id) ON DELETE CASCADE,
    INDEX idx_usuario (id_usuario),
    INDEX idx_fecha (fechaPublicacion)
);

-- Tabla CalificacionPlatillo
CREATE TABLE CalificacionPlatillo (
    idCalificacion BIGINT PRIMARY KEY AUTO_INCREMENT,
    puntuacion INT NOT NULL,
    comentarioCorto VARCHAR(255),
    id_usuario INT NOT NULL,
    id_platillo BIGINT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (id_platillo) REFERENCES Platillo(idPlatillo) ON DELETE CASCADE,
    CHECK (puntuacion >= 1 AND puntuacion <= 5),
    UNIQUE KEY uk_usuario_platillo (id_usuario, id_platillo),
    INDEX idx_usuario (id_usuario),
    INDEX idx_platillo (id_platillo)
);

-- Tabla ReaccionComentario
CREATE TABLE ReaccionComentario (
    idReaccion BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipoReaccion VARCHAR(50) NOT NULL,
    id_usuario INT NOT NULL,
    id_comentario INT NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (id_comentario) REFERENCES Comentario(idComentario) ON DELETE CASCADE,
    UNIQUE KEY uk_usuario_comentario (id_usuario, id_comentario),
    INDEX idx_usuario (id_usuario),
    INDEX idx_comentario (id_comentario)
);
```

---

## 🔍 Queries Útiles

### Platillos más calificados
```sql
SELECT 
    p.nombre,
    ROUND(AVG(cp.puntuacion), 2) as promedio,
    COUNT(cp.idCalificacion) as total_calificaciones
FROM Platillo p
LEFT JOIN CalificacionPlatillo cp ON p.idPlatillo = cp.id_platillo
GROUP BY p.idPlatillo
ORDER BY promedio DESC;
```

### Usuarios más activos
```sql
SELECT 
    u.nombreCompleto,
    COUNT(DISTINCT c.idComentario) as comentarios,
    COUNT(DISTINCT cp.idCalificacion) as calificaciones,
    COUNT(DISTINCT c.idComentario) + COUNT(DISTINCT cp.idCalificacion) as total
FROM Usuarios u
LEFT JOIN Comentario c ON u.id = c.id_usuario
LEFT JOIN CalificacionPlatillo cp ON u.id = cp.id_usuario
GROUP BY u.id
ORDER BY total DESC;
```

### Categorías con mejor promedio
```sql
SELECT 
    cat.nombreCategoria,
    ROUND(AVG(cp.puntuacion), 2) as promedio_categoria,
    COUNT(cp.idCalificacion) as total_votos
FROM Categoria cat
JOIN Platillo p ON cat.idCategoria = p.id_categoria
LEFT JOIN CalificacionPlatillo cp ON p.idPlatillo = cp.id_platillo
GROUP BY cat.idCategoria
ORDER BY promedio_categoria DESC;
```

### Últimos comentarios
```sql
SELECT 
    c.textoComentario,
    u.nombreCompleto,
    c.fechaPublicacion,
    COUNT(rc.idReaccion) as reacciones
FROM Comentario c
JOIN Usuarios u ON c.id_usuario = u.id
LEFT JOIN ReaccionComentario rc ON c.idComentario = rc.id_comentario
ORDER BY c.fechaPublicacion DESC
LIMIT 10;
```

---

## 🚀 Optimizaciones Realizadas

✅ Índices en campos de búsqueda frecuente  
✅ Relaciones con ON DELETE CASCADE para eliminar registros relacionados  
✅ UNIQUE constraints para evitar duplicados  
✅ CHECK constraints para validar datos  
✅ Timestamps para auditoria  

---

¡Base de datos lista para usar! 📊
