package com.saborurbano.restaurante.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;

import com.saborurbano.restaurante.dtos.CategoriaBasicoDto;
import com.saborurbano.restaurante.service.Categoria.CategoriaServiceImp;

@RequestMapping("api/categorias")
@RestController
@Tag(name = "Categorias", description = "Endpoint para gestionar las categorias")
public class CategoriaController {

    private final CategoriaServiceImp categoriaServiceImp;

    public CategoriaController(CategoriaServiceImp categoriaServiceImp) {
        this.categoriaServiceImp = categoriaServiceImp;
    }

    @Operation(summary = "Obtener todas las categorias", description = "Devuelve una lista de todas las categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una lista de categorias"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<CategoriaBasicoDto>> getAllCategorias() {
        return ResponseEntity.ok(categoriaServiceImp.getAllCategorias());
    }

    @Operation(summary = "Obtener una categoria por su id", description = "Devuelve una categoria con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve una categoria con el id especificado"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaBasicoDto> getCategoriaById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaServiceImp.getCategoriaById(id));
    }

    @Operation(summary = "Crear una categoria", description = "Crea una nueva categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CategoriaBasicoDto> createCategoria(@Valid @RequestBody CategoriaBasicoDto categoria) {
        CategoriaBasicoDto nuevaCategoria = categoriaServiceImp.registrarCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar una categoria", description = "Elimina una categoria con el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria eliminada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error del cliente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaServiceImp.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
