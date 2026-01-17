package com.saborurbano.restaurante.service.Comentario;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.saborurbano.restaurante.dtos.ComentarioDto;
import com.saborurbano.restaurante.mapper.ComentarioMapper;
import com.saborurbano.restaurante.model.Comentario;
import com.saborurbano.restaurante.model.Usuarios;
import com.saborurbano.restaurante.repository.ComentarioRepository;
import com.saborurbano.restaurante.repository.UsuariosRepository;

@Service
public class ComentarioServiceImp implements ComentarioServiceInt {

    private final ComentarioRepository comentarioRepository;
    private final UsuariosRepository usuariosRepository;
    private final ComentarioMapper comentarioMapper;

    public ComentarioServiceImp(ComentarioRepository comentarioRepository,
            UsuariosRepository usuariosRepository,
            ComentarioMapper comentarioMapper) {
        this.comentarioRepository = comentarioRepository;
        this.usuariosRepository = usuariosRepository;
        this.comentarioMapper = comentarioMapper;
    }

    @Override
    public List<ComentarioDto> getAllComentarios() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return comentarios.stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }

    @Override
    public List<ComentarioDto> getComentariosByUsuario(Integer idUsuario) {
        // Verificar que el usuario existe
        usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + idUsuario + " no encontrado"));

        List<Comentario> comentarios = comentarioRepository.findByUsuarioId(idUsuario);
        return comentarios.stream()
                .map(comentarioMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteComentario(Integer id) {
        comentarioRepository.findById(id).ifPresentOrElse(c -> {
            comentarioRepository.deleteById(id);
        },
                () -> {

                    throw new IllegalArgumentException("El comentario " + id + " no existe");
                });
    }

    @Override
    public ComentarioDto registrarComentario(ComentarioDto comentarioDto, Integer idUsuario) {
        Usuarios usuarios = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + idUsuario + " no encontrado"));

        Comentario comentario = comentarioMapper.toEntity(comentarioDto);
        comentario.setUsuario(usuarios);
        comentario.setFechaPublicacion(LocalDateTime.now());

        Comentario comentarioGuardado = comentarioRepository.save(comentario);
        return comentarioMapper.toDTO(comentarioGuardado);
    }

}
