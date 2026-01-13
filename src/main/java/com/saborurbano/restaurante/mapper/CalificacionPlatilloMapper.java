package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import com.saborurbano.restaurante.dtos.CalificacionPlatilloDto;
import com.saborurbano.restaurante.model.CalificacionPlatillo;

@Mapper(componentModel = "spring")
public interface CalificacionPlatilloMapper {

    CalificacionPlatilloDto toDTO(CalificacionPlatillo calificacionPlatillo);

    CalificacionPlatillo toEntity(CalificacionPlatilloDto calificacionPlatilloDto);
}
