package com.mesago.mesago.controller;

import com.mesago.mesago.dto.insumo.InsumoRequestDto;
import com.mesago.mesago.dto.insumo.InsumoResponseDto;
import com.mesago.mesago.service.InsumoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService service;

    @PostMapping
    public InsumoResponseDto crear(@Valid @RequestBody InsumoRequestDto dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<InsumoResponseDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public InsumoResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public InsumoResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody InsumoRequestDto dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}