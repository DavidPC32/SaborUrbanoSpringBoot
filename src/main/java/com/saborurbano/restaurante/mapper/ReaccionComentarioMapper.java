package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.saborurbano.restaurante.dtos.ReaccionComentarioDto;
import com.saborurbano.restaurante.model.ReaccionComentario;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReaccionComentarioMapper {

    ReaccionComentarioDto toDTO(ReaccionComentario reaccionComentario);

    ReaccionComentario toEntity(ReaccionComentarioDto reaccionComentarioDto);
}
