package com.mesago.mesago.repository;

import com.mesago.mesago.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    Usuario findByUsernameAndPassword(String username, String password);
}
