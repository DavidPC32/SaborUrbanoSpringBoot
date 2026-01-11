package com.saborurbano.restaurante.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saborurbano.restaurante.model.Categoria;
import com.saborurbano.restaurante.service.Categoria.CategoriaServiceImp;

@RequestMapping("api/categorias")
@RestController
public class CategoriaController {

    private final CategoriaServiceImp categoriaServiceImp;

    public CategoriaController(CategoriaServiceImp categoriaServiceImp) {
        this.categoriaServiceImp = categoriaServiceImp;
    }

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaServiceImp.getAllCategorias();
    }

    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaServiceImp.getCategoriaById(id);
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaServiceImp.registrarCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaServiceImp.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
