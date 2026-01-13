package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import com.saborurbano.restaurante.dtos.ComentarioDto;
import com.saborurbano.restaurante.model.Comentario;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    ComentarioDto toDTO(Comentario comentario);

    Comentario toEntity(ComentarioDto comentarioDto);
}
