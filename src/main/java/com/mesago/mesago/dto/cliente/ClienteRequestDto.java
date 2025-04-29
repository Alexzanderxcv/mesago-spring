package com.mesago.mesago.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDto {

    private String nombre;
    private String telefono;
    private String email;
    private String observaciones;
}
