package com.mesago.mesago.dto.insumo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsumoRequestDto {

    private String nombre;

    private String unidadMedida;

    private Integer stock;

    private Integer stockMinimo;

    private String estado;

}