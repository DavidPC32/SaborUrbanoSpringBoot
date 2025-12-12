package com.saborurbano.restaurante.model;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Platillo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Platillo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlatillo;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;


    @Column(nullable = false, length = 100)
    private Double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "platillo")
    private Set<CalificacionPlatillo> calificaciones;

    public Platillo(String nombre, Double precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }
    

    



}