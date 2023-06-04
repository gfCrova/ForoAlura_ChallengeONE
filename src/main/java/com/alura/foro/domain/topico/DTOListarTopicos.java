package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DTOListarTopicos(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String status, String usuario, String curso) {

    public DTOListarTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus().toString(), topico.getUsuario().getNombre(), topico.getCurso().getNombre());
    }
}
