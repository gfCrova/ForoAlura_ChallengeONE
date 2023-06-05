package com.alura.foro.domain.curso;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "curso")
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;

	public Curso(DTORegistrarCurso registro) {
		this.nombre = registro.nombre();
		this.categoria = registro.categoria();
	}

	public Curso(@NotNull @Valid Curso curso) {
		this(curso.getId(), curso.getNombre(), curso.getCategoria());
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
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void actualizarDatos(@Valid DTOActualizarCurso datosActualizar) {
		if (datosActualizar.nombre() != null) {
			this.nombre = datosActualizar.nombre();
		}
		if (datosActualizar.categoria() != null) {
			this.categoria = datosActualizar.categoria();
		}
	}
}
