package com.aluracursos.forohub.model;

import com.aluracursos.forohub.model.usuarios.DatosAutor;

import java.time.LocalDate;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, LocalDate fechaCreacion, Boolean status, DatosAutor autor) {
}
