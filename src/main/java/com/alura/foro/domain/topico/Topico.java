package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	@Column(name = "fecha_de_creacion")
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	@Column(name = "estatus")
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
	private Curso curso;
	@OneToMany(mappedBy="topico", cascade = CascadeType.ALL)
	private List<Respuesta> respuestas = new ArrayList<>();
	private Boolean activo = true;

	public Topico(DTORegistrarTopico registrar) {
		this.titulo = registrar.titulo();
		this.mensaje = registrar.mensaje();
		this.usuario = new Usuario(registrar.usuario());
		this.curso = new Curso(registrar.curso());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void actualizarDatos(DTOActualizarTopico datosActualizar) {
		if(datosActualizar.titulo() != null) {
			this.titulo = datosActualizar.titulo();
		}
		if (datosActualizar.mensaje() != null) {
			this.mensaje = datosActualizar.mensaje();
		}
		if (datosActualizar.usuario() != null) {
			this.usuario = datosActualizar.usuario();
		}
		if (datosActualizar.curso() != null) {
			this.curso = datosActualizar.curso();
		}
	}

}
