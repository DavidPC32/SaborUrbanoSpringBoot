package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.saborurbano.restaurante.dtos.ReaccionComentarioDto;
import com.saborurbano.restaurante.model.ReaccionComentario;

@Mapper(componentModel = "spring")
public interface ReaccionComentarioMapper {
    ReaccionComentarioMapper INSTANCE = Mappers.getMapper(ReaccionComentarioMapper.class);

    ReaccionComentarioDto toDTO(ReaccionComentario reaccionComentario);

    ReaccionComentario toEntity(ReaccionComentarioDto reaccionComentarioDto);
}
