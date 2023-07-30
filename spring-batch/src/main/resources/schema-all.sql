DROP TABLE persona IF EXISTS;

CREATE TABLE persona(
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    primer_nombre VARCHAR(),
    segundo_nombre VARCHAR(),
    telefono VARCHAR()
);