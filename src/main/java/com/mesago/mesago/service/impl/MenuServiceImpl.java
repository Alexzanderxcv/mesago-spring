package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.menu.MenuRequestDto;
import com.mesago.mesago.dto.menu.MenuResponseDto;
import com.mesago.mesago.entity.Menu;
import com.mesago.mesago.mapper.menu.MenuMapper;
import com.mesago.mesago.repository.MenuRepository;
import com.mesago.mesago.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repository;
    private final MenuMapper mapper;

    @Override
    public MenuResponseDto crear(MenuRequestDto dto) {
        Menu menu = mapper.toEntity(dto);
        return mapper.toResponseDto(repository.save(menu));
    }

    @Override
    public MenuResponseDto obtenerPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Menú no encontrado: " + id));
    }

    @Override
    public List<MenuResponseDto> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenuResponseDto actualizar(Long id, MenuRequestDto dto) {
        Menu menu = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Menú no encontrado: " + id));
        mapper.updateEntity(dto, menu);
        return mapper.toResponseDto(repository.save(menu));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Menú no encontrado: " + id);
        }
        repository.deleteById(id);
    }
}
