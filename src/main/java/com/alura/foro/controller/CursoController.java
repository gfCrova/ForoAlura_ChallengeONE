package com.alura.foro.controller;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.DTOListarCursos;
import com.alura.foro.domain.curso.DTORegistrarCurso;
import com.alura.foro.domain.curso.DTORespuestaCursos;
import com.alura.foro.domain.topico.DTOListarTopicos;
import com.alura.foro.domain.topico.DTORegistrarTopico;
import com.alura.foro.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DTORespuestaCursos> regitrarCurso(@RequestBody @Valid DTORegistrarCurso registro, UriComponentsBuilder uri) {
        Curso curso = cursoRepository.save(new Curso(registro));
        DTORespuestaCursos dtoRespuestaCursos = new DTORespuestaCursos(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
        URI url = uri.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(dtoRespuestaCursos);
    }

    @GetMapping
    public List<DTOListarCursos> listadoCursos() {
        return cursoRepository.findAll().stream().map(DTOListarCursos::new).toList();
    }
}
