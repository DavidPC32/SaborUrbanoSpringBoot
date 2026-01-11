package com.saborurbano.restaurante.service.Categoria;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saborurbano.restaurante.model.Categoria;
import com.saborurbano.restaurante.repository.CategoriaRepository;

@Service
public class CategoriaServiceImp implements CategoriaServiceInt {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria registrarCategoria(Categoria categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException("La categoria no puede ser null.");
        }

        String nombre = categoria.getNombreCategoria();
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre de la categoria es obligatorio.");
        }

        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoria no puede estar vacío.");
        }

        if (nombre.length() < 3 || nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre de la categoria debe tener entre 3 y 100 caracteres.");
        }

        // Normalización
        categoria.setNombreCategoria(nombre);

        // Regla: no duplicados
        if (categoriaRepository.existsByNombreCategoria(nombre)) {
            throw new IllegalArgumentException("Ya existe una categoria con el nombre: " + nombre);
        }

        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de categoria es inválido.");
        }

        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la categoria con ID " + id));
    }

    @Override
    public void deleteCategoria(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de categoria es inválido.");
        }

        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe la categoria con ID " + id);
        }

        categoriaRepository.deleteById(id);
    }
}
