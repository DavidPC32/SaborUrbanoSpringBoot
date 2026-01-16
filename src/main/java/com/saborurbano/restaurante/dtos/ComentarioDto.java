package com.saborurbano.restaurante.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDto {
    private int idComentario;
    private String textoComentario;
    private LocalDateTime fechaPublicacion;
    private UsuarioDto usuario;
}
