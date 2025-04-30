package com.mesago.mesago.mapper.mesa;

import com.mesago.mesago.dto.mesa.MesaRequestDto;
import com.mesago.mesago.dto.mesa.MesaResponseDto;
import com.mesago.mesago.entity.Mesa;
import org.springframework.stereotype.Component;

@Component
public class MesaMapper {

    public MesaResponseDto toResponseDto(Mesa entity) {
        return MesaResponseDto.builder()
                .id(entity.getId())
                .numero(entity.getNumero())
                .capacidad(entity.getCapacidad())
                .estado(entity.getEstado())
                .build();
    }

    public Mesa toEntity(MesaRequestDto dto) {
        return Mesa.builder()
                .numero(dto.getNumero())
                .capacidad(dto.getCapacidad())
                .estado(dto.getEstado())
                .build();
    }

    public void updateEntityFromDto(MesaRequestDto dto, Mesa entity) {
        if (dto.getNumero() != null) entity.setNumero(dto.getNumero());
        if (dto.getCapacidad() != null) entity.setCapacidad(dto.getCapacidad());
        if (dto.getEstado() != null) entity.setEstado(dto.getEstado());
    }
}