package com.saborurbano.restaurante.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saborurbano.restaurante.dtos.PlatilloDto;
import com.saborurbano.restaurante.service.Platillo.PlatilloServiceInt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RequestMapping("api/platillos")
@RestController
@Tag(name = "Platillos", description = "Endpoint para gestionar platillos")
public class PlatilloController {

    private final PlatilloServiceInt platilloServiceInt;

    public PlatilloController(PlatilloServiceInt platilloServiceInt) {
        this.platilloServiceInt = platilloServiceInt;
    }

    @Operation(summary = "Obtener todos los platillos", description = "Devuelve una lista de todos los platillos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de platillos"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<PlatilloDto>> getAllPlatillos() {
        return ResponseEntity.ok(platilloServiceInt.getAllPlatillos());
    }

    @Operation(summary = "Obtener un platillo por su id", description = "Devuelve un platillo con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve un platillo con el id especificado"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlatilloDto> getPlatilloById(@PathVariable Long id) {
        return ResponseEntity.ok(platilloServiceInt.getPlatilloById(id));
    }

    @Operation(summary = "Crear un platillo", description = "Crea un nuevo platillo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Platillo creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/{idCategoria}")
    public ResponseEntity<PlatilloDto> createPlatillo(@Valid @RequestBody PlatilloDto platillo,
            @PathVariable Long idCategoria) {
        PlatilloDto nuevoPlatillo = platilloServiceInt.registrarPlatillo(platillo, idCategoria);
        return new ResponseEntity<>(nuevoPlatillo, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un platillo", description = "Elimina un platillo con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Platillo eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatillo(@PathVariable Long id) {
        platilloServiceInt.deletePlatillo(id);
        return ResponseEntity.noContent().build();
    }
}
