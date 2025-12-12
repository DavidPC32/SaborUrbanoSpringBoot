package com.saborurbano.restaurante.service.Usuario;
import java.util.List;

import com.saborurbano.restaurante.model.Usuarios;

public interface UsuarioServiceInt {

    public Usuarios registrarUsuarios(Usuarios usuarios);

    public List<Usuarios> getAllUsuarios();

    public Usuarios getUsuarioId(Integer id);

    public void deleteUsuarios(Integer id, Usuarios usuario);
    
}
