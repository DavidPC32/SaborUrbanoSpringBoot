package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import com.saborurbano.restaurante.dtos.CategoriaDto;
import com.saborurbano.restaurante.dtos.CategoriaBasicoDto;
import com.saborurbano.restaurante.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDto toDTO(Categoria categoria);

    CategoriaBasicoDto toBasicoDTO(Categoria categoria);

    Categoria toEntity(CategoriaDto categoriaDto);
}
