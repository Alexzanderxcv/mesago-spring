package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.trabajador.TrabajadorRequestDto;
import com.mesago.mesago.dto.trabajador.TrabajadorResponseDto;
import com.mesago.mesago.entity.Trabajador;
import com.mesago.mesago.mapper.trabajador.TrabajadorMapper;
import com.mesago.mesago.repository.TrabajadorRepository;
import com.mesago.mesago.service.TrabajadorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository repository;
    private final TrabajadorMapper mapper;

    @Override
    public TrabajadorResponseDto crear(TrabajadorRequestDto request) {
        Trabajador trabajador = mapper.toEntity(request);
        return mapper.toDto(repository.save(trabajador));
    }

    @Override
    public TrabajadorResponseDto obtenerPorId(Long id) {
        Trabajador trabajador = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado: " + id));
        return mapper.toDto(trabajador);
    }

    @Override
    public List<TrabajadorResponseDto> listar() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrabajadorResponseDto actualizar(Long id, TrabajadorRequestDto request) {
        Trabajador trabajador = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado: " + id));
        mapper.updateEntityFromDto(request, trabajador);
        return mapper.toDto(repository.save(trabajador));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Trabajador no encontrado: " + id);
        }
        repository.deleteById(id);
    }
}
