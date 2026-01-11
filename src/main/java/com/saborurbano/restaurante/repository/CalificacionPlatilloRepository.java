package com.saborurbano.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saborurbano.restaurante.model.CalificacionPlatillo;

@Repository
public interface CalificacionPlatilloRepository extends JpaRepository<CalificacionPlatillo, Long> {
    boolean existsByUsuarioIdAndPlatilloIdPlatillo(Integer idUsuario, Long idPlatillo);
}
