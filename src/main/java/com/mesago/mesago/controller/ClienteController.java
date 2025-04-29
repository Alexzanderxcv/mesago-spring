package com.mesago.mesago.controller;

import com.mesago.mesago.dto.cliente.ClienteRequestDto;
import com.mesago.mesago.dto.cliente.ClienteResponseDto;
import com.mesago.mesago.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto crear(@Validated @RequestBody ClienteRequestDto req) {
        return service.crear(req);
    }

    @GetMapping("/{id}")
    public ClienteResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<ClienteResponseDto> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public ClienteResponseDto actualizar(
            @PathVariable Long id,
            @Validated @RequestBody ClienteRequestDto req
    ) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

}
