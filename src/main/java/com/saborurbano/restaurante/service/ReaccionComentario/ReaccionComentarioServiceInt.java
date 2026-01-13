package com.saborurbano.restaurante.service.ReaccionComentario;

import java.util.List;

import com.saborurbano.restaurante.model.ReaccionComentario;

public interface ReaccionComentarioServiceInt {

    ReaccionComentario registrarReaccion(ReaccionComentario reaccion);

    List<ReaccionComentario> getAllReacciones();

    ReaccionComentario getReaccionById(Long id);

    void deleteReaccion(Long id);
}
