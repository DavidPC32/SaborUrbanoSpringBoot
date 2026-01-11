package com.saborurbano.restaurante.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saborurbano.restaurante.model.CalificacionPlatillo;
import com.saborurbano.restaurante.service.CalificacionPlatillo.CalificacionPlatilloServiceImp;

@RequestMapping("api/calificaciones")
@RestController
public class CalificacionPlatilloController {

    private final CalificacionPlatilloServiceImp calificacionServiceImp;

    public CalificacionPlatilloController(CalificacionPlatilloServiceImp calificacionServiceImp) {
        this.calificacionServiceImp = calificacionServiceImp;
    }

    @GetMapping
    public List<CalificacionPlatillo> getAllCalificaciones() {
        return calificacionServiceImp.getAllCalificaciones();
    }

    @GetMapping("/{id}")
    public CalificacionPlatillo getCalificacionById(@PathVariable Long id) {
        return calificacionServiceImp.getCalificacionById(id);
    }

    @PostMapping("/{idUsuario}/{idPlatillo}")
    public ResponseEntity<CalificacionPlatillo> crearCalificacion(
            @RequestBody CalificacionPlatillo calificacion,
            @PathVariable Integer idUsuario,
            @PathVariable Long idPlatillo
    ) {
        CalificacionPlatillo nueva = calificacionServiceImp.registrarCalificacion(calificacion, idUsuario, idPlatillo);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable Long id) {
        calificacionServiceImp.deleteCalificacion(id);
        return ResponseEntity.noContent().build();
    }
}
