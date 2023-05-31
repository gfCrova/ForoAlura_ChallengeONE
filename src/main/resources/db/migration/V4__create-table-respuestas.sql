CREATE TABLE respuestas (
  id INT NOT NULL AUTO_INCREMENT,
  mensaje VARCHAR(255) NOT NULL,
  fecha_de_creacion DATETIME NOT NULL,
  solucion BOOLEAN NOT NULL,
  topico_id INT NOT NULL,
  usuario_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_topico FOREIGN KEY (topico_id) REFERENCES topicos (id),
  CONSTRAINT fk_usuario_respuesta FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);