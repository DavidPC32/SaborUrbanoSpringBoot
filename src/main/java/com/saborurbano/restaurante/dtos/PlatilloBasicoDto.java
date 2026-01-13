package com.saborurbano.restaurante.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatilloBasicoDto {
    private Long idPlatillo;
    private String nombre;
    private Double precio;
}
