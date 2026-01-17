package com.saborurbano.restaurante.service.CalificacionPlatillo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saborurbano.restaurante.dtos.CalificacionPlatilloDto;
import com.saborurbano.restaurante.mapper.CalificacionPlatilloMapper;
import com.saborurbano.restaurante.model.CalificacionPlatillo;
import com.saborurbano.restaurante.model.Platillo;
import com.saborurbano.restaurante.model.Usuarios;
import com.saborurbano.restaurante.repository.CalificacionPlatilloRepository;
import com.saborurbano.restaurante.repository.PlatilloRepository;
import com.saborurbano.restaurante.repository.UsuariosRepository;

@Service
public class CalificacionPlatilloServiceImp implements CalificacionPlatilloServiceInt {

    private final CalificacionPlatilloRepository calificacionRepository;
    private final UsuariosRepository usuariosRepository;
    private final PlatilloRepository platilloRepository;
    private final CalificacionPlatilloMapper calificacionMapper;

    public CalificacionPlatilloServiceImp(
            CalificacionPlatilloRepository calificacionRepository,
            UsuariosRepository usuariosRepository,
            PlatilloRepository platilloRepository,
            CalificacionPlatilloMapper calificacionMapper) {
        this.calificacionRepository = calificacionRepository;
        this.usuariosRepository = usuariosRepository;
        this.platilloRepository = platilloRepository;
        this.calificacionMapper = calificacionMapper;
    }

    @Override
    public CalificacionPlatilloDto registrarCalificacion(CalificacionPlatilloDto calificacionDto, Integer idUsuario,
            Long idPlatillo) {

        if (calificacionDto == null) {
            throw new IllegalArgumentException("La calificacion no puede ser null.");
        }

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("El idUsuario es inválido.");
        }

        if (idPlatillo == null || idPlatillo <= 0) {
            throw new IllegalArgumentException("El idPlatillo es inválido.");
        }

        // Validación puntuación
        Integer puntuacion = calificacionDto.getPuntuacion();
        if (puntuacion == null) {
            throw new IllegalArgumentException("La puntuacion es obligatoria.");
        }
        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("La puntuacion debe estar entre 1 y 5.");
        }

        // Validación comentario (opcional)
        String comentario = calificacionDto.getComentarioCorto();
        if (comentario != null) {
            comentario = comentario.trim();
            if (comentario.length() > 255) {
                throw new IllegalArgumentException("El comentario no puede superar 255 caracteres.");
            }
            calificacionDto.setComentarioCorto(comentario);
        }

        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("No existe el usuario con ID " + idUsuario));

        Platillo platillo = platilloRepository.findById(idPlatillo)
                .orElseThrow(() -> new RuntimeException("No existe el platillo con ID " + idPlatillo));

        // Regla: no permitir 2 calificaciones del mismo usuario al mismo platillo
        if (calificacionRepository.existsByUsuarioIdAndPlatilloIdPlatillo(idUsuario, idPlatillo)) {
            throw new IllegalArgumentException("El usuario ya calificó este platillo.");
        }

        CalificacionPlatillo calificacion = calificacionMapper.toEntity(calificacionDto);
        calificacion.setIdCalificacion(null); // Asegurar que es un nuevo registro
        calificacion.setUsuario(usuario);
        calificacion.setPlatillo(platillo);

        CalificacionPlatillo calificacionGuardada = calificacionRepository.save(calificacion);
        return calificacionMapper.toDTO(calificacionGuardada);
    }

    @Override
    public List<CalificacionPlatilloDto> getAllCalificaciones() {
        List<CalificacionPlatillo> calificaciones = calificacionRepository.findAll();
        return calificaciones.stream()
                .map(calificacionMapper::toDTO)
                .toList();
    }

    @Override
    public CalificacionPlatilloDto getCalificacionById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de calificacion es inválido.");
        }

        CalificacionPlatillo calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la calificacion con ID " + id));

        return calificacionMapper.toDTO(calificacion);
    }

    @Override
    public void deleteCalificacion(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de calificacion es inválido.");
        }

        if (!calificacionRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe la calificacion con ID " + id);
        }

        calificacionRepository.deleteById(id);
    }
}
