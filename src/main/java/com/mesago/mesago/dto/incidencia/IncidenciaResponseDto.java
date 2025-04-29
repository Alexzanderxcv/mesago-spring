package com.mesago.mesago.dto.incidencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidenciaResponseDto {

    private Long id;
    private String descripcion;
    private LocalDateTime fechaRegistro;
    private String estado;
    private String tipo;
    private Long idTrabajador;
    private String nombreTrabajador;
}
