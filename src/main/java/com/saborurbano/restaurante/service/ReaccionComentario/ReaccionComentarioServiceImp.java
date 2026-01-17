package com.saborurbano.restaurante.service.ReaccionComentario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saborurbano.restaurante.dtos.ReaccionComentarioDto;
import com.saborurbano.restaurante.mapper.ReaccionComentarioMapper;
import com.saborurbano.restaurante.model.Comentario;
import com.saborurbano.restaurante.model.ReaccionComentario;
import com.saborurbano.restaurante.model.Usuarios;
import com.saborurbano.restaurante.repository.ComentarioRepository;
import com.saborurbano.restaurante.repository.ReaccionComentarioRepository;
import com.saborurbano.restaurante.repository.UsuariosRepository;

@Service
public class ReaccionComentarioServiceImp implements ReaccionComentarioServiceInt {

    private final ReaccionComentarioRepository reaccionComentarioRepository;
    private final UsuariosRepository usuariosRepository;
    private final ComentarioRepository comentarioRepository;
    private final ReaccionComentarioMapper reaccionMapper;

    public ReaccionComentarioServiceImp(
            ReaccionComentarioRepository reaccionComentarioRepository,
            UsuariosRepository usuariosRepository,
            ComentarioRepository comentarioRepository,
            ReaccionComentarioMapper reaccionMapper) {
        this.reaccionComentarioRepository = reaccionComentarioRepository;
        this.usuariosRepository = usuariosRepository;
        this.comentarioRepository = comentarioRepository;
        this.reaccionMapper = reaccionMapper;
    }

    @Override
    public ReaccionComentarioDto registrarReaccion(ReaccionComentarioDto reaccionDto, Integer idUsuario,
            Integer idComentario) {

        if (reaccionDto == null) {
            throw new IllegalArgumentException("La reacción no puede ser null.");
        }

        String tipoReaccion = reaccionDto.getTipoReaccion();
        if (tipoReaccion == null) {
            throw new IllegalArgumentException("El tipo de reacción es obligatorio.");
        }

        tipoReaccion = tipoReaccion.trim();
        if (tipoReaccion.isEmpty()) {
            throw new IllegalArgumentException("El tipo de reacción no puede estar vacío.");
        }

        if (idUsuario == null) {
            throw new IllegalArgumentException("El ID de usuario es obligatorio.");
        }

        if (idComentario == null) {
            throw new IllegalArgumentException("El ID de comentario es obligatorio.");
        }

        // Obtener Usuario
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("No existe el usuario con ID " + idUsuario));

        // Obtener Comentario
        Comentario comentario = comentarioRepository.findById(idComentario)
                .orElseThrow(() -> new RuntimeException("No existe el comentario con ID " + idComentario));

        // Validar que el usuario no haya reaccionado ya al mismo comentario
        if (reaccionComentarioRepository.existsByComentarioIdComentarioAndUsuarioId(idComentario, idUsuario)) {
            throw new IllegalArgumentException("El usuario ya ha reaccionado a este comentario.");
        }

        // Normalización
        reaccionDto.setTipoReaccion(tipoReaccion.toUpperCase());

        ReaccionComentario reaccion = reaccionMapper.toEntity(reaccionDto);
        reaccion.setUsuario(usuario);
        reaccion.setComentario(comentario);

        ReaccionComentario reaccionGuardada = reaccionComentarioRepository.save(reaccion);
        return reaccionMapper.toDTO(reaccionGuardada);
    }

    @Override
    public List<ReaccionComentarioDto> getAllReacciones() {
        List<ReaccionComentario> reacciones = reaccionComentarioRepository.findAll();
        return reacciones.stream()
                .map(reaccionMapper::toDTO)
                .toList();
    }

    @Override
    public ReaccionComentarioDto getReaccionById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la reacción es inválido.");
        }

        ReaccionComentario reaccion = reaccionComentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la reacción con ID " + id));

        return reaccionMapper.toDTO(reaccion);
    }

    @Override
    public void deleteReaccion(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la reacción es inválido.");
        }

        if (!reaccionComentarioRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe la reacción con ID " + id);
        }

        reaccionComentarioRepository.deleteById(id);
    }
}
