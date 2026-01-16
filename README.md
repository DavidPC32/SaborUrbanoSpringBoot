# Sabor Urbano - API REST

## 📋 Descripción del Proyecto

**Sabor Urbano** es una API REST desarrollada en **Spring Boot** que forma parte del **Momento 3** de mi proyecto escolar colaborativo. Esta aplicación permite gestionar información de un restaurante, incluyendo usuarios, platillos, categorías, comentarios y calificaciones.

La API está diseñada para integrarse con un proyecto complementario en **Python** que se encargará del análisis de datos.

## 🎯 Funcionalidades Principales

### Gestión de Usuarios
- Crear, obtener, listar y eliminar usuarios
- Validación de datos (email, nombre)
- Gestión de comentarios y calificaciones por usuario

### Gestión de Platillos
- Crear, obtener y listar platillos
- Clasificación por categorías
- Seguimiento de calificaciones y comentarios

### Gestión de Categorías
- Crear y obtener categorías de platillos
- Asociar platillos a categorías

### Comentarios y Reacciones
- Usuarios pueden comentar sobre platillos
- Sistema de reacciones en comentarios
- Calificaciones numéricas de platillos

### Calificaciones
- Sistema de puntuación de platillos (1-5)
- Comentarios cortos asociados a calificaciones
- Análisis de tendencias de preferencias

## 🏗️ Arquitectura

```
src/main/java/com/saborurbano/restaurante/
├── config/              # Configuración (OpenAPI/Swagger)
├── controller/          # Endpoints REST
├── dtos/               # Data Transfer Objects
├── mapper/             # Mapeo Entity ↔ DTO (MapStruct)
├── model/              # Entidades JPA
├── repository/         # Acceso a datos
├── service/            # Lógica de negocio
│   └── Usuario/
│       ├── UsuarioServiceInt.java
│       └── UsuarioServiceImp.java
└── RestauranteApplication.java
```

## 🔧 Stack Tecnológico

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MapStruct** - Mapeo de entidades
- **Lombok** - Reducción de código boilerplate
- **MySQL** - Base de datos
- **OpenAPI 3.0 / Swagger** - Documentación de API
- **Maven** - Gestor de dependencias

## 📦 Modelos de Datos

### Usuarios
```json
{
  "id": 1,
  "nombreCompleto": "Juan Pérez",
  "email": "juan@example.com"
}
```

### Platillos
```json
{
  "idPlatillo": 1,
  "nombre": "Pizza Margherita",
  "precio": 12.99,
  "categoria": {
    "idCategoria": 1,
    "nombreCategoria": "Pizzas"
  }
}
```

### Comentarios
```json
{
  "idComentario": 1,
  "textoComentario": "Excelente sabor",
  "fechaPublicacion": "2024-01-16T10:30:00",
  "usuario": {
    "id": 1,
    "nombreCompleto": "Juan Pérez",
    "email": "juan@example.com"
  }
}
```

### Calificaciones
```json
{
  "idCalificacion": 1,
  "puntuacion": 5,
  "comentarioCorto": "Muy bueno",
  "usuario": { ... },
  "platillo": { ... }
}
```

## 🚀 Endpoints Disponibles

### Usuarios
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios` | Obtener todos los usuarios |
| GET | `/api/usuarios/{id}` | Obtener un usuario por ID |
| POST | `/api/usuarios` | Crear un nuevo usuario |
| DELETE | `/api/usuarios/{id}` | Eliminar un usuario |

### Platillos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/platillos` | Obtener todos los platillos |
| POST | `/api/platillos` | Crear un nuevo platillo |

### Categorías
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/categorias` | Obtener todas las categorías |
| POST | `/api/categorias` | Crear una nueva categoría |

### Comentarios
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/comentarios` | Obtener todos los comentarios |
| POST | `/api/comentarios` | Crear un nuevo comentario |

### Calificaciones
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/calificaciones` | Obtener todas las calificaciones |
| POST | `/api/calificaciones` | Crear una nueva calificación |

## 🔗 Integración con Python

Esta API proporciona los endpoints necesarios para que un módulo Python realice:

- **Análisis de Sentimiento**: Procesamiento de comentarios para determinar opiniones positivas/negativas
- **Estadísticas**: Cálculo de promedios, tendencias y popularidad de platillos
- **Recomendaciones**: Sistemas de recomendación basados en calificaciones
- **Visualizaciones**: Generación de gráficos y reportes
- **Predicciones**: Modelos predictivos sobre preferencias futuras

## 📋 Requisitos Previos

- **JDK 17** o superior
- **Maven 3.8+**
- **MySQL 8.0+**
- **Git**

## 🛠️ Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone <url-repositorio>
cd SaborUrbanoSpringBoot
```

### 2. Configurar la base de datos
Editar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sabor_urbano
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### 3. Compilar el proyecto
```bash
./mvnw clean compile
```

### 4. Ejecutar la aplicación
```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## 📚 Documentación de API

Una vez que la aplicación está corriendo, accede a la documentación interactiva de Swagger:

```
http://localhost:8080/swagger-ui.html
```

## 👥 Compañeros de Trabajo

Este proyecto fue desarrollado colaborativamente por el siguiente equipo:

| Nombre | Rol | Responsabilidades |
|--------|-----|-------------------|
| **Omar Salcedo**|
| **Yorman**|
| **David R Pinzón**|


## 📋 Estructura de DTOs Simplificada

Para mantener la claridad en las peticiones, hemos simplificado los DTOs eliminando redundancias:

- **UsuarioDto**: Información básica del usuario
- **CategoriaBasicoDto**: Información de categorías
- **PlatilloDto**: Información de platillos con categoría
- **ComentarioDto**: Comentarios con usuario asociado
- **CalificacionPlatilloDto**: Calificaciones con usuario y platillo
- **ReaccionComentarioDto**: Reacciones a comentarios

## 🔐 Validaciones

Los DTOs incluyen validaciones automáticas:
- **Email**: Formato válido de correo electrónico
- **Nombre**: Mínimo 3 caracteres, máximo 100
- **Obligatorios**: Los campos requeridos están anotados con `@NotEmpty`

## 📅 Historial de Cambios

### Versión 1.0.0 (Inicial)
- ✅ Creación de endpoints de Usuarios, Platillos y Categorías
- ✅ Implementación de comentarios y calificaciones
- ✅ Eliminación de redundancias en DTOs
- ✅ Documentación con Swagger/OpenAPI
- ✅ Validación de datos en DTOs

