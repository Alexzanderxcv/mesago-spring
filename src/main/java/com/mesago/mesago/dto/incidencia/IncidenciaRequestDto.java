package com.mesago.mesago.dto.incidencia;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidenciaRequestDto {

    private String descripcion;
    private String estado;
    private String tipo;
    private Long idTrabajador; // referencia al trabajador que la reporta
}