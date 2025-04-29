package com.mesago.mesago.controller;

import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuRequestDto;
import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuResponseDto;
import com.mesago.mesago.repository.CategoriaMenuRepository;
import com.mesago.mesago.service.CategoriaMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categoriaMenu")
public class CategoriaMenuController {

    private final CategoriaMenuService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaMenuResponseDto crear(@Validated @RequestBody CategoriaMenuRequestDto req) {
        return service.crear(req);
    }

    @GetMapping("/{id}")
    public CategoriaMenuResponseDto obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<CategoriaMenuResponseDto> listar() {
        return service.listarTodas();
    }

    @PutMapping("/{id}")
    public CategoriaMenuResponseDto actualizar(
            @PathVariable Long id,
            @Validated @RequestBody CategoriaMenuRequestDto req
    ) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

}
