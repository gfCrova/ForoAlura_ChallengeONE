package com.alura.foro.controller;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.DTOActualizarCurso;
import com.alura.foro.domain.curso.DTOListarCursos;
import com.alura.foro.domain.usuario.DTOActualizarUsuario;
import com.alura.foro.domain.usuario.DTOListarUsuarios;
import com.alura.foro.domain.usuario.DTORegistrarUsuario;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<DTOListarUsuarios> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(DTOListarUsuarios::new).toList();
    }

    @PostMapping
    public ResponseEntity<DTOListarUsuarios> regitrarCurso(@RequestBody @Valid DTORegistrarUsuario registro, UriComponentsBuilder uri) {
        Usuario usuario = usuarioRepository.save(new Usuario(registro));
        DTOListarUsuarios respuestaUsuarios = new DTOListarUsuarios(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        URI url = uri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaUsuarios);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DTOActualizarUsuario> actualizarTopico(@RequestBody @Valid DTOActualizarUsuario datosActualizar) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizar.id());
        usuario.actualizarDatos(datosActualizar);
        return ResponseEntity.ok( new DTOActualizarUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
