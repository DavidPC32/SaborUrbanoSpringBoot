package com.saborurbano.restaurante.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saborurbano.restaurante.model.Comentario;
import com.saborurbano.restaurante.service.Comentario.ComentarioServiceImp;
import com.saborurbano.restaurante.service.Usuario.UsuarioServiceImp;


@RequestMapping("api/comentarios")
@RestController
public class ComentarioController {

    private final ComentarioServiceImp comentarioServiceImp;

    public ComentarioController(ComentarioServiceImp comentarioServiceImp) {
        this.comentarioServiceImp = comentarioServiceImp;
    }

    @GetMapping
    public List<Comentario> getAllComentarios() {
        return comentarioServiceImp.getAllComentarios();
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario, @PathVariable Integer idUsuario) {
        Comentario nuevoComentario = comentarioServiceImp.registrarComentario(comentario, idUsuario);
        return new ResponseEntity<>(nuevoComentario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id, Comentario comentario) {
        comentarioServiceImp.deleteComentario(id, comentario);
        return ResponseEntity.noContent().build();
    }

}
