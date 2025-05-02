package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoRequestDto;
import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoResponseDto;
import com.mesago.mesago.entity.ProveedorInsumo;
import com.mesago.mesago.mapper.proveedorInsumo.ProveedorInsumoMapper;
import com.mesago.mesago.repository.ProveedorInsumoRepository;
import com.mesago.mesago.service.ProveedorInsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorInsumoServiceImpl implements ProveedorInsumoService {

    private final ProveedorInsumoRepository repository;
    private final ProveedorInsumoMapper mapper;

    @Override
    public ProveedorInsumoResponseDto crear(ProveedorInsumoRequestDto dto) {
        ProveedorInsumo entity = mapper.toEntity(dto);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public List<ProveedorInsumoResponseDto> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorInsumoResponseDto obtenerPorId(Long id) {
        ProveedorInsumo entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProveedorInsumo no encontrado"));
        return mapper.toResponseDto(entity);
    }

    @Override
    public ProveedorInsumoResponseDto actualizar(Long id, ProveedorInsumoRequestDto dto) {
        ProveedorInsumo entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProveedorInsumo no encontrado"));
        mapper.updateEntityFromDto(dto, entity);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
