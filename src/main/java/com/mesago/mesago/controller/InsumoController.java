package com.mesago.mesago.controller;

import com.mesago.mesago.dto.insumo.InsumoRequestDto;
import com.mesago.mesago.dto.insumo.InsumoResponseDto;
import com.mesago.mesago.service.InsumoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/insumos")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService insumoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("insumos", insumoService.listar());
        return "insumos/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear() {
        return "insumos/crear";
    }

    @PostMapping
    public String crear(
            @RequestParam String nombre,
            @RequestParam String unidadMedida,
            @RequestParam Integer stock,
            @RequestParam Integer stockMinimo,
            @RequestParam String estado
    ) {
        InsumoRequestDto dto = new InsumoRequestDto();
        dto.setNombre(nombre);
        dto.setUnidadMedida(unidadMedida);
        dto.setStock(stock);
        dto.setStockMinimo(stockMinimo);
        dto.setEstado(estado);
        insumoService.crear(dto);
        return "redirect:/admin/insumos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("insumo", insumoService.obtenerPorId(id));
        return "insumos/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam String unidadMedida,
            @RequestParam Integer stock,
            @RequestParam Integer stockMinimo,
            @RequestParam String estado
    ) {
        InsumoRequestDto dto = new InsumoRequestDto();
        dto.setNombre(nombre);
        dto.setUnidadMedida(unidadMedida);
        dto.setStock(stock);
        dto.setStockMinimo(stockMinimo);
        dto.setEstado(estado);
        insumoService.actualizar(id, dto);
        return "redirect:/admin/insumos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        insumoService.eliminar(id);
        return "redirect:/admin/insumos";
    }
}