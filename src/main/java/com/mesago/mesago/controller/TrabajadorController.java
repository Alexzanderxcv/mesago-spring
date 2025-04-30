package com.mesago.mesago.controller;


import com.mesago.mesago.dto.trabajador.TrabajadorRequestDto;
import com.mesago.mesago.dto.trabajador.TrabajadorResponseDto;
import com.mesago.mesago.service.TrabajadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")
@RequiredArgsConstructor
public class TrabajadorController {

    private final TrabajadorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrabajadorResponseDto crear(@Valid @RequestBody TrabajadorRequestDto dto) {
        return service.crear(dto);
    }

    @GetMapping("/{id}")
    public TrabajadorResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<TrabajadorResponseDto> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public TrabajadorResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody TrabajadorRequestDto dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
