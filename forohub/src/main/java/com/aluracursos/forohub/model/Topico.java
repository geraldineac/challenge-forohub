package com.aluracursos.forohub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
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
    @ManyToOne(cascade = CascadeType.ALL)
    //   @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonManagedReference
    private Usuario autor;

    public Topico(DatosTopico datosTopico){
        this.id = datosTopico.id();
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.status = true;
      //  this.autor = datosTopico.autor();
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
