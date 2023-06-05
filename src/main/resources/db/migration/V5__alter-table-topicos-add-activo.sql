ALTER TABLE topicos ADD activo TINYINT;
UPDATE topicos SET activo = 1;