package com.mesago.mesago.dto.factura;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaResponseDto {

    private Long id;
    private LocalDateTime fechaEmision;
    private BigDecimal montoTotal;
    private String metodoPago;
    private String estado;
    private String numeroFactura;
    private String tipo;
    private Long idPedido;

}
