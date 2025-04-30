package com.mesago.mesago.dto.mesa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesaRequestDto {

    private Integer numero;

    private Integer capacidad;

    private String estado;
}
