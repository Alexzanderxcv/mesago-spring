package com.mesago.mesago.controller;

import com.mesago.mesago.dto.proveedor.ProveedorRequestDto;
import com.mesago.mesago.dto.proveedor.ProveedorResponseDto;
import com.mesago.mesago.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService service;

    @PostMapping
    public ProveedorResponseDto crear(@Valid @RequestBody ProveedorRequestDto dto) {
        return service.crear(dto);
    }

    @GetMapping
    public Page<ProveedorResponseDto> listar(Pageable pageable) {
        return service.listar(pageable);
    }


    @GetMapping("/{id}")
    public ProveedorResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ProveedorResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody ProveedorRequestDto dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
