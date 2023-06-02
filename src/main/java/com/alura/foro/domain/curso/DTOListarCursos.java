package com.alura.foro.domain.curso;

import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DTOListarCursos(Long id, String nombre, String categoria) {

    public DTOListarCursos(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
