package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.saborurbano.restaurante.dtos.ComentarioDto;
import com.saborurbano.restaurante.model.Comentario;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    ComentarioMapper INSTANCE = Mappers.getMapper(ComentarioMapper.class);

    ComentarioDto toDTO(Comentario comentario);

    Comentario toEntity(ComentarioDto comentarioDto);
}
