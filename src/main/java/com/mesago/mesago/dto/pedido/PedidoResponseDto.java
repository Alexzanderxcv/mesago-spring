package com.mesago.mesago.dto.pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDto {

    private Long id;
    private LocalDateTime fecha;
    private String estado;
    private BigDecimal total;
    private Integer tiempoPreparado;
    private Long idTrabajador;
    private Long idMesa;
    private Long idCliente;
}
