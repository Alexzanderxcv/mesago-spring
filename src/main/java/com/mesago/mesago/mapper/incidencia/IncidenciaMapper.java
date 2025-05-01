package com.mesago.mesago.mapper.incidencia;

import com.mesago.mesago.dto.incidencia.IncidenciaRequestDto;
import com.mesago.mesago.dto.incidencia.IncidenciaResponseDto;
import com.mesago.mesago.entity.Incidencia;
import com.mesago.mesago.repository.TrabajadorRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Builder
public class IncidenciaMapper {

    private final TrabajadorRepository trabajadorRepository;

    public IncidenciaResponseDto toResponseDto(Incidencia entity) {
        if (entity == null) {
            return null;
        }

        return IncidenciaResponseDto.builder()
                .id(entity.getId())
                .descripcion(entity.getDescripcion())
                .fechaRegistro(entity.getFechaRegistro())
                .estado(entity.getEstado())
                .tipo(entity.getTipo())
                .idTrabajador(entity.getTrabajador().getId())
                .nombreTrabajador(entity.getTrabajador().getNombre())
                .build();
    }

    public Incidencia toEntity(IncidenciaRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Incidencia.builder()
                .descripcion(dto.getDescripcion())
                .estado(dto.getEstado())
                .tipo(dto.getTipo())
                .fechaRegistro(LocalDateTime.now())
                .trabajador(trabajadorRepository.findById(dto.getIdTrabajador())
                        .orElseThrow(() -> new RuntimeException("Trabajador no encontrado")))
                .build();
    }

    public void updateEntityFromDto(IncidenciaRequestDto dto, Incidencia entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getDescripcion() != null) {
            entity.setDescripcion(dto.getDescripcion());
        }
        if (dto.getEstado() != null) {
            entity.setEstado(dto.getEstado());
        }
        if (dto.getTipo() != null) {
            entity.setTipo(dto.getTipo());
        }
        if (dto.getIdTrabajador() != null) {
            entity.setTrabajador(trabajadorRepository.findById(dto.getIdTrabajador())
                    .orElseThrow(() -> new RuntimeException("Trabajador no encontrado")));
        }
    }
}
