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

import com.saborurbano.restaurante.dtos.ReaccionComentarioDto;
import com.saborurbano.restaurante.service.ReaccionComentario.ReaccionComentarioServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("api/reacciones-comentarios")
@RestController
@Tag(name = "Reacciones Comentarios", description = "Endpoint para gestionar reacciones de comentarios")
public class ReaccionComentarioController {

    private final ReaccionComentarioServiceImp reaccionComentarioServiceImp;

    public ReaccionComentarioController(ReaccionComentarioServiceImp reaccionComentarioServiceImp) {
        this.reaccionComentarioServiceImp = reaccionComentarioServiceImp;
    }

    @Operation(summary = "Obtener todas las reacciones de comentarios", description = "Devuelve una lista de todas las reacciones de comentarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de reacciones de comentarios"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<ReaccionComentarioDto>> getAllReacciones() {
        return ResponseEntity.ok(reaccionComentarioServiceImp.getAllReacciones());
    }

    @Operation(summary = "Obtener una reacción de comentario por su id", description = "Devuelve una reacción de comentario con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una reacción de comentario con el id especificado"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReaccionComentarioDto> getReaccionById(@PathVariable Long id) {
        return ResponseEntity.ok(reaccionComentarioServiceImp.getReaccionById(id));
    }

    @Operation(summary = "Crear una reacción de comentario", description = "Crea una nueva reacción de comentario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reacción de comentario creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/{idUsuario}/{idComentario}")
    public ResponseEntity<ReaccionComentarioDto> createReaccion(
            @RequestBody ReaccionComentarioDto reaccion,
            @PathVariable Integer idUsuario,
            @PathVariable Integer idComentario) {
        ReaccionComentarioDto nuevaReaccion = reaccionComentarioServiceImp.registrarReaccion(reaccion, idUsuario,
                idComentario);
        return new ResponseEntity<>(nuevaReaccion, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener reacciones de comentarios por usuario", description = "Devuelve todas las reacciones hechas por un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de reacciones del usuario"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReaccionComentarioDto>> getReaccionesByUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(reaccionComentarioServiceImp.getReaccionByUsuarioId(idUsuario));
    }

    @Operation(summary = "Eliminar una reacción de comentario", description = "Elimina una reacción de comentario con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reacción de comentario eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReaccion(@PathVariable Long id) {
        reaccionComentarioServiceImp.deleteReaccion(id);
        return ResponseEntity.noContent().build();
    }
}
