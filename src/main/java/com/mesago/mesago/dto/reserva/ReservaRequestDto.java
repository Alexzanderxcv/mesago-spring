package com.mesago.mesago.dto.reserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequestDto {

    private LocalDate fechaReserva;

    private LocalTime hora;

    private Integer numeroPersonas;

    private String estado;

    private Long idCliente;

    private Long idTrabajador;

    private Long idMesa;
}