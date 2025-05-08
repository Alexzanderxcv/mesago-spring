package com.mesago.mesago.controller;

import com.mesago.mesago.dto.trabajador.TrabajadorRequestDto;
import com.mesago.mesago.service.RolService;
import com.mesago.mesago.service.TrabajadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/trabajadores")
@RequiredArgsConstructor
public class TrabajadorController {

    private final TrabajadorService service;
    private final RolService rolService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trabajadores", service.listar());
        return "trabajadores/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("roles", rolService.listarTodos());
        return "trabajadores/crear";
    }

    @PostMapping
    public String crear(
            @RequestParam String nombre,
            @RequestParam String dni,
            @RequestParam String telefono,
            @RequestParam String email,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIngreso,
            @RequestParam String turno,
            @RequestParam(required = false) String estado,
            @RequestParam Long idRol
    ) {
        TrabajadorRequestDto dto = new TrabajadorRequestDto();
        dto.setNombre(nombre);
        dto.setDni(dni);
        dto.setTelefono(telefono);
        dto.setEmail(email);
        dto.setFechaIngreso(fechaIngreso);
        dto.setTurno(turno);
        dto.setEstado(estado);
        dto.setIdRol(idRol);

        service.crear(dto);
        return "redirect:/admin/trabajadores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("trabajador", service.obtenerPorId(id));
        model.addAttribute("roles", rolService.listarTodos());
        return "trabajadores/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam String dni,
            @RequestParam String telefono,
            @RequestParam String email,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIngreso,
            @RequestParam String turno,
            @RequestParam(required = false) String estado,
            @RequestParam Long idRol
    ) {
        TrabajadorRequestDto dto = new TrabajadorRequestDto();
        dto.setNombre(nombre);
        dto.setDni(dni);
        dto.setTelefono(telefono);
        dto.setEmail(email);
        dto.setFechaIngreso(fechaIngreso);
        dto.setTurno(turno);
        dto.setEstado(estado);
        dto.setIdRol(idRol);

        service.actualizar(id, dto);
        return "redirect:/admin/trabajadores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/admin/trabajadores";
    }
}
