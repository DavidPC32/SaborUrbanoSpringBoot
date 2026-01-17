package com.saborurbano.restaurante.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Usuarios")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombreCompleto;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario-comentarios")
    private Set<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario-calificaciones")
    private Set<CalificacionPlatillo> calificaciones;

    public Usuarios(String nombreCompleto, String email) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }

}
