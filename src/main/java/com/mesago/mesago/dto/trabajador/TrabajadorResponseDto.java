package com.mesago.mesago.dto.trabajador;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrabajadorResponseDto {

    private Long id;
    private String nombre;
    private String dni;
    private String telefono;
    private String email;
    private LocalDate fechaIngreso;
    private String turno;
    private String estado;
    private String nombreRol;
}