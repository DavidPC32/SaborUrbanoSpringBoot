package com.saborurbano.restaurante.service.Platillo;

import java.util.List;

import com.saborurbano.restaurante.model.Platillo;

public interface PlatilloServiceInt {

    Platillo registrarPlatillo(Platillo platillo);

    List<Platillo> getAllPlatillos();

    Platillo getPlatilloById(Long id);

    void deletePlatillo(Long id);
}
