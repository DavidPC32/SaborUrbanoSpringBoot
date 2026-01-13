package com.saborurbano.restaurante.service.Usuario;
import java.util.List;

import com.saborurbano.restaurante.dtos.UsuarioDtoCompleto;
import com.saborurbano.restaurante.model.Usuarios;

public interface UsuarioServiceInt {

    public UsuarioDtoCompleto registrarUsuarios(UsuarioDtoCompleto dto);

    public List<Usuarios> getAllUsuarios();

    public Usuarios getUsuarioId(Integer id);

    public void deleteUsuarios(Integer id, Usuarios usuario);
    
}
