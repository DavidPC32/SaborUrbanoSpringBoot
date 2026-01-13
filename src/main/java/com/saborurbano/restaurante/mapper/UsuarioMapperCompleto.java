package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import com.saborurbano.restaurante.dtos.UsuarioBasicoDto;
import com.saborurbano.restaurante.dtos.UsuarioDtoCompleto;
import com.saborurbano.restaurante.model.Usuarios;

@Mapper(componentModel = "spring")
public interface UsuarioMapperCompleto {

    UsuarioBasicoDto toBasicoDTO(Usuarios usuarios);

    UsuarioDtoCompleto toCompletoDTO(Usuarios usuarios);

    Usuarios toEntity(UsuarioDtoCompleto usuarioDtoCompleto);
}
