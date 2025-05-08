package com.mesago.mesago.controller;

import com.mesago.mesago.dto.proveedor.ProveedorRequestDto;
import com.mesago.mesago.dto.proveedor.ProveedorResponseDto;
import com.mesago.mesago.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,
                         Model model) {

        Page<ProveedorResponseDto> proveedoresPage = proveedorService.listar(PageRequest.of(page, size));

        model.addAttribute("proveedoresPage", proveedoresPage);
        model.addAttribute("paginaActual", page);
        model.addAttribute("totalPaginas", proveedoresPage.getTotalPages());
        model.addAttribute("size", size); // Para mantener selección activa en el dropdown

        return "proveedores/lista";
    }


    @GetMapping("/crear")
    public String mostrarFormularioCrear() {
        return "proveedores/crear";
    }

    @PostMapping
    public String crear(
            @RequestParam String ruc,
            @RequestParam String nombre,
            @RequestParam(required = false) String direccion,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) String estado) {

        ProveedorRequestDto dto = new ProveedorRequestDto();
        dto.setRuc(ruc);
        dto.setNombre(nombre);
        dto.setDireccion(direccion);
        dto.setTelefono(telefono);
        dto.setEstado(estado);

        proveedorService.crear(dto);
        return "redirect:/admin/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("proveedor", proveedorService.obtenerPorId(id));
        return "proveedores/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @RequestParam String ruc,
            @RequestParam String nombre,
            @RequestParam(required = false) String direccion,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) String estado) {

        ProveedorRequestDto dto = new ProveedorRequestDto();
        dto.setRuc(ruc);
        dto.setNombre(nombre);
        dto.setDireccion(direccion);
        dto.setTelefono(telefono);
        dto.setEstado(estado);

        proveedorService.actualizar(id, dto);
        return "redirect:/admin/proveedores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return "redirect:/admin/proveedores";
    }
}
