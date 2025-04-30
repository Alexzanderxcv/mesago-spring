package com.mesago.mesago.dto.reserva;


import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaResponseDto {

    private Long id;
    private LocalDate fechaReserva;
    private LocalTime hora;
    private Integer numeroPersonas;
    private String estado;
    private Long idCliente;
    private Long idTrabajador;
    private Long idMesa;
}