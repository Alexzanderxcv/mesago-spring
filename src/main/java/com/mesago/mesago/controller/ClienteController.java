package com.mesago.mesago.controller;

import com.mesago.mesago.dto.cliente.ClienteRequestDto;
import com.mesago.mesago.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleado/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "cliente/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear() {
        return "cliente/crear";
    }

    @PostMapping
    public String crear(
            @RequestParam String nombre,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String observaciones
    ) {
        ClienteRequestDto dto = new ClienteRequestDto();
        dto.setNombre(nombre);
        dto.setTelefono(telefono);
        dto.setEmail(email);
        dto.setObservaciones(observaciones);

        clienteService.crear(dto);
        return "redirect:/empleado/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.obtenerPorId(id));
        return "cliente/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String observaciones
    ) {
        ClienteRequestDto dto = new ClienteRequestDto();
        dto.setNombre(nombre);
        dto.setTelefono(telefono);
        dto.setEmail(email);
        dto.setObservaciones(observaciones);

        clienteService.actualizar(id, dto);
        return "redirect:/empleado/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/empleado/clientes";
    }
}

