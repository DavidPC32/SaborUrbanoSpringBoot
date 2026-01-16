package com.saborurbano.restaurante.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saborurbano.restaurante.service.Usuario.UsuarioServiceImp;
import com.saborurbano.restaurante.dtos.UsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RequestMapping("api/usuarios")
@RestController
@Tag(name = "Usuarios", description = "Endpoint para gestionar usuarios")
public class UsuariosController {

    private final UsuarioServiceImp usuarioServiceImp;

    public UsuariosController (UsuarioServiceImp usuarioServiceImp) {
        this.usuarioServiceImp = usuarioServiceImp;
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Devuelve una lista de usuarios"),
        @ApiResponse(responseCode = "400", description = "Error del cliente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioServiceImp.getAllUsuarios());
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve un usuario específico por su ID")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioServiceImp.getUsuarioId(id));
    }

    @Operation(summary = "Crear un usuario", description = "Crea un nuevo usuario")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error del cliente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@Valid @RequestBody UsuarioDto dto) {
        UsuarioDto nuevoUsuario = usuarioServiceImp.registrarUsuarios(dto);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario con el id especificado")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarios(@PathVariable Integer id) {
        usuarioServiceImp.deleteUsuarios(id);
        return ResponseEntity.noContent().build();
    }

}
