package com.mesago.mesago.dto.usuario;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDto {

    private Long id;
    private String username;
    private String estado;
    private Long idTrabajador;
    private String nombreTrabajador;
}