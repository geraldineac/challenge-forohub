package com.aluracursos.forohub.model;

import com.aluracursos.forohub.model.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;
@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;
    @Column(name = "mensaje", nullable = false, unique = true)
    private String mensaje;
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDate fechaCreacion;
    private boolean status;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    @JsonManagedReference
    private Usuario autor;

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario autor){
        //this.id = datosRegistroTopico.id();
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        //this.fechaCreacion = LocalDate.now();
        this.status = true;
        // No asignamos el ID aquí, lo generará la base de datos
        this.autor = autor;
    }


    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDate.now();
    }

    public void actualizarDatos(@Valid DatosActualizarTopico datosTopico) {
        if (datosTopico.titulo() != null) {
            this.titulo = datosTopico.titulo();
        }
        if(datosTopico.mensaje() != null){
            this.mensaje = datosTopico.mensaje();
        }
    }
}
