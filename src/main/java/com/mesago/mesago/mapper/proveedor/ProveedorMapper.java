package com.mesago.mesago.mapper.proveedor;

import com.mesago.mesago.dto.proveedor.ProveedorRequestDto;
import com.mesago.mesago.dto.proveedor.ProveedorResponseDto;
import com.mesago.mesago.entity.Proveedor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Builder
public class ProveedorMapper {

    public ProveedorResponseDto toResponseDto(Proveedor entity) {
        if (entity == null) {
            return null;
        }

        return ProveedorResponseDto.builder()
                .id(entity.getId())
                .ruc(entity.getRuc())
                .nombre(entity.getNombre())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .estado(entity.getEstado())
                .build();
    }

    public Proveedor toEntity(ProveedorRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Proveedor.builder()
                .ruc(dto.getRuc())
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .estado(dto.getEstado())
                .build();
    }

    public void updateEntityFromDto(ProveedorRequestDto dto, Proveedor entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getRuc() != null) entity.setRuc(dto.getRuc());
        if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
        if (dto.getDireccion() != null) entity.setDireccion(dto.getDireccion());
        if (dto.getTelefono() != null) entity.setTelefono(dto.getTelefono());
        if (dto.getEstado() != null) entity.setEstado(dto.getEstado());
    }
}

