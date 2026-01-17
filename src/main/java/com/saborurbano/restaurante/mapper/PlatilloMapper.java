package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.saborurbano.restaurante.dtos.PlatilloDto;
import com.saborurbano.restaurante.dtos.PlatilloBasicoDto;
import com.saborurbano.restaurante.model.Platillo;

@Mapper(componentModel = "spring", uses = { CategoriaMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlatilloMapper {

    PlatilloDto toDTO(Platillo platillo);

    PlatilloBasicoDto toBasicoDTO(Platillo platillo);

    Platillo toEntity(PlatilloDto platilloDto);
}
