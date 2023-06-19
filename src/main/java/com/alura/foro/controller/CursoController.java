package com.alura.foro.controller;

import com.alura.foro.domain.curso.*;
import com.alura.foro.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<DTOListarCursos> regitrarCurso(@RequestBody @Valid DTORegistrarCurso registro, UriComponentsBuilder uri) {
        Curso curso = cursoRepository.save(new Curso(registro));
        DTOListarCursos dtoRespuestaCursos = new DTOListarCursos(
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

    @PutMapping
    @Transactional
    public ResponseEntity<DTOListarCursos> actualizarTopico(@RequestBody @Valid DTOActualizarCurso datosActualizar) {
        Curso curso = cursoRepository.getReferenceById(datosActualizar.id());
        curso.actualizarDatos(datosActualizar);
            return ResponseEntity.ok( new DTOListarCursos(
                    curso.getId(),
                    curso.getNombre(),
                    curso.getCategoria()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
        return ResponseEntity.noContent().build();
    }
}
