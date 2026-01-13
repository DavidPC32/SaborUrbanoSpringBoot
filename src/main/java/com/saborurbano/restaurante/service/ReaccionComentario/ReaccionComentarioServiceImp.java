package com.saborurbano.restaurante.service.ReaccionComentario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saborurbano.restaurante.model.ReaccionComentario;
import com.saborurbano.restaurante.repository.ReaccionComentarioRepository;

@Service
public class ReaccionComentarioServiceImp implements ReaccionComentarioServiceInt {

    private final ReaccionComentarioRepository reaccionComentarioRepository;

    public ReaccionComentarioServiceImp(ReaccionComentarioRepository reaccionComentarioRepository) {
        this.reaccionComentarioRepository = reaccionComentarioRepository;
    }

    @Override
    public ReaccionComentario registrarReaccion(ReaccionComentario reaccion) {

        if (reaccion == null) {
            throw new IllegalArgumentException("La reacción no puede ser null.");
        }

        String tipoReaccion = reaccion.getTipoReaccion();
        if (tipoReaccion == null) {
            throw new IllegalArgumentException("El tipo de reacción es obligatorio.");
        }

        tipoReaccion = tipoReaccion.trim();
        if (tipoReaccion.isEmpty()) {
            throw new IllegalArgumentException("El tipo de reacción no puede estar vacío.");
        }

        if (reaccion.getComentario() == null) {
            throw new IllegalArgumentException("La reacción debe estar asociada a un comentario.");
        }

        if (reaccion.getUsuario() == null) {
            throw new IllegalArgumentException("La reacción debe estar asociada a un usuario.");
        }

        // Validar que el usuario no haya reaccionado ya al mismo comentario
        if (reaccionComentarioRepository.existsByComentarioIdComentarioAndUsuarioId(
                reaccion.getComentario().getIdComentario(), reaccion.getUsuario().getId())) {
            throw new IllegalArgumentException("El usuario ya ha reaccionado a este comentario.");
        }

        // Normalización
        reaccion.setTipoReaccion(tipoReaccion.toUpperCase());

        return reaccionComentarioRepository.save(reaccion);
    }

    @Override
    public List<ReaccionComentario> getAllReacciones() {
        return reaccionComentarioRepository.findAll();
    }

    @Override
    public ReaccionComentario getReaccionById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la reacción es inválido.");
        }

        return reaccionComentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la reacción con ID " + id));
    }

    @Override
    public void deleteReaccion(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id de la reacción es inválido.");
        }

        if (!reaccionComentarioRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe la reacción con ID " + id);
        }

        reaccionComentarioRepository.deleteById(id);
    }
}
