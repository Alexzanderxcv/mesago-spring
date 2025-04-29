package com.mesago.mesago.dto.categoriaMenu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaMenuResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
}
