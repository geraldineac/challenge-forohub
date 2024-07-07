package com.aluracursos.forohub.model;

import com.aluracursos.forohub.model.usuarios.DatosAutor;

import java.time.LocalDate;

public record DatosListadoTopico(String titulo, String mensaje, LocalDate fechaCreacion, DatosAutor autor) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), DatosAutor.fromUsuario(topico.getAutor()));
    }
}
