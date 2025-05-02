package com.mesago.mesago.controller;

import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoRequestDto;
import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoResponseDto;
import com.mesago.mesago.service.ProveedorInsumoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor-insumos")
@RequiredArgsConstructor
public class ProveedorInsumoController {

    private final ProveedorInsumoService service;

    @PostMapping
    public ProveedorInsumoResponseDto crear(@Valid @RequestBody ProveedorInsumoRequestDto dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<ProveedorInsumoResponseDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProveedorInsumoResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ProveedorInsumoResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody ProveedorInsumoRequestDto dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}

