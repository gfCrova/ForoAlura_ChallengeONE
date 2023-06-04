package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;

public record DTORegistrarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        Usuario usuario,
        Curso curso) {
}
