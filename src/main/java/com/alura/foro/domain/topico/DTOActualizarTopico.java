package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.usuario.Usuario;

public record DTOActualizarTopico(Long id, String titulo, String mensaje, Usuario usuario, Curso curso) {
}
