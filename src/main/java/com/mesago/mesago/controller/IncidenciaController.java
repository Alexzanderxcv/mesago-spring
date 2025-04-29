package com.mesago.mesago.controller;

import com.mesago.mesago.dto.incidencia.IncidenciaRequestDto;
import com.mesago.mesago.dto.incidencia.IncidenciaResponseDto;
import com.mesago.mesago.service.IncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IncidenciaResponseDto crear(@Validated @RequestBody IncidenciaRequestDto req) {
        return service.crear(req);
    }

    @GetMapping("/{id}")
    public IncidenciaResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<IncidenciaResponseDto> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public IncidenciaResponseDto actualizar(
            @PathVariable Long id,
            @Validated @RequestBody IncidenciaRequestDto req
    ) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
