package com.saborurbano.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saborurbano.restaurante.model.Platillo;

@Repository
public interface PlatilloRepository extends JpaRepository<Platillo, Long> {
}
