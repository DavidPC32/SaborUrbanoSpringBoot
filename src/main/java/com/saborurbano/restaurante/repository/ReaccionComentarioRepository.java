package com.saborurbano.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saborurbano.restaurante.model.ReaccionComentario;

@Repository
public interface ReaccionComentarioRepository extends JpaRepository<ReaccionComentario, Long> {
    boolean existsByComentarioIdComentarioAndUsuarioId(Integer idComentario, Integer idUsuario);
}
