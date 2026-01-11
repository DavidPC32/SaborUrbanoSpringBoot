package com.saborurbano.restaurante.service.CalificacionPlatillo;

import java.util.List;

import com.saborurbano.restaurante.model.CalificacionPlatillo;

public interface CalificacionPlatilloServiceInt {

    public CalificacionPlatillo registrarCalificacion(CalificacionPlatillo calificacion, Integer idUsuario, Long idPlatillo);

    public List<CalificacionPlatillo> getAllCalificaciones();

    public CalificacionPlatillo getCalificacionById(Long id);

    public void deleteCalificacion(Long id);
}
