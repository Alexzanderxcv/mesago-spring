package com.mesago.mesago.controller;

import com.mesago.mesago.dto.categoriaMenu.*;
import com.mesago.mesago.service.CategoriaMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categoria")
@RequiredArgsConstructor
public class CategoriaMenuController {

    private final CategoriaMenuService categoriaMenuService;

    @GetMapping
    public String listar(Model model) {
        List<CategoriaMenuResponseDto> categorias = categoriaMenuService.listarTodas();
        model.addAttribute("categorias", categorias);
        return "categoria/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear() {
        return "categoria/crear";
    }

    @PostMapping
    public String crear(@RequestParam String nombre,
                        @RequestParam(required = false) String descripcion) {
        CategoriaMenuRequestDto dto = new CategoriaMenuRequestDto(nombre, descripcion);
        categoriaMenuService.crear(dto);
        return "redirect:/admin/categoria";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        CategoriaMenuResponseDto dto = categoriaMenuService.obtenerPorId(id);
        model.addAttribute("categoria", dto);
        return "categoria/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @RequestParam String nombre,
                             @RequestParam(required = false) String descripcion) {
        CategoriaMenuRequestDto dto = new CategoriaMenuRequestDto(nombre, descripcion);
        categoriaMenuService.actualizar(id, dto);
        return "redirect:/admin/categoria";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        categoriaMenuService.eliminar(id);
        return "redirect:/admin/categoria";
    }
}
