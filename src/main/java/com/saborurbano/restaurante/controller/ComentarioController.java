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

import com.saborurbano.restaurante.dtos.ComentarioDto;
import com.saborurbano.restaurante.service.Comentario.ComentarioServiceImp;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RequestMapping("api/comentarios")
@RestController
@Tag(name = "Comentarios", description = "Endpoint para gestionar los comentarios")
public class ComentarioController {

    private final ComentarioServiceImp comentarioServiceImp;

    public ComentarioController(ComentarioServiceImp comentarioServiceImp) {
        this.comentarioServiceImp = comentarioServiceImp;
    }

    @Operation(summary = "Obtener todos los comentarios", description = "Devuelve una lista de comentarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de comentarios"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ComentarioDto>> getAllComentarios() {
        return ResponseEntity.ok(comentarioServiceImp.getAllComentarios());
    }

    @Operation(summary = "Obtener comentarios por usuario", description = "Devuelve todos los comentarios de un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de comentarios del usuario"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ComentarioDto>> getComentariosByUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(comentarioServiceImp.getComentariosByUsuario(idUsuario));
    }

    @Operation(summary = "Crear un comentario", description = "Crea un nuevo comentario con el idUsuario especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/{idUsuario}")
    public ResponseEntity<ComentarioDto> crearComentario(@Valid @RequestBody ComentarioDto comentario,
            @PathVariable Integer idUsuario) {
        ComentarioDto nuevoComentario = comentarioServiceImp.registrarComentario(comentario, idUsuario);
        return new ResponseEntity<>(nuevoComentario, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un comentario", description = "Elimina un comentario con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentario eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id) {
        comentarioServiceImp.deleteComentario(id);
        return ResponseEntity.ok().build();
    }

}
