package com.mesago.mesago.service.impl;


import com.mesago.mesago.dto.factura.FacturaRequestDto;
import com.mesago.mesago.dto.factura.FacturaResponseDto;
import com.mesago.mesago.entity.Factura;
import com.mesago.mesago.mapper.factura.FacturaMapper;
import com.mesago.mesago.repository.FacturaRepository;
import com.mesago.mesago.service.FacturaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository repository;
    private final FacturaMapper mapper;

    @Override
    public FacturaResponseDto crear(FacturaRequestDto requestDto) {
        Factura entity =  mapper.toEntity(requestDto);
        Factura saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public FacturaResponseDto obtenerPorId(Long id) {
        Factura entity = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Factura no encontrada: " + id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<FacturaResponseDto> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacturaResponseDto actualizar(Long id, FacturaRequestDto requestDto) {
        Factura entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada: " + id));
        mapper.updateEntityFromDto(requestDto,entity);
        Factura updated = repository.save(entity);
        return mapper.toResponseDto(updated);

    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Factura no encontrada: " + id);
        }
        repository.deleteById(id);
    }
}
