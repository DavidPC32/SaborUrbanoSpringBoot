package com.saborurbano.restaurante.service.Usuario;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saborurbano.restaurante.dtos.UsuarioDtoCompleto;
import com.saborurbano.restaurante.mapper.UsuarioMapper;
import com.saborurbano.restaurante.model.Usuarios;
import com.saborurbano.restaurante.repository.UsuariosRepository;


@Service
public class UsuarioServiceImp implements  UsuarioServiceInt {
    @Autowired
    private final UsuariosRepository usuarioRepository;
    @Autowired
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImp(UsuariosRepository usuariosRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuariosRepository;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public UsuarioDtoCompleto registrarUsuarios(UsuarioDtoCompleto dto) {
        usuarioRepository.findById(dto.getId()).ifPresent(c -> {
            throw new IllegalArgumentException("El Veterinario '" + c.getNombreCompleto() + "' ya existe.");
        });
        Usuarios usuarioGuardado = usuarioRepository.save(usuarioMapper.toEntity(dto));
        return usuarioMapper.toDTO(usuarioGuardado);
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

    
