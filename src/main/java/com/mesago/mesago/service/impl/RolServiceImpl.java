package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.rol.RolRequestDto;
import com.mesago.mesago.dto.rol.RolResponseDto;
import com.mesago.mesago.entity.Rol;
import com.mesago.mesago.mapper.rol.RolMapper;
import com.mesago.mesago.repository.RolRepository;
import com.mesago.mesago.service.RolService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository repository;
    private final RolMapper mapper;

    @Override
    public RolResponseDto crear(RolRequestDto request) {
        Rol rol = mapper.toEntity(request);
        return mapper.toResponseDto(repository.save(rol));
    }

    @Override
    public RolResponseDto obtenerPorId(Long id) {
        Rol rol = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado: " + id));
        return mapper.toResponseDto(rol);
    }

    @Override
    public List<RolResponseDto> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RolResponseDto actualizar(Long id, RolRequestDto request) {
        Rol rol = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado: " + id));
        mapper.updateEntity(request, rol);
        return mapper.toResponseDto(repository.save(rol));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Rol no encontrado: " + id);
        }
        repository.deleteById(id);
    }

}
