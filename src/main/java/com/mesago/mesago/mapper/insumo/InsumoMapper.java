package com.mesago.mesago.mapper.insumo;

import com.mesago.mesago.dto.insumo.InsumoRequestDto;
import com.mesago.mesago.dto.insumo.InsumoResponseDto;
import com.mesago.mesago.entity.Insumo;
import org.springframework.stereotype.Component;

@Component
public class InsumoMapper {

    public Insumo toEntity(InsumoRequestDto dto) {
        return Insumo.builder()
                .nombre(dto.getNombre())
                .unidadMedida(dto.getUnidadMedida())
                .stock(dto.getStock())
                .stockMinimo(dto.getStockMinimo())
                .estado(dto.getEstado())
                .build();
    }

    public InsumoResponseDto toDto(Insumo entity) {
        InsumoResponseDto dto = new InsumoResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setUnidadMedida(entity.getUnidadMedida());
        dto.setStock(entity.getStock());
        dto.setStockMinimo(entity.getStockMinimo());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    public void updateEntity(Insumo entity, InsumoRequestDto dto) {
        entity.setNombre(dto.getNombre());
        entity.setUnidadMedida(dto.getUnidadMedida());
        entity.setStock(dto.getStock());
        entity.setStockMinimo(dto.getStockMinimo());
        entity.setEstado(dto.getEstado());
    }
}