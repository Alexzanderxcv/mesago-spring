package com.mesago.mesago.controller;


import com.mesago.mesago.dto.detallePedido.DetallePedidoRequestDto;
import com.mesago.mesago.dto.detallePedido.DetallePedidoResponseDto;
import com.mesago.mesago.service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
@RequiredArgsConstructor
public class DetallePedidoController {

    private final DetallePedidoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetallePedidoResponseDto crear(@Validated @RequestBody DetallePedidoRequestDto req) {
        return service.crear(req);
    }

    @GetMapping("/{id}")
    public DetallePedidoResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<DetallePedidoResponseDto> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public DetallePedidoResponseDto actualizar(
            @PathVariable Long id,
            @Validated @RequestBody DetallePedidoRequestDto req
    ) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
