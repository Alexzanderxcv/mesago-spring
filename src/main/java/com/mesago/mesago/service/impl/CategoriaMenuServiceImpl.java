package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuRequestDto;
import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuResponseDto;
import com.mesago.mesago.entity.CategoriaMenu;
import com.mesago.mesago.mapper.categoriaMenu.CategoriaMenuMapper;
import com.mesago.mesago.repository.CategoriaMenuRepository;
import com.mesago.mesago.service.CategoriaMenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaMenuServiceImpl implements CategoriaMenuService {

    private final CategoriaMenuRepository repository;
    private final CategoriaMenuMapper mapper;

    @Override
    public CategoriaMenuResponseDto crear(CategoriaMenuRequestDto request) {
        CategoriaMenu entity = mapper.toEntity(request);
        CategoriaMenu saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public CategoriaMenuResponseDto obtenerPorId(Long id) {
        CategoriaMenu entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<CategoriaMenuResponseDto> listarTodas() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaMenuResponseDto actualizar(Long id, CategoriaMenuRequestDto request) {
        CategoriaMenu entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + id));
        mapper.updateEntityFromDto(request, entity);
        CategoriaMenu updated = repository.save(entity);
        return mapper.toResponseDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada: " + id);
        }
        repository.deleteById(id);
    }
}
