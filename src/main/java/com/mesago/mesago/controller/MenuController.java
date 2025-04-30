package com.mesago.mesago.controller;

import com.mesago.mesago.dto.menu.MenuRequestDto;
import com.mesago.mesago.dto.menu.MenuResponseDto;
import com.mesago.mesago.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuResponseDto crear(@Valid @RequestBody MenuRequestDto dto) {
        return service.crear(dto);
    }

    @GetMapping("/{id}")
    public MenuResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<MenuResponseDto> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public MenuResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody MenuRequestDto dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
