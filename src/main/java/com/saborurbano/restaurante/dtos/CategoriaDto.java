package com.saborurbano.restaurante.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {
    private Long idCategoria;
    private String nombreCategoria;
    private Set<PlatilloBasicoDto> platillos;
}
