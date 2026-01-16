package com.saborurbano.restaurante.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaccionComentarioDto {
    private Long idReaccion;
    private String tipoReaccion;
    private UsuarioDto usuario;
}
