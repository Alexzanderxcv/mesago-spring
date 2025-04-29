package com.mesago.mesago.mapper.cliente;

import com.mesago.mesago.dto.cliente.ClienteRequestDto;
import com.mesago.mesago.dto.cliente.ClienteResponseDto;
import com.mesago.mesago.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteResponseDto toResponseDto(Cliente entity) {
        if (entity == null) {
            return null;
        }
        return ClienteResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .observaciones(entity.getObservaciones())
                .build();
    }

    public Cliente toEntity(ClienteRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Cliente.builder()
                .nombre(dto.getNombre())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .observaciones(dto.getObservaciones())
                .build();
    }

    public void updateEntityFromDto(ClienteRequestDto dto, Cliente entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }
        if (dto.getTelefono() != null) {
            entity.setTelefono(dto.getTelefono());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getObservaciones() != null) {
            entity.setObservaciones(dto.getObservaciones());
        }
    }
}
