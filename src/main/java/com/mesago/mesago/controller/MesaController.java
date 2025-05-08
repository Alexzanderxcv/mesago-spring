package com.mesago.mesago.controller;

import com.mesago.mesago.dto.mesa.MesaRequestDto;
import com.mesago.mesago.service.MesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleado/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mesas", mesaService.listarTodas());
        return "mesa/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear() {
        return "mesa/crear";
    }

    @PostMapping
    public String crear(@RequestParam Integer numero,
                        @RequestParam Integer capacidad,
                        @RequestParam(required = false) String estado) {

        MesaRequestDto dto = new MesaRequestDto();
        dto.setNumero(numero);
        dto.setCapacidad(capacidad);
        dto.setEstado(estado);

        mesaService.crear(dto);
        return "redirect:/empleado/mesas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("mesa", mesaService.obtenerPorId(id));
        return "mesa/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @RequestParam Integer numero,
                             @RequestParam Integer capacidad,
                             @RequestParam(required = false) String estado) {

        MesaRequestDto dto = new MesaRequestDto();
        dto.setNumero(numero);
        dto.setCapacidad(capacidad);
        dto.setEstado(estado);

        mesaService.actualizar(id, dto);
        return "redirect:/empleado/mesas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mesaService.eliminar(id);
        return "redirect:/empleado/mesas";
    }
}
