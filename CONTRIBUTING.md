# CONTRIBUTING.md

## Guía de Contribución - Sabor Urbano API

Agradecemos tu interés en contribuir al proyecto Sabor Urbano. Esta guía te ayudará a entender cómo trabajar con el código y hacer contribuciones significativas.

## 🎯 Objetivos del Proyecto

Este es un proyecto educativo de **Momento 3** que busca:
- Aprender arquitectura de microservicios
- Implementar patrones de diseño REST
- Integrar APIs con aplicaciones Python
- Practicar trabajo en equipo en desarrollo

## 📝 Convenciones de Código

### Nombrado de Clases
- **Controllers**: `<Recurso>Controller.java` (ej: `UsuariosController.java`)
- **Services**: `<Recurso>Service<Int/Imp>.java` (ej: `UsuarioServiceInt.java`, `UsuarioServiceImp.java`)
- **DTOs**: `<Recurso>Dto.java` (ej: `UsuarioDto.java`)
- **Mappers**: `<Recurso>Mapper.java` (ej: `UsuarioMapper.java`)
- **Repositories**: `<Recurso>Repository.java` (ej: `UsuariosRepository.java`)
- **Models**: `<Recurso>.java` (ej: `Usuarios.java`)

### Paquetes
```
com.saborurbano.restaurante
├── config/           # Configuraciones (Swagger, etc)
├── controller/       # Controladores REST
├── dtos/            # Data Transfer Objects
├── mapper/          # MapStruct Mappers
├── model/           # Entidades JPA
├── repository/      # Spring Data Repositories
└── service/         # Lógica de negocio
    ├── Usuario/
    ├── Platillo/
    ├── Categoria/
    ├── Comentario/
    ├── CalificacionPlatillo/
    └── ReaccionComentario/
```

## 🔄 Flujo de Trabajo

### 1. Crear una rama feature
```bash
git checkout -b feature/descripcion-corta
```

Ejemplos:
- `feature/usuario-update-endpoint`
- `feature/platillo-validation`
- `feature/comentario-service`

### 2. Hacer cambios
Sigue las convenciones de código y estructura del proyecto.

### 3. Testear localmente
```bash
./mvnw clean test
./mvnw spring-boot:run
```

### 4. Commit con mensajes claros
```bash
git commit -m "Add: descripción clara del cambio"
git commit -m "Fix: descripción del bug corregido"
git commit -m "Refactor: descripción de la mejora"
```

### 5. Push y Pull Request
```bash
git push origin feature/descripcion-corta
```

Luego abre un PR en GitHub con descripción clara de los cambios.

## 📊 Estructura de Commits

Usa prefijos claros para clasificar tus commits:

- **Add**: Nueva funcionalidad
- **Fix**: Corrección de bugs
- **Refactor**: Mejora de código existente
- **Docs**: Cambios en documentación
- **Test**: Agregación o mejora de tests
- **Chore**: Tareas administrativas

Ejemplo:
```bash
git commit -m "Add: GET endpoint para obtener usuario por ID"
git commit -m "Fix: validación de email en UsuarioDto"
git commit -m "Refactor: simplificación de DTOs eliminando redundancias"
```

## 🏗️ Agregar un Nuevo Recurso

Si necesitas agregar un nuevo recurso (ej: Reservas), sigue este patrón:

### 1. Crear el Model (JPA Entity)
```java
// src/main/java/.../model/Reserva.java
@Entity
@Table(name = "Reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    
    // propiedades
}
```

### 2. Crear el DTO
```java
// src/main/java/.../dtos/ReservaDto.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {
    private Long idReserva;
    // propiedades
}
```

### 3. Crear el Repository
```java
// src/main/java/.../repository/ReservaRepository.java
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
```

### 4. Crear el Mapper
```java
// src/main/java/.../mapper/ReservaMapper.java
@Mapper(componentModel = "spring")
public interface ReservaMapper {
    ReservaDto toDTO(Reserva reserva);
    Reserva toEntity(ReservaDto reservaDto);
    List<ReservaDto> toDto(List<Reserva> reservas);
}
```

### 5. Crear la Interfaz del Servicio
```java
// src/main/java/.../service/Reserva/ReservaServiceInt.java
public interface ReservaServiceInt {
    ReservaDto crearReserva(ReservaDto dto);
    List<ReservaDto> obtenerTodas();
    ReservaDto obtenerPorId(Long id);
    void eliminar(Long id);
}
```

### 6. Implementar el Servicio
```java
// src/main/java/.../service/Reserva/ReservaServiceImp.java
@Service
public class ReservaServiceImp implements ReservaServiceInt {
    // implementación
}
```

### 7. Crear el Controller
```java
// src/main/java/.../controller/ReservaController.java
@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Gestión de reservas")
public class ReservaController {
    // endpoints
}
```

## ✅ Checklist Antes de Hacer Push

- [ ] Código sigue las convenciones del proyecto
- [ ] No hay warnings de compilación
- [ ] Todos los tests pasan (`./mvnw test`)
- [ ] Documentación está actualizada si aplica
- [ ] DTOs tienen validaciones necesarias
- [ ] El servicio sigue la interfaz + implementación
- [ ] El controller tiene anotaciones Swagger/OpenAPI
- [ ] No hay código duplicado
- [ ] Mensaje de commit es claro y descriptivo

## 🐞 Reporte de Issues

Cuando reportes un problema, incluye:

```markdown
## Descripción
[Descripción clara del problema]

## Pasos para Reproducir
1. [Paso 1]
2. [Paso 2]
3. [Paso 3]

## Comportamiento Esperado
[Qué debería pasar]

## Comportamiento Actual
[Qué está pasando]

## Ambiente
- Java: [Versión]
- Spring Boot: [Versión]
- OS: [Sistema Operativo]

## Capturas de Pantalla
[Si aplica]
```

## 🧪 Testing

Para nuevas funcionalidades, escribe tests:

```bash
./mvnw test
```

Los tests deben estar en `src/test/java/`

## 📚 Integración Python

Si tu cambio afecta la integración con Python, asegúrate de:

1. Documentar los cambios en el endpoint
2. Mantener la compatibilidad con versiones anteriores cuando sea posible
3. Informar al equipo Python sobre cambios importantes
4. Proporcionar ejemplos de request/response

## ❓ Preguntas

Si tienes preguntas:
1. Revisa la documentación existente
2. Pregunta en los issues
3. Contacta al equipo de desarrollo

---

¡Gracias por contribuir a Sabor Urbano! 🍕
