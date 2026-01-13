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

import com.saborurbano.restaurante.model.Usuarios;
import com.saborurbano.restaurante.service.Usuario.UsuarioServiceImp;

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
    public List<Usuarios> getAllUsuarios() {
        return usuarioServiceImp.getAllUsuarios();
    }



    @Operation(summary = "Crear un usuario", description = "Crea un nuevo usuario")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error del cliente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<Usuarios> createUsuario(@Valid @RequestBody Usuarios usuarios) {
        Usuarios  nuevoUsuario = usuarioServiceImp.registrarUsuarios(usuarios);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario con el id especificado")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error del cliente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarios(@PathVariable Integer id, Usuarios usuarios) {
        usuarioServiceImp.deleteUsuarios(id, usuarios);
        return ResponseEntity.noContent().build();
    }

    
}
