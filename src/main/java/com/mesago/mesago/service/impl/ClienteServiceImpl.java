package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.cliente.ClienteRequestDto;
import com.mesago.mesago.dto.cliente.ClienteResponseDto;
import com.mesago.mesago.entity.Cliente;
import com.mesago.mesago.mapper.cliente.ClienteMapper;
import com.mesago.mesago.repository.ClienteRepository;
import com.mesago.mesago.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Override
    public ClienteResponseDto crear(ClienteRequestDto request) {
        Cliente entity = mapper.toEntity(request);
        Cliente saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public ClienteResponseDto obtenerPorId(Long id) {
        Cliente entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<ClienteResponseDto> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDto actualizar(Long id, ClienteRequestDto request) {
        Cliente entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + id));
        mapper.updateEntityFromDto(request, entity);
        Cliente updated = repository.save(entity);
        return mapper.toResponseDto(updated);
    }

    @Override
    public ClienteResponseDto obtenerPorNombre(String nombre) {
        Cliente entity = repository.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + nombre));
        return mapper.toResponseDto(entity);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado: " + id);
        }
        repository.deleteById(id);
    }
    @Override
    public long count() {
        return repository.count();
    }
}
