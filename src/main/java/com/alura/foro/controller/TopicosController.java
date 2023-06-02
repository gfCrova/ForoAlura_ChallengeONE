package com.alura.foro.controller;

import com.alura.foro.domain.curso.DTORegistrarCurso;
import com.alura.foro.domain.curso.DatosCurso;
import com.alura.foro.domain.topico.DTOListarTopicos;
import com.alura.foro.domain.topico.DTORegistrarTopico;
import com.alura.foro.domain.respuesta.DTORespuestaTopico;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.DatosUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.alura.foro.repository.TopicoRepository;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DTORespuestaTopico> registrarTopico(@RequestBody @Valid DTORegistrarTopico registro, UriComponentsBuilder uri) {
        System.out.println("El request llega correctamente!");
        System.out.println(registro);
        Topico topico = topicoRepository.save(new Topico(registro));
        DTORespuestaTopico datosDeRespuestaTopico = new DTORespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getUsuario(),
                topico.getCurso()
        );
        URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosDeRespuestaTopico);
    }

    @GetMapping
    public List<DTOListarTopicos> listadoTopicos() {
        return topicoRepository.findAll().stream().map(DTOListarTopicos::new).toList();
    }
}
