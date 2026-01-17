package com.saborurbano.restaurante.service.ReaccionComentario;

import java.util.List;

import com.saborurbano.restaurante.dtos.ReaccionComentarioDto;

public interface ReaccionComentarioServiceInt {

    ReaccionComentarioDto registrarReaccion(ReaccionComentarioDto reaccion, Integer idUsuario, Integer idComentario);

    List<ReaccionComentarioDto> getAllReacciones();

    ReaccionComentarioDto getReaccionById(Long id);

    void deleteReaccion(Long id);
}
