package com.mesago.mesago.dto.trabajador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadorRequestDto {

    private String nombre;

    private String dni;

    private String telefono;

    private String email;

    private LocalDate fechaIngreso;

    private String turno;

    private String estado;

    private Long idRol;

}