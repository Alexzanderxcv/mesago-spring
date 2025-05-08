package com.mesago.mesago.mapper.usuario;

import com.mesago.mesago.dto.usuario.UsuarioRequestDto;
import com.mesago.mesago.dto.usuario.UsuarioResponseDto;
import com.mesago.mesago.entity.Trabajador;
import com.mesago.mesago.entity.Usuario;
import com.mesago.mesago.repository.TrabajadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final TrabajadorRepository trabajadorRepository;

    public Usuario toEntity(UsuarioRequestDto dto) {
        Trabajador trabajador = trabajadorRepository.findById(dto.getIdTrabajador())
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado: " + dto.getIdTrabajador()));

        return Usuario.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .estado(dto.getEstado())
                .trabajador(trabajador)
                .build();
    }

    public UsuarioResponseDto toResponseDto(Usuario entity) {
        return UsuarioResponseDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .estado(entity.getEstado())
                .idTrabajador(entity.getTrabajador().getId())
                .nombreTrabajador(entity.getTrabajador().getNombre())
                .build();
    }

    public void updateEntityFromDto(UsuarioRequestDto dto, Usuario entity) {
        if (dto.getUsername() != null) {
            entity.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            entity.setPassword(dto.getPassword());
        }
        if (dto.getEstado() != null) {
            entity.setEstado(dto.getEstado());
        }
        if (dto.getIdTrabajador() != null) {
            Trabajador trabajador = trabajadorRepository.findById(dto.getIdTrabajador())
                    .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado: " + dto.getIdTrabajador()));
            entity.setTrabajador(trabajador);
        }
    }
}