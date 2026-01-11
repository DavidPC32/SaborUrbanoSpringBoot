package com.saborurbano.restaurante.service.CalificacionPlatillo;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public CalificacionPlatilloServiceImp(
            CalificacionPlatilloRepository calificacionRepository,
            UsuariosRepository usuariosRepository,
            PlatilloRepository platilloRepository
    ) {
        this.calificacionRepository = calificacionRepository;
        this.usuariosRepository = usuariosRepository;
        this.platilloRepository = platilloRepository;
    }

    @Override
    public CalificacionPlatillo registrarCalificacion(CalificacionPlatillo calificacion, Integer idUsuario, Long idPlatillo) {

        if (calificacion == null) {
            throw new IllegalArgumentException("La calificacion no puede ser null.");
        }

        if (idUsuario == null || idUsuario <= 0) {
            throw new IllegalArgumentException("El idUsuario es inválido.");
        }

        if (idPlatillo == null || idPlatillo <= 0) {
            throw new IllegalArgumentException("El idPlatillo es inválido.");
        }

        // Validación puntuación
        Integer puntuacion = calificacion.getPuntuacion();
        if (puntuacion == null) {
            throw new IllegalArgumentException("La puntuacion es obligatoria.");
        }
        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("La puntuacion debe estar entre 1 y 5.");
        }

        // Validación comentario (opcional)
        String comentario = calificacion.getComentarioCorto();
        if (comentario != null) {
            comentario = comentario.trim();
            if (comentario.length() > 255) {
                throw new IllegalArgumentException("El comentario no puede superar 255 caracteres.");
            }
            calificacion.setComentarioCorto(comentario);
        }

        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("No existe el usuario con ID " + idUsuario));

        Platillo platillo = platilloRepository.findById(idPlatillo)
                .orElseThrow(() -> new RuntimeException("No existe el platillo con ID " + idPlatillo));

        // Regla: no permitir 2 calificaciones del mismo usuario al mismo platillo
        if (calificacionRepository.existsByUsuarioIdAndPlatilloIdPlatillo(idUsuario, idPlatillo)) {
            throw new IllegalArgumentException("El usuario ya calificó este platillo.");
        }

        calificacion.setUsuario(usuario);
        calificacion.setPlatillo(platillo);

        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<CalificacionPlatillo> getAllCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Override
    public CalificacionPlatillo getCalificacionById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de calificacion es inválido.");
        }

        return calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la calificacion con ID " + id));
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
