package com.saborurbano.restaurante.service.Platillo;

import java.util.List;

import org.springframework.stereotype.Service;
import com.saborurbano.restaurante.model.Platillo;
import com.saborurbano.restaurante.repository.PlatilloRepository;

@Service
public class PlatilloServiceImp implements PlatilloServiceInt {

    private final PlatilloRepository platilloRepository;

    public PlatilloServiceImp(PlatilloRepository platilloRepository) {
        this.platilloRepository = platilloRepository;
    }

    @Override
    public Platillo registrarPlatillo(Platillo platillo) {

        if (platillo == null) {
            throw new IllegalArgumentException("El platillo no puede ser null.");
        }

        String nombre = platillo.getNombre();
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

        if (platillo.getPrecio() == null || platillo.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio del platillo debe ser mayor a 0.");
        }

        if (platillo.getCategoria() == null) {
            throw new IllegalArgumentException("El platillo debe tener una categoria asignada.");
        }

        // Normalización
        platillo.setNombre(nombre);

        return platilloRepository.save(platillo);
    }

    @Override
    public List<Platillo> getAllPlatillos() {
        return platilloRepository.findAll();
    }

    @Override
    public Platillo getPlatilloById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del platillo es inválido.");
        }

        return platilloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el platillo con ID " + id));
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
