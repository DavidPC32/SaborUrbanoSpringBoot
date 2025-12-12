package com.saborurbano.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Reaccion_comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaccionComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idReaccion;
    @Column(nullable = false, length = 100)
    private String tipoReaccion; // Ej: 'LIKE', 'UTIL', 'DISLIKE'

    // Relación ManyToOne con Comentario (FK: id_comentario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comentario")
    private Comentario comentario;

    // Relación ManyToOne con Usuario (FK: id_usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    public ReaccionComentario(String tipoReaccion, Comentario comentario, Usuarios usuario) {
        this.tipoReaccion = tipoReaccion;
        this.comentario = comentario;
        this.usuario = usuario;
    }

    
}