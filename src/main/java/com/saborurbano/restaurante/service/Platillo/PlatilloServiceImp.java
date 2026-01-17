package com.saborurbano.restaurante.service.Platillo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saborurbano.restaurante.dtos.PlatilloDto;
import com.saborurbano.restaurante.mapper.PlatilloMapper;
import com.saborurbano.restaurante.model.Platillo;
import com.saborurbano.restaurante.model.Categoria;
import com.saborurbano.restaurante.repository.PlatilloRepository;
import com.saborurbano.restaurante.repository.CategoriaRepository;

@Service
public class PlatilloServiceImp implements PlatilloServiceInt {

    private final PlatilloRepository platilloRepository;
    private final PlatilloMapper platilloMapper;
    private final CategoriaRepository categoriaRepository;

    public PlatilloServiceImp(PlatilloRepository platilloRepository, PlatilloMapper platilloMapper,
            CategoriaRepository categoriaRepository) {
        this.platilloRepository = platilloRepository;
        this.platilloMapper = platilloMapper;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public PlatilloDto registrarPlatillo(PlatilloDto platilloDto, Long idCategoria) {

        if (platilloDto == null) {
            throw new IllegalArgumentException("El platillo no puede ser null.");
        }

        String nombre = platilloDto.getNombre();
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre del platillo es obligatorio.");
        }

        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del platillo no puede estar vacío.");
        }

        if (nombre.length() < 3 || nombre.length() > 100) {
            throw new IllegalArgumentException("El nombre del platillo debe tener entre 3 y 100 caracteres.");
        }

        if (platilloDto.getPrecio() == null || platilloDto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio del platillo debe ser mayor a 0.");
        }

        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("No existe la categoría con ID " + idCategoria));

        // Normalización
        platilloDto.setNombre(nombre);

        Platillo platillo = platilloMapper.toEntity(platilloDto);
        platillo.setIdPlatillo(null); // Asegurar que es un nuevo registro
        platillo.setCategoria(categoria);

        Platillo platilloGuardado = platilloRepository.save(platillo);
        return platilloMapper.toDTO(platilloGuardado);
    }

    @Override
    public List<PlatilloDto> getAllPlatillos() {
        List<Platillo> platillos = platilloRepository.findAll();
        return platillos.stream()
                .map(platilloMapper::toDTO)
                .toList();
    }

    @Override
    public PlatilloDto getPlatilloById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del platillo es inválido.");
        }

        Platillo platillo = platilloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el platillo con ID " + id));

        return platilloMapper.toDTO(platillo);
    }

    @Override
    public void deletePlatillo(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del platillo es inválido.");
        }

        if (!platilloRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe el platillo con ID " + id);
        }

        platilloRepository.deleteById(id);
    }
}
