package com.saborurbano.restaurante.service.Comentario;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.saborurbano.restaurante.model.Comentario;
import com.saborurbano.restaurante.model.Usuarios;
import com.saborurbano.restaurante.repository.ComentarioRepository;
import com.saborurbano.restaurante.repository.UsuariosRepository;



@Service
public class ComentarioServiceImp implements ComentarioServiceInt {

    private final ComentarioRepository comentarioRepository;
    private final UsuariosRepository usuariosRepository;

    public ComentarioServiceImp(ComentarioRepository comentarioRepository, UsuariosRepository usuariosRepository) {
        this.comentarioRepository = comentarioRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    @Override
    public void deleteComentario(Integer id, Comentario comentario) {
        comentarioRepository.findById(comentario.getIdComentario()).ifPresentOrElse(c -> {
            comentarioRepository.deleteById(id);
        },
        () -> {

            throw new IllegalArgumentException("El comentario " + comentario.getIdComentario() + " no existe");
        });
    }



    public ComentarioRepository ComentarioRepository() {
        return comentarioRepository;
    
    
    }


    @Override
    public Comentario registrarComentario(Comentario comentario, Integer idUsuario) {
        Usuarios usuarios = usuariosRepository.findById(idUsuario).orElseThrow();

        comentario.setUsuario(usuarios);
        comentario.setFechaPublicacion(LocalDateTime.now());

        return comentarioRepository.save(comentario);
    }


}
