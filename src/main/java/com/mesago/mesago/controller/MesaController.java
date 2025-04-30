package com.mesago.mesago.controller;

import com.mesago.mesago.dto.mesa.MesaRequestDto;
import com.mesago.mesago.dto.mesa.MesaResponseDto;
import com.mesago.mesago.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MesaResponseDto crear(@Validated @RequestBody MesaRequestDto dto) {
        return service.crear(dto);
    }

    @GetMapping("/{id}")
    public MesaResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<MesaResponseDto> listar() {
        return service.listarTodas();
    }

    @PutMapping("/{id}")
    public MesaResponseDto actualizar(
            @PathVariable Long id,
            @Validated @RequestBody MesaRequestDto dto
    ) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}