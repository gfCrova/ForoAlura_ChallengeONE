package com.alura.foro.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DTORegistrarUsuario(
        @NotBlank String nombre,
        @NotBlank String email,
        @NotBlank String contrasena) {
}
