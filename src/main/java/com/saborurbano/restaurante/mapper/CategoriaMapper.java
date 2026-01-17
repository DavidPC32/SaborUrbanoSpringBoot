package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.saborurbano.restaurante.dtos.CategoriaBasicoDto;
import com.saborurbano.restaurante.model.Categoria;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriaMapper {

    CategoriaBasicoDto toDTO(Categoria categoria);

    Categoria toEntity(CategoriaBasicoDto categoriaDto);
}
