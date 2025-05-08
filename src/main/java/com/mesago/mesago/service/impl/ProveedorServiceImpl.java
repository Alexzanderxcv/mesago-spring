package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.proveedor.ProveedorRequestDto;
import com.mesago.mesago.dto.proveedor.ProveedorResponseDto;
import com.mesago.mesago.entity.Proveedor;
import com.mesago.mesago.mapper.proveedor.ProveedorMapper;
import com.mesago.mesago.repository.ProveedorRepository;
import com.mesago.mesago.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;

    @Override
    public ProveedorResponseDto crear(ProveedorRequestDto dto) {
        Proveedor proveedor = mapper.toEntity(dto);
        return mapper.toResponseDto(repository.save(proveedor));
    }

    @Override
    public Page<ProveedorResponseDto> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponseDto);
    }

    @Override
    public ProveedorResponseDto obtenerPorId(Long id) {
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return mapper.toResponseDto(proveedor);
    }

    @Override
    public ProveedorResponseDto actualizar(Long id, ProveedorRequestDto dto) {
        Proveedor proveedor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        mapper.updateEntityFromDto(dto, proveedor);
        return mapper.toResponseDto(repository.save(proveedor));
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
