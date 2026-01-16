# REQUIREMENTS.md

## Requisitos del Proyecto Sabor Urbano - API REST

### 🖥️ Requisitos de Sistema

#### Mínimos
- **SO**: Windows 10, macOS 10.14+, o Linux (cualquier distribución moderna)
- **RAM**: 4 GB
- **Almacenamiento**: 2 GB de espacio libre

#### Recomendados
- **SO**: Windows 11, macOS Ventura+, o Ubuntu 20.04+
- **RAM**: 8 GB o más
- **Almacenamiento**: 5 GB de espacio libre
- **Procesador**: Intel i5/i7 o equivalente AMD

---

## 📦 Software Obligatorio

### 1. Java Development Kit (JDK)

**Versión Requerida**: JDK 17 o superior

#### Instalación en Windows
```bash
# Opción 1: Usar Chocolatey (si lo tienes instalado)
choco install openjdk17

# Opción 2: Descargar de Oracle
# https://www.oracle.com/java/technologies/downloads/#java17
```

#### Instalación en macOS
```bash
# Usar Homebrew
brew install openjdk@17
```

#### Instalación en Linux (Ubuntu)
```bash
sudo apt update
sudo apt install openjdk-17-jdk openjdk-17-jre
```

#### Verificar instalación
```bash
java -version
javac -version
```

Deberías ver algo como:
```
openjdk version "17.x.x"
```

---

### 2. Maven

**Versión Requerida**: 3.8.0 o superior

El proyecto incluye **Maven Wrapper** (`mvnw`), por lo que no es obligatorio instalar Maven globalmente.

#### Instalación Global (Opcional)

**Windows**:
```bash
# Descargar de https://maven.apache.org/download.cgi
# Extraer en una carpeta y agregar a PATH
```

**macOS**:
```bash
brew install maven
```

**Linux**:
```bash
sudo apt install maven
```

#### Verificar instalación
```bash
mvn -version
```

---

### 3. MySQL

**Versión Requerida**: 8.0 o superior

#### Instalación en Windows
```bash
# Opción 1: Descargar instalador de https://dev.mysql.com/downloads/mysql/
# Opción 2: Usar Chocolatey
choco install mysql
```

#### Instalación en macOS
```bash
brew install mysql
brew services start mysql
```

#### Instalación en Linux (Ubuntu)
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

#### Verificar instalación
```bash
mysql --version
```

---

### 4. Git

**Versión Requerida**: 2.30 o superior

#### Instalación en Windows
```bash
# Descargar de https://git-scm.com/download/win
# O usar Chocolatey
choco install git
```

#### Instalación en macOS
```bash
brew install git
```

#### Instalación en Linux (Ubuntu)
```bash
sudo apt install git
```

#### Verificar instalación
```bash
git --version
```

---

## 🛠️ Software Recomendado (Opcional)

### IDE / Editor de Código

#### IntelliJ IDEA (Recomendado)
- **Versión**: Community Edition o Ultimate
- **Descarga**: https://www.jetbrains.com/idea/download/

#### Visual Studio Code
- **Descarga**: https://code.visualstudio.com/
- **Extensiones necesarias**:
  - Extension Pack for Java
  - Spring Boot Extension Pack
  - REST Client

#### Spring Tool Suite (STS)
- **Descarga**: https://spring.io/tools
- IDE basada en Eclipse optimizada para Spring

---

### Herramientas Auxiliares

#### Postman (Para testing de endpoints)
```bash
# Descargar de https://www.postman.com/downloads/
# Importar colección de requests incluida en el proyecto
```

#### DBeaver (Para gestionar MySQL)
```bash
# Descargar de https://dbeaver.io/download/
```

#### MongoDB Compass (Si se agrega MongoDB)
```bash
# Descargar de https://www.mongodb.com/products/compass
```

---

## 📋 Dependencias del Proyecto (Maven)

Las siguientes dependencias se descargan automáticamente:

### Core Spring
- `spring-boot-starter-web` - Web y REST
- `spring-boot-starter-data-jpa` - Acceso a datos
- `spring-boot-starter-validation` - Validación

### Base de Datos
- `mysql-connector-java` - Driver MySQL
- `spring-boot-starter-jdbc` - JDBC

### Mapeo de Datos
- `mapstruct` - Mapeo Entity ↔ DTO
- `mapstruct-processor` - Procesador de anotaciones

### Utilidades
- `lombok` - Reduce código boilerplate
- `lombok-mapstruct-binding` - Integración Lombok + MapStruct

### Documentación
- `springdoc-openapi-starter-webmvc-ui` - Swagger UI

### Testing
- `spring-boot-starter-test` - JUnit, Mockito, etc.

---

## 🚀 Verificación Completa del Ambiente

Ejecuta este script para verificar que todo está instalado:

### Windows (PowerShell)
```powershell
Write-Host "Java:" (java -version 2>&1)[0]
Write-Host "Maven:" (mvn -version 2>&1)[0]
Write-Host "Git:" (git --version)
Write-Host "MySQL:" (mysql --version)
```

### macOS / Linux (Bash)
```bash
echo "Java:"
java -version

echo -e "\nMaven:"
mvn -version

echo -e "\nGit:"
git --version

echo -e "\nMySQL:"
mysql --version
```

---

## 📝 Setup Inicial Después de Clonar

```bash
# 1. Clonar repositorio
git clone <URL_REPOSITORIO>
cd SaborUrbanoSpringBoot

# 2. Descargar dependencias (primero)
./mvnw clean install

# 3. Crear base de datos MySQL
mysql -u root -p < script_database.sql
# O crear manualmente:
# CREATE DATABASE sabor_urbano;

# 4. Configurar application.properties
cp application.properties.example src/main/resources/application.properties
# Editar y agregar credenciales MySQL

# 5. Ejecutar aplicación
./mvnw spring-boot:run

# 6. Acceder a Swagger UI
# http://localhost:8080/swagger-ui.html
```

---

## 🔍 Troubleshooting

### Error: "No se encuentra el comando java"
- Verifica que Java está instalado: `java -version`
- Agrega Java al PATH del sistema

### Error: "Maven wrapper no funciona"
- En Windows: Abre PowerShell como Administrador
- En Linux/Mac: `chmod +x mvnw`

### Error: "Conexión MySQL denegada"
- Verifica que MySQL está corriendo
- Verifica usuario/contraseña en application.properties

### Error: "Puerto 8080 ya está en uso"
- Cambia el puerto en application.properties: `server.port=8081`

---

## ✅ Checklist de Instalación

- [ ] JDK 17+ instalado
- [ ] Maven 3.8+ instalado (o Maven Wrapper disponible)
- [ ] MySQL 8.0+ instalado y ejecutándose
- [ ] Git instalado
- [ ] IDE instalado (opcional pero recomendado)
- [ ] Repositorio clonado
- [ ] Dependencias descargadas (`./mvnw clean install`)
- [ ] Base de datos creada
- [ ] application.properties configurado
- [ ] Aplicación se ejecuta sin errores

---

¡Una vez completados estos requisitos, estás listo para desarrollar! 🚀
