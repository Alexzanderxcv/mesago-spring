package com.mesago.mesago.repository;

import com.mesago.mesago.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m WHERE m.categoria.nombre = :nombre")
    List<Menu> findByCategoriaNombre(@Param("nombre") String nombre);
}
