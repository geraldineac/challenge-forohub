package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.model.DatosActualizarTopico;
import com.aluracursos.forohub.model.DatosListadoTopico;
import com.aluracursos.forohub.model.DatosTopico;
import com.aluracursos.forohub.model.Topico;
import com.aluracursos.forohub.repository.TopicoRepository;
import com.aluracursos.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<DatosListadoTopico> listar(@PageableDefault(page = 0, size = 10) Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public DatosTopico obtenerPorId(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> new DatosTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.isStatus()
                     //   topico.getAutor()
                ))
                .orElse(null);
    }

    @PostMapping
    @Transactional
    public void registrarTopico(@RequestBody  DatosTopico datos) {
        topicoRepository.save(new Topico(datos));
    }

    @PutMapping
    @Transactional
    public void actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
    }
//@PutMapping("/{id}")
//@Transactional
//public DatosTopico actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosTopico datosTopico) {
//    System.out.println("ID recibido: " + id);
//    System.out.println("Datos recibidos: " + datosTopico);
//
//    Topico topico = topicoRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
//
//    System.out.println("Tópico encontrado: " + topico);
//
//    topico.actualizarDatos(datosTopico);
//
//    Topico topicoActualizado = topicoRepository.save(topico);
//
//    System.out.println("Tópico después de actualizar: " + topicoActualizado);
//
//    return new DatosTopico(
//            topicoActualizado.getId(),
//            topicoActualizado.getTitulo(),
//            topicoActualizado.getMensaje(),
//            topicoActualizado.getFechaCreacion(),
//            topicoActualizado.isStatus()
//        //    topicoActualizado.getAutor()
//
//    );
//}
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
    }
}
