package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import com.saborurbano.restaurante.dtos.UsuarioDtoCompleto;
import com.saborurbano.restaurante.model.Usuarios;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDtoCompleto toDTO(Usuarios usuarios);

    Usuarios toEntity(UsuarioDtoCompleto usuarioDto);
    
}
