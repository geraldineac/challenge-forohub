package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.model.*;
import com.aluracursos.forohub.model.usuarios.DatosAutor;
import com.aluracursos.forohub.model.usuarios.Usuario;
import com.aluracursos.forohub.repository.TopicoRepository;
import com.aluracursos.forohub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(@PageableDefault(page = 0, size = 10) Pageable paginacion) {
       // return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerPorId(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico ->  ResponseEntity.ok(new DatosRespuestaTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.isStatus(),
                        DatosAutor.fromUsuario(topico.getAutor())
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder,
                                                                @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuario no autenticado");
        }

        // Obtener el usuario autenticado
        UserDetails usuarioDetails = usuarioRepository.findByLogin(userDetails.getUsername());
        if (usuarioDetails == null) {
            throw new UsernameNotFoundException("Usuario no encontrado en la base de datos");
        }

        // Convertir UserDetails a Usuario
        Usuario autor = (Usuario) usuarioDetails;

        // Crear el tópico
        Topico topico = new Topico(datosRegistroTopico, autor);

        // Guardar el tópico
        topico = topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.isStatus(),
                DatosAutor.fromUsuario(topico.getAutor()));
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.isStatus(),
                DatosAutor.fromUsuario(topico.getAutor())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        return topicoRepository.findById(id)
                .map(topico -> {
                    topicoRepository.delete(topico);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
