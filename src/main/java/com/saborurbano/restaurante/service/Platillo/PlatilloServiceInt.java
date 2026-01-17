package com.saborurbano.restaurante.service.Platillo;

import java.util.List;

import com.saborurbano.restaurante.dtos.PlatilloDto;

public interface PlatilloServiceInt {

    PlatilloDto registrarPlatillo(PlatilloDto platillo, Long idCategoria);

    List<PlatilloDto> getAllPlatillos();

    PlatilloDto getPlatilloById(Long id);

    void deletePlatillo(Long id);
}
