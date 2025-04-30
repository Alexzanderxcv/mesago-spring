package com.mesago.mesago.controller;

import com.mesago.mesago.dto.reserva.ReservaRequestDto;
import com.mesago.mesago.dto.reserva.ReservaResponseDto;
import com.mesago.mesago.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService service;

    @GetMapping
    public List<ReservaResponseDto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ReservaResponseDto obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaResponseDto registrar(@Valid @RequestBody ReservaRequestDto dto) {
        return service.registrar(dto);
    }

    @PutMapping("/{id}")
    public ReservaResponseDto actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ReservaRequestDto dto
    ) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
