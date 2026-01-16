package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import com.saborurbano.restaurante.dtos.CategoriaBasicoDto;
import com.saborurbano.restaurante.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaBasicoDto toDTO(Categoria categoria);

    Categoria toEntity(CategoriaBasicoDto categoriaDto);
}
