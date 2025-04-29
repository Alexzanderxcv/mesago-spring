package com.mesago.mesago.controller;


import com.mesago.mesago.dto.pedido.PedidoRequestDto;
import com.mesago.mesago.dto.pedido.PedidoResponseDto;
import com.mesago.mesago.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDto crear(@Validated @RequestBody PedidoRequestDto req) {
        return service.crear(req);
    }

    @GetMapping("/{id}")
    public PedidoResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<PedidoResponseDto> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public PedidoResponseDto actualizar(
            @PathVariable Long id,
            @Validated @RequestBody PedidoRequestDto req
    ) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
