package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.saborurbano.restaurante.dtos.CalificacionPlatilloDto;
import com.saborurbano.restaurante.model.CalificacionPlatillo;

@Mapper(componentModel = "spring")
public interface CalificacionPlatilloMapper {
    CalificacionPlatilloMapper INSTANCE = Mappers.getMapper(CalificacionPlatilloMapper.class);

    CalificacionPlatilloDto toDTO(CalificacionPlatillo calificacionPlatillo);

    CalificacionPlatillo toEntity(CalificacionPlatilloDto calificacionPlatilloDto);
}
