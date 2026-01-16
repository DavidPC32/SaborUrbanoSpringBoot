package com.saborurbano.restaurante.service.Usuario;
import java.util.List;

import com.saborurbano.restaurante.dtos.UsuarioDto;

public interface UsuarioServiceInt {

    public UsuarioDto registrarUsuarios(UsuarioDto dto);

    public List<UsuarioDto> getAllUsuarios();

    public UsuarioDto getUsuarioId(Integer id);

    public void deleteUsuarios(Integer id);
    
}
