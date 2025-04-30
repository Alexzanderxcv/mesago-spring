package com.mesago.mesago.mapper.rol;

import com.mesago.mesago.dto.rol.RolRequestDto;
import com.mesago.mesago.dto.rol.RolResponseDto;
import com.mesago.mesago.entity.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public RolResponseDto toResponseDto(Rol entity) {
        return RolResponseDto.builder()
                .id(entity.getId())
                .cargo(entity.getCargo())
                .build();
    }

    public Rol toEntity(RolRequestDto dto) {
        return Rol.builder()
                .cargo(dto.getCargo())
                .build();
    }

    public void updateEntity(RolRequestDto dto, Rol entity) {
        if (dto.getCargo() != null) {
            entity.setCargo(dto.getCargo());
        }
    }
}
