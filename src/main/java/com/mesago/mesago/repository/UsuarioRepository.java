package com.mesago.mesago.repository;

import com.mesago.mesago.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByUsername(String username);
    Usuario findByUsernameAndPassword(String username, String password);
}
