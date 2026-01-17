package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.saborurbano.restaurante.dtos.CalificacionPlatilloDto;
import com.saborurbano.restaurante.model.CalificacionPlatillo;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class,
        PlatilloMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CalificacionPlatilloMapper {

    CalificacionPlatilloDto toDTO(CalificacionPlatillo calificacionPlatillo);

    CalificacionPlatillo toEntity(CalificacionPlatilloDto calificacionPlatilloDto);
}
