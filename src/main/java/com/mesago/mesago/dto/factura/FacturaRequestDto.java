package com.mesago.mesago.dto.factura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaRequestDto {

    private BigDecimal montoTotal;
    private String metodoPago;
    private String estado;
    private String numeroFactura;
    private String tipo;
    private Long idPedido;

}