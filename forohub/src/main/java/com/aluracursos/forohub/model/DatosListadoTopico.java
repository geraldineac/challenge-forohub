package com.aluracursos.forohub.model;

import java.time.LocalDate;

public record DatosListadoTopico(String titulo, String mensaje, LocalDate fechaCreacion) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion());
    }
}
