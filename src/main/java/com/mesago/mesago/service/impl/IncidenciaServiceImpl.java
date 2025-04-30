package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.incidencia.IncidenciaRequestDto;
import com.mesago.mesago.dto.incidencia.IncidenciaResponseDto;
import com.mesago.mesago.entity.Incidencia;
import com.mesago.mesago.mapper.incidencia.IncidenciaMapper;
import com.mesago.mesago.repository.IncidenciaRepository;
import com.mesago.mesago.service.IncidenciaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository repository;
    private final IncidenciaMapper mapper;

    @Override
    public IncidenciaResponseDto crear(IncidenciaRequestDto requestDto) {
        Incidencia entity = mapper.toEntity(requestDto);
        Incidencia saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public IncidenciaResponseDto obtenerPorId(Long id) {
        Incidencia entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada: " + id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<IncidenciaResponseDto> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public IncidenciaResponseDto actualizar(Long id, IncidenciaRequestDto requestDto) {
        Incidencia entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada: " + id));
        mapper.updateEntityFromDto(requestDto, entity);
        Incidencia updated = repository.save(entity);
        return mapper.toResponseDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Incidencia no encontrada: " + id);
        }
        repository.deleteById(id);
    }
}
