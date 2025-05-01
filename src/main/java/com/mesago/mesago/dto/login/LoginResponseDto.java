package com.mesago.mesago.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponseDto {

    private Long id;
    private String username;
    private String nombreTrabajador;
    private String cargo;
    private String token;
}
