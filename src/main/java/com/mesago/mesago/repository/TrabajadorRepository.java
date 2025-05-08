package com.mesago.mesago.repository;

import com.mesago.mesago.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {
    @Query("SELECT t FROM Trabajador t WHERE t.id NOT IN (SELECT u.trabajador.id FROM Usuario u)")
    List<Trabajador> findTrabajadoresSinUsuario();

}
