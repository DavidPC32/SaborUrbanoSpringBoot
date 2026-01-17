package com.saborurbano.restaurante.service.Categoria;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saborurbano.restaurante.dtos.CategoriaBasicoDto;
import com.saborurbano.restaurante.mapper.CategoriaMapper;
import com.saborurbano.restaurante.model.Categoria;
import com.saborurbano.restaurante.repository.CategoriaRepository;

@Service
public class CategoriaServiceImp implements CategoriaServiceInt {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaBasicoDto registrarCategoria(CategoriaBasicoDto categoriaDto) {

        if (categoriaDto == null) {
            throw new IllegalArgumentException("La categoria no puede ser null.");
        }

        String nombre = categoriaDto.getNombreCategoria();
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
        categoriaDto.setNombreCategoria(nombre);

        // Regla: no duplicados
        if (categoriaRepository.existsByNombreCategoria(nombre)) {
            throw new IllegalArgumentException("Ya existe una categoria con el nombre: " + nombre);
        }

        Categoria categoria = categoriaMapper.toEntity(categoriaDto);
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoriaGuardada);
    }

    @Override
    public List<CategoriaBasicoDto> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toDTO)
                .toList();
    }

    @Override
    public CategoriaBasicoDto getCategoriaById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de categoria es inválido.");
        }

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la categoria con ID " + id));

        return categoriaMapper.toDTO(categoria);
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
