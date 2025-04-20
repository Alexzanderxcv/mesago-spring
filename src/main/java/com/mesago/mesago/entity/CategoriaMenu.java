package com.mesago.mesago.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_menu")
    private Long id;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion;
}
