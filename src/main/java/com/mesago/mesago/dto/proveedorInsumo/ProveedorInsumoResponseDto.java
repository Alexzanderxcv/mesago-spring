package com.mesago.mesago.dto.proveedorInsumo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorInsumoResponseDto {
    private Long id;
    private BigDecimal precioUnitario;
    private LocalDate fechaEntrega;
    private Integer cantidad;

    private Long idProveedor;
    private String nombreProveedor;

    private Long idInsumo;
    private String nombreInsumo;
}

