package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.mesa.MesaRequestDto;
import com.mesago.mesago.dto.mesa.MesaResponseDto;
import com.mesago.mesago.entity.Mesa;
import com.mesago.mesago.mapper.mesa.MesaMapper;
import com.mesago.mesago.repository.MesaRepository;
import com.mesago.mesago.service.MesaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MesaServiceImpl implements MesaService {

    private final MesaRepository repository;
    private final MesaMapper mapper;

    @Override
    public MesaResponseDto crear(MesaRequestDto request) {
        Mesa mesa = mapper.toEntity(request);
        return mapper.toResponseDto(repository.save(mesa));
    }

    @Override
    public MesaResponseDto obtenerPorId(Long id) {
        Mesa mesa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada: " + id));
        return mapper.toResponseDto(mesa);
    }

    @Override
    public List<MesaResponseDto> listarTodas() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MesaResponseDto actualizar(Long id, MesaRequestDto request) {
        Mesa mesa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada: " + id));
        mapper.updateEntityFromDto(request, mesa);
        return mapper.toResponseDto(repository.save(mesa));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Mesa no encontrada: " + id);
        }
        repository.deleteById(id);
    }
}