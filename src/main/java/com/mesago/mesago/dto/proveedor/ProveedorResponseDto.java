package com.mesago.mesago.dto.proveedor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorResponseDto {
    private Long id;
    private String ruc;
    private String nombre;
    private String direccion;
    private String telefono;
    private String estado;
}

