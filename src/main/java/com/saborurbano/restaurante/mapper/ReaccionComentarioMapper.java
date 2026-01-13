package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import com.saborurbano.restaurante.dtos.ReaccionComentarioDto;
import com.saborurbano.restaurante.model.ReaccionComentario;

@Mapper(componentModel = "spring")
public interface ReaccionComentarioMapper {

    ReaccionComentarioDto toDTO(ReaccionComentario reaccionComentario);

    ReaccionComentario toEntity(ReaccionComentarioDto reaccionComentarioDto);
}
