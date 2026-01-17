package com.saborurbano.restaurante.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private int id;
    @NotEmpty
    @Size(min = 3, max = 100)
    private String nombreCompleto;
    @NotEmpty
    @Email
    private String email;
}
