package com.mesago.mesago.controller;

import com.mesago.mesago.dto.rol.RolRequestDto;
import com.mesago.mesago.dto.rol.RolResponseDto;
import com.mesago.mesago.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RolResponseDto crear(@Valid @RequestBody RolRequestDto request) {
        return service.crear(request);
    }

    @GetMapping("/{id}")
    public RolResponseDto obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<RolResponseDto> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public RolResponseDto actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RolRequestDto request
    ) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

}
