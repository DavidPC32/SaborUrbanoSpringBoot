package com.saborurbano.restaurante.service.Comentario;

import java.util.List;

import com.saborurbano.restaurante.dtos.ComentarioDto;

public interface ComentarioServiceInt {

    public ComentarioDto registrarComentario(ComentarioDto comentario, Integer idUsuario);

    public List<ComentarioDto> getAllComentarios();

    public List<ComentarioDto> getComentariosByUsuario(Integer idUsuario);

    public void deleteComentario(Integer id);

}
