package com.aluracursos.forohub.model.usuarios;

public record DatosAutor(Long id, String nombre) {
    public static DatosAutor fromUsuario(Usuario usuario) {
        return new DatosAutor(usuario.getId(), usuario.getNombre());
    }
}
