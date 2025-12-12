package com.saborurbano.restaurante.service.Comentario;
import java.util.List;

import com.saborurbano.restaurante.model.Comentario;

public interface ComentarioServiceInt {

    public Comentario registrarComentario(Comentario comentario, Integer idUsuario);

    public List<Comentario> getAllComentarios();

    public void deleteComentario(Integer id, Comentario comentario);
    
}
