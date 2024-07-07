package com.aluracursos.forohub.repository;

import com.aluracursos.forohub.model.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String username);
   // Optional<Usuario> findByLogin(String username);
}
