package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.detallePedido.DetallePedidoRequestDto;
import com.mesago.mesago.dto.detallePedido.DetallePedidoResponseDto;
import com.mesago.mesago.entity.DetallePedido;
import com.mesago.mesago.mapper.detallePedido.DetallePedidoMapper;
import com.mesago.mesago.repository.DetallePedidoRepository;
import com.mesago.mesago.service.DetallePedidoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository repository;
    private final DetallePedidoMapper mapper;

    @Override
    public DetallePedidoResponseDto crear(DetallePedidoRequestDto request) {
        DetallePedido entity = mapper.toEntity(request);
        DetallePedido saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public DetallePedidoResponseDto obtenerPorId(Long id) {
        DetallePedido entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DetallePedido no encontrado: " + id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<DetallePedidoResponseDto> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DetallePedidoResponseDto actualizar(Long id, DetallePedidoRequestDto request) {
        DetallePedido entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DetallePedido no encontrado: " + id));
        mapper.updateEntityFromDto(request, entity);
        DetallePedido updated = repository.save(entity);
        return mapper.toResponseDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("DetallePedido no encontrado: " + id);
        }
        repository.deleteById(id);
    }

}
