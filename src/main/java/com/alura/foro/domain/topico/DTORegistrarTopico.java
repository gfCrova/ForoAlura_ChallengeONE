package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTORegistrarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull @Valid Usuario usuario,
        @NotNull @Valid Curso curso) {
}
