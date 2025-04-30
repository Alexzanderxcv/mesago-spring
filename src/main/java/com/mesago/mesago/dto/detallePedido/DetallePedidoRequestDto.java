package com.mesago.mesago.dto.detallePedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoRequestDto {

    private Long idPedido;

    private Long idMenu;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subTotal;

}
