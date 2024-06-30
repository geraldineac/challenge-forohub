package com.aluracursos.forohub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    //private List<Perfil> perfiles


    public Usuario(DatosUsuario datosUsuario){
        this.nombre = datosUsuario.nombre();
        this.correoElectronico = datosUsuario.correoElectronico();
        this.contrasena = datosUsuario.contrasena();
    }

}
