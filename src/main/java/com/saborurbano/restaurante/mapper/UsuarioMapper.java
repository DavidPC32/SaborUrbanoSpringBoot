package com.saborurbano.restaurante.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.saborurbano.restaurante.dtos.UsuarioDto;
import com.saborurbano.restaurante.model.Usuarios;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDTO(Usuarios usuarios);

    Usuarios toEntity(UsuarioDto usuarioDto);

    List<UsuarioDto> toDto(List<Usuarios> usuarios);
    
}
