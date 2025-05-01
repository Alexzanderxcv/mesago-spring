package com.mesago.mesago.controller;

import com.mesago.mesago.dto.usuario.UsuarioRequestDto;
import com.mesago.mesago.dto.usuario.UsuarioResponseDto;
import com.mesago.mesago.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDto crear(@Valid @RequestBody UsuarioRequestDto dto) {
        return service.crear(dto);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<UsuarioResponseDto> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public UsuarioResponseDto actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDto dto
    ) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}