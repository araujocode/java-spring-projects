package com.tttgerenciadorusuarios.springexercicio2.repositories;

import com.tttgerenciadorusuarios.springexercicio2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
