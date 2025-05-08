package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.insumo.InsumoRequestDto;
import com.mesago.mesago.dto.insumo.InsumoResponseDto;
import com.mesago.mesago.entity.Insumo;
import com.mesago.mesago.mapper.insumo.InsumoMapper;
import com.mesago.mesago.repository.InsumoRepository;
import com.mesago.mesago.service.InsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsumoServiceImpl implements InsumoService {

    private final InsumoRepository repository;
    private final InsumoMapper mapper;

    @Override
    public InsumoResponseDto crear(InsumoRequestDto dto) {
        Insumo insumo = mapper.toEntity(dto);
        return mapper.toDto(repository.save(insumo));
    }

    @Override
    public List<InsumoResponseDto> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InsumoResponseDto obtenerPorId(Long id) {
        Insumo insumo = repository.findById(id).orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
        return mapper.toDto(insumo);
    }

    @Override
    public InsumoResponseDto actualizar(Long id, InsumoRequestDto dto) {
        Insumo insumo = repository.findById(id).orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
        mapper.updateEntity(insumo, dto);
        return mapper.toDto(repository.save(insumo));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
