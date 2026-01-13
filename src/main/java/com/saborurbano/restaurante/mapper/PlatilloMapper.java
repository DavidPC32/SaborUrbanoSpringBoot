package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.saborurbano.restaurante.dtos.PlatilloDto;
import com.saborurbano.restaurante.dtos.PlatilloBasicoDto;
import com.saborurbano.restaurante.model.Platillo;

@Mapper(componentModel = "spring")
public interface PlatilloMapper {
    PlatilloMapper INSTANCE = Mappers.getMapper(PlatilloMapper.class);

    PlatilloDto toDTO(Platillo platillo);

    PlatilloBasicoDto toBasicoDTO(Platillo platillo);

    Platillo toEntity(PlatilloDto platilloDto);
}
