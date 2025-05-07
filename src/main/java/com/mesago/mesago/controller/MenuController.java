package com.mesago.mesago.controller;

import com.mesago.mesago.dto.menu.MenuRequestDto;
import com.mesago.mesago.service.CategoriaMenuService;
import com.mesago.mesago.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService service;
    private final CategoriaMenuService categoriaMenuService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("menus", service.listarTodos());
        return "menu/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("categorias", categoriaMenuService.listarTodas());
        return "menu/crear";
    }

    @PostMapping
    public String crear(
            @RequestParam String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam double precio,
            @RequestParam int stock,
            @RequestParam(required = false) String estado,
            @RequestParam Long categoriaId
    ) {
        MenuRequestDto dto = new MenuRequestDto();
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setPrecio(BigDecimal.valueOf(precio));
        dto.setStock(stock);
        dto.setEstado(estado);
        dto.setIdCategoria(categoriaId);

        service.crear(dto);
        return "redirect:/admin/menus";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("menu", service.obtenerPorId(id));
        model.addAttribute("categorias", categoriaMenuService.listarTodas());
        return "menu/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam double precio,
            @RequestParam int stock,
            @RequestParam(required = false) String estado,
            @RequestParam Long categoriaId
    ) {
        MenuRequestDto dto = new MenuRequestDto();
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setPrecio(BigDecimal.valueOf(precio));
        dto.setStock(stock);
        dto.setEstado(estado);
        dto.setIdCategoria(categoriaId);

        service.actualizar(id, dto);
        return "redirect:/admin/menus";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/admin/menus";
    }
}
