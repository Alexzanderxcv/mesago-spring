package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.pedido.PedidoRequestDto;
import com.mesago.mesago.dto.pedido.PedidoResponseDto;
import com.mesago.mesago.entity.Pedido;
import com.mesago.mesago.mapper.pedido.PedidoMapper;
import com.mesago.mesago.repository.PedidoRepository;
import com.mesago.mesago.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final PedidoMapper mapper;

    @Override
    public PedidoResponseDto crear(PedidoRequestDto request) {
        Pedido entity = mapper.toEntity(request);
        Pedido saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public PedidoResponseDto obtenerPorId(Long id) {
        Pedido entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado: " + id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<PedidoResponseDto> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDto actualizar(Long id, PedidoRequestDto request) {
        Pedido entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado: " + id));
        mapper.updateEntityFromDto(request, entity);
        Pedido updated = repository.save(entity);
        return mapper.toResponseDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Pedido no encontrado: " + id);
        }
        repository.deleteById(id);
    }
}
