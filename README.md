# Sabor Urbano - API REST

## 📋 Descripción del Proyecto

**Sabor Urbano** es una API REST desarrollada en **Spring Boot** que forma parte del **Momento 3** de mi proyecto escolar colaborativo. Esta aplicación permite gestionar información de un restaurante, incluyendo usuarios, platillos, categorías, comentarios y calificaciones.

La API está diseñada para integrarse con un proyecto complementario en **Python** que se encargará del análisis de datos para esto se deben rellenar la base de datos con el sql que esta en el archivo BASE_DE_DATOS.MD .

## 🎯 Funcionalidades Principales

### 👤 Gestión de Usuarios

- Registro y consulta de usuarios.
- Historial de actividad (comentarios y calificaciones).
- Eliminar usuarios por el ID

### 🍽️ Gestión de Menú

- **Platillos**: Administración completa (crear, listar, eliminar) con precios y categorías.
- **Categorías**: Organización del menú (ej: Entradas, Platos Fuertes, Postres).

### 💬 Interacción y Feedback

- **Calificaciones**: Sistema de 1 a 5 estrellas con comentarios cortos.
- **Comentarios**: Los usuarios pueden dejar opiniones detalladas en los platillos.
- **Reacciones**: "Me gusta" o interacciones en los comentarios de otros usuarios.

---

## 🛠️ Puesta en Marcha (Paso a Paso)

Sigue estos 3 pasos sencillos para iniciar el proyecto:

### 1. Requisitos Previos

Asegúrate de tener instalado:

- **Java JDK 17** o superior.
- **SQL Server** (Base de datos).
- **Git** (Opcional, para clonar).

### 2. Configurar Base de Datos

1. Crea una base de datos vacía en SQL Server llamada `restauranteSaborUrbano`.
2. Abre el archivo `src/main/resources/application.properties`.
3. Verifica/Actualiza tus credenciales:
   ```properties
   spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=restauranteSaborUrbano;TrustServerCertificate=true;
   spring.datasource.username=TU_USUARIO_SQL  (ej: sa)
   spring.datasource.password=TU_CONTRASEÑA   (ej: 123456)
   ```
4. Llena los datos en tu base de datos al Ejecutar el sql del archivo BASE_DE_DATOS.MD

### 3. Ejecutar la Aplicación

Abre una terminal en la carpeta del proyecto y ejecuta:

**En Windows:**

```powershell
./mvnw spring-boot:run
```

**En Mac/Linux:**

```bash
./mvnw spring-boot:run
```

¡Listo! 🚀 La aplicación iniciará en el puerto **8080**.

---

## 📚 Documentación API

Una vez iniciada la app, explora y prueba todos los endpoints aquí:
👉 **[http://localhost:8080/documentacion](http://localhost:8080/documentacion)**

---

## 👥 Equipo de Desarrollo

| Integrante         | Rol                    |
| ------------------ | ---------------------- |
| **Omar Salcedo**   | Backend & Database     |
| **Yorman**         | Backend & Arquitectura |
| **David R Pinzón** | Backend & Testing      |

---

_Proyecto educativo para la materia de Programación Back end II - Momento 3_
