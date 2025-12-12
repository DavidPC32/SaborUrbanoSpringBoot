package com.saborurbano.restaurante.service.Usuario;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saborurbano.restaurante.model.Usuarios;
import com.saborurbano.restaurante.repository.UsuariosRepository;


@Service
public class UsuarioServiceImp implements  UsuarioServiceInt {

    private final UsuariosRepository usuarioRepository;

    public UsuarioServiceImp(UsuariosRepository usuariosRepository) {
        this.usuarioRepository = usuariosRepository;
    }

    @Override
    public Usuarios registrarUsuarios(Usuarios usuarios) {
        usuarioRepository.findById(usuarios.getId()).ifPresent(c -> {
            throw new IllegalArgumentException("El Veterinario '" + c.getNombreCompleto() + "' ya existe.");
        });
        return usuarioRepository.save(usuarios);

    }

    @Override
    public List<Usuarios> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuarios getUsuarioId(Integer id){
    return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Error de Negocio: El usuario con ID " + id + " no existe."));
    }

    @SuppressWarnings("null")
    @Override
    public void deleteUsuarios(Integer id, Usuarios usuarios) {

        usuarioRepository.findById(usuarios.getId()).ifPresentOrElse(c -> {
            usuarioRepository.deleteById(id);
        },
                () -> {

                    throw new IllegalArgumentException("El Usuario '" + usuarios.getNombreCompleto() + "' no existe.");
                });
    }

    public UsuariosRepository getUsuarioRepository() {
        return usuarioRepository;
    }

}

    
