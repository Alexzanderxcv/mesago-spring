package com.mesago.mesago.dto.rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolResponseDto {

    private Long id;
    private String cargo;
}
