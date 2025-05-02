package com.mesago.mesago.dto.proveedorInsumo;

import jakarta.validation.constraints.NotNull;
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
public class ProveedorInsumoRequestDto {
    @NotNull
    private BigDecimal precioUnitario;

    private LocalDate fechaEntrega;

    @NotNull
    private Integer cantidad;

    @NotNull
    private Long idProveedor;

    @NotNull
    private Long idInsumo;
}
