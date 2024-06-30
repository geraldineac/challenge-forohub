package com.aluracursos.forohub.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosTopico(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDate fechaCreacion,
        @NotNull
        Boolean status
       // @NotNull
       // Usuario autor // El autor del topico es un tipo Usuario
        //Curso curso,
        //List<Respuesta> respuestas

        ) {
}
