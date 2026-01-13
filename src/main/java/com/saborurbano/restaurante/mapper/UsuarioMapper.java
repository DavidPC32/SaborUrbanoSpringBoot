package com.saborurbano.restaurante.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.saborurbano.restaurante.dtos.UsuarioDtoCompleto;
import com.saborurbano.restaurante.model.Usuarios;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDtoCompleto toDTO(Usuarios usuarios);

    Usuarios toEntity(UsuarioDtoCompleto usuarioDto);
    
}
