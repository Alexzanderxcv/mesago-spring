package com.mesago.mesago.controller;


import com.mesago.mesago.dto.factura.FacturaRequestDto;
import com.mesago.mesago.dto.factura.FacturaResponseDto;
import com.mesago.mesago.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FacturaResponseDto crear(@Validated @RequestBody FacturaRequestDto req) {

        return service.crear(req);
    }

    @GetMapping("/{id}")
    public FacturaResponseDto obtener(@PathVariable Long id){
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<FacturaResponseDto> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public FacturaResponseDto actualizar(
            @PathVariable Long id,
            @Validated @RequestBody FacturaRequestDto req
    ) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

}
