package com.mesago.mesago.dto.detallePedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedidoResponseDto {

    private Long id;
    private Long idPedido;
    private Long idMenu;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subTotal;
}
