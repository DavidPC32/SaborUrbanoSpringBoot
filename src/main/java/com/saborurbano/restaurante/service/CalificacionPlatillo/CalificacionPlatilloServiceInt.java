package com.saborurbano.restaurante.service.CalificacionPlatillo;

import java.util.List;

import com.saborurbano.restaurante.dtos.CalificacionPlatilloDto;

public interface CalificacionPlatilloServiceInt {

    CalificacionPlatilloDto registrarCalificacion(CalificacionPlatilloDto calificacion, Integer idUsuario,
            Long idPlatillo);

    List<CalificacionPlatilloDto> getAllCalificaciones();

    CalificacionPlatilloDto getCalificacionById(Long id);

    CalificacionPlatilloDto getCalificacionByUsuarioId(Long idUsuario);

    void deleteCalificacion(Long id);
}
