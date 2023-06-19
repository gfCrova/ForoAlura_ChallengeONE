package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DTORespuestaTopico(
         Long id,
         String titulo,
         String mensaje,
         LocalDateTime fechaCreacion,
         StatusTopico status,
         Usuario usuario,
         Curso curso) {
}
