package com.saborurbano.restaurante.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Calificacion_platillo")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CalificacionPlatillo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificacion;

    @Column(nullable = false)
    private Integer puntuacion; // Asumiendo 1 a 5

    @Column(nullable = false, length = 255)
    private String comentarioCorto;

    // Relación ManyToOne con Usuario (FK: id_usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    // Relación ManyToOne con Platillo (FK: id_platillo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_platillo")
    private Platillo platillo;

    public CalificacionPlatillo(Integer puntuacion, String comentarioCorto, Usuarios usuarios, Platillo platillo) {
        this.puntuacion = puntuacion;
        this.comentarioCorto = comentarioCorto;
        this.usuario = usuarios;
        this.platillo = platillo;
    }

    
}