package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
}
