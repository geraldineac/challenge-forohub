package com.aluracursos.forohub.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosActualizarTopico(@NotNull Long id, String titulo, String mensaje, LocalDate fechaCreacion, Boolean status) {
//    public DatosActualizarTopico(Topico topico) {
//        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.isStatus());
//    }
}
