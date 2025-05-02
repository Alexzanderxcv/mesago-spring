package com.mesago.mesago.dto.proveedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorRequestDto {
    @NotBlank
    @Size(max = 20)
    private String ruc;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 255)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    @Size(max = 20)
    private String estado;
}
