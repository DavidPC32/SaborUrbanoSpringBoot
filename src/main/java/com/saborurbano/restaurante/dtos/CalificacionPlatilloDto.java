package com.saborurbano.restaurante.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificacionPlatilloDto {
    private Long idCalificacion;
    private Integer puntuacion;
    private String comentarioCorto;
    private UsuarioBasicoDto usuario;
    private PlatilloBasicoDto platillo;
}
