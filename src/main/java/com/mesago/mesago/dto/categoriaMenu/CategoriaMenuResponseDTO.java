package com.mesago.mesago.dto.categoriaMenu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaMenuResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
}
