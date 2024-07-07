package com.aluracursos.forohub.model;

import com.aluracursos.forohub.model.usuarios.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DatosRegistroTopico(
        @NotNull
        Long autorId,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDate fechaCreacion,
        @NotNull
        Boolean status
) {
}
