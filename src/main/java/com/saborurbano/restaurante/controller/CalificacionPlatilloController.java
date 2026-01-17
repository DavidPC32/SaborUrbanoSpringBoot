package com.saborurbano.restaurante.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;

import com.saborurbano.restaurante.dtos.CalificacionPlatilloDto;
import com.saborurbano.restaurante.service.CalificacionPlatillo.CalificacionPlatilloServiceImp;

@RequestMapping("api/calificaciones")
@RestController
@Tag(name = "Calificaciones", description = "Endpoint para gestionar las calificaciones")
public class CalificacionPlatilloController {

    private final CalificacionPlatilloServiceImp calificacionServiceImp;

    public CalificacionPlatilloController(CalificacionPlatilloServiceImp calificacionServiceImp) {
        this.calificacionServiceImp = calificacionServiceImp;
    }

    @Operation(summary = "Obtener todas las calificaciones", description = "Devuelve una lista de todas las calificaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de calificaciones"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<CalificacionPlatilloDto>> getAllCalificaciones() {
        return ResponseEntity.ok(calificacionServiceImp.getAllCalificaciones());
    }

    @Operation(summary = "Obtener una calificacion por su id", description = "Devuelve una calificacion con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una calificacion con el id especificado"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CalificacionPlatilloDto> getCalificacionById(@PathVariable Long id) {
        return ResponseEntity.ok(calificacionServiceImp.getCalificacionById(id));
    }

    @Operation(summary = "Crear una calificacion", description = "Crea una nueva calificacion con un idUsuario y un idPlatillo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Calificacion creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/{idUsuario}/{idPlatillo}")
    public ResponseEntity<CalificacionPlatilloDto> crearCalificacion(
            @RequestBody CalificacionPlatilloDto calificacion,
            @PathVariable Integer idUsuario,
            @PathVariable Long idPlatillo) {
        CalificacionPlatilloDto nueva = calificacionServiceImp.registrarCalificacion(calificacion, idUsuario,
                idPlatillo);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar una calificacion", description = "Elimina una calificacion con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Calificacion eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable Long id) {
        calificacionServiceImp.deleteCalificacion(id);
        return ResponseEntity.noContent().build();
    }
}
