package com.mesago.mesago.dto.mesa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MesaResponseDto {

    private Long id;
    private Integer numero;
    private Integer capacidad;
    private String estado;
}
