package com.mesago.mesago.dto.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDto {

    private LocalDateTime fecha;

    private String estado;

    private BigDecimal total;

    private Integer tiempoPreparado;

    private Long idTrabajador;

    private Long idMesa;

    private Long idCliente;
}
