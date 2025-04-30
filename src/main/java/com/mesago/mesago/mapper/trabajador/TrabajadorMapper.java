package com.mesago.mesago.mapper.trabajador;

import com.mesago.mesago.dto.trabajador.TrabajadorRequestDto;
import com.mesago.mesago.dto.trabajador.TrabajadorResponseDto;
import com.mesago.mesago.entity.Rol;
import com.mesago.mesago.entity.Trabajador;
import com.mesago.mesago.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrabajadorMapper {

    private final RolRepository rolRepository;

    public Trabajador toEntity(TrabajadorRequestDto dto) {
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + dto.getIdRol()));

        return Trabajador.builder()
                .nombre(dto.getNombre())
                .dni(dto.getDni())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .fechaIngreso(dto.getFechaIngreso())
                .turno(dto.getTurno())
                .estado(dto.getEstado())
                .rol(rol)
                .build();
    }

    public TrabajadorResponseDto toDto(Trabajador entity) {
        return TrabajadorResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .dni(entity.getDni())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .fechaIngreso(entity.getFechaIngreso())
                .turno(entity.getTurno())
                .estado(entity.getEstado())
                .nombreRol(entity.getRol().getCargo())
                .build();
    }

    public void updateEntityFromDto(TrabajadorRequestDto dto, Trabajador entity) {
        entity.setNombre(dto.getNombre());
        entity.setDni(dto.getDni());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setFechaIngreso(dto.getFechaIngreso());
        entity.setTurno(dto.getTurno());
        entity.setEstado(dto.getEstado());

        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + dto.getIdRol()));
        entity.setRol(rol);
    }
}
