package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.saborurbano.restaurante.dtos.ComentarioDto;
import com.saborurbano.restaurante.model.Comentario;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComentarioMapper {

    ComentarioDto toDTO(Comentario comentario);

    Comentario toEntity(ComentarioDto comentarioDto);
}
