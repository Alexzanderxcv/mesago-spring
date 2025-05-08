package com.mesago.mesago.controller;

import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoRequestDto;
import com.mesago.mesago.service.InsumoService;
import com.mesago.mesago.service.ProveedorInsumoService;
import com.mesago.mesago.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin/proveedor-insumos")
@RequiredArgsConstructor
public class ProveedorInsumoController {

    private final ProveedorInsumoService service;
    private final ProveedorService proveedorService;
    private final InsumoService insumoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedorInsumos", service.listar());
        return "proveedor_insumo/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("proveedores", proveedorService.listar(Pageable.unpaged()).getContent());
        model.addAttribute("insumos", insumoService.listar());
        return "proveedor_insumo/crear";
    }

    @PostMapping
    public String crear(
            @RequestParam BigDecimal precioUnitario,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEntrega,
            @RequestParam Integer cantidad,
            @RequestParam Long idProveedor,
            @RequestParam Long idInsumo
    ) {
        ProveedorInsumoRequestDto dto = ProveedorInsumoRequestDto.builder()
                .precioUnitario(precioUnitario)
                .fechaEntrega(fechaEntrega)
                .cantidad(cantidad)
                .idProveedor(idProveedor)
                .idInsumo(idInsumo)
                .build();

        service.crear(dto);
        return "redirect:/admin/proveedor-insumos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("proveedorInsumo", service.obtenerPorId(id));
        model.addAttribute("proveedores", proveedorService.listar(Pageable.unpaged()).getContent());
        model.addAttribute("insumos", insumoService.listar());
        return "proveedor_insumo/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @RequestParam BigDecimal precioUnitario,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaEntrega,
            @RequestParam Integer cantidad,
            @RequestParam Long idProveedor,
            @RequestParam Long idInsumo
    ) {
        ProveedorInsumoRequestDto dto = ProveedorInsumoRequestDto.builder()
                .precioUnitario(precioUnitario)
                .fechaEntrega(fechaEntrega)
                .cantidad(cantidad)
                .idProveedor(idProveedor)
                .idInsumo(idInsumo)
                .build();

        service.actualizar(id, dto);
        return "redirect:/admin/proveedor-insumos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/admin/proveedor-insumos";
    }
}

