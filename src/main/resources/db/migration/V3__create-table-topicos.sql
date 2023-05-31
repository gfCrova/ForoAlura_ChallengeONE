CREATE TABLE topicos (
  id INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(155) NOT NULL,
  mensaje VARCHAR(255) NOT NULL,
  fecha_de_creacion DATETIME NOT NULL,
  estatus VARCHAR(155) NOT NULL,
  usuario_id INT NOT NULL,
  curso_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos (id),
  CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
  );