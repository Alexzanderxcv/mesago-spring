package com.mesago.mesago.controller;

import com.mesago.mesago.dto.reserva.ReservaRequestDto;
import com.mesago.mesago.dto.reserva.ReservaResponseDto;
import com.mesago.mesago.service.ClienteService;
import com.mesago.mesago.service.MesaService;
import com.mesago.mesago.service.ReservaService;
import com.mesago.mesago.service.TrabajadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleado/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final ClienteService clienteService;
    private final TrabajadorService trabajadorService;
    private final MesaService mesaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas", reservaService.listar());
        return "reserva/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("trabajadores", trabajadorService.listar());
        model.addAttribute("mesas", mesaService.listarTodas());
        return "reserva/crear";
    }

    @PostMapping
    public String crear(@RequestParam("fechaReserva") String fechaReserva,
                        @RequestParam("hora") String hora,
                        @RequestParam("numeroPersonas") Integer numeroPersonas,
                        @RequestParam("estado") String estado,
                        @RequestParam("idCliente") Long idCliente,
                        @RequestParam("idTrabajador") Long idTrabajador,
                        @RequestParam("idMesa") Long idMesa) {

        ReservaRequestDto dto = new ReservaRequestDto();
        dto.setFechaReserva(java.time.LocalDate.parse(fechaReserva));
        dto.setHora(java.time.LocalTime.parse(hora));
        dto.setNumeroPersonas(numeroPersonas);
        dto.setEstado(estado);
        dto.setIdCliente(idCliente);
        dto.setIdTrabajador(idTrabajador);
        dto.setIdMesa(idMesa);

        reservaService.registrar(dto);
        return "redirect:/empleado/reservas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("reserva", reservaService.obtenerPorId(id));
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("trabajadores", trabajadorService.listar());
        model.addAttribute("mesas", mesaService.listarTodas());
        return "reserva/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                             @RequestParam("fechaReserva") String fechaReserva,
                             @RequestParam("hora") String hora,
                             @RequestParam("numeroPersonas") Integer numeroPersonas,
                             @RequestParam("estado") String estado,
                             @RequestParam("idCliente") Long idCliente,
                             @RequestParam("idTrabajador") Long idTrabajador,
                             @RequestParam("idMesa") Long idMesa) {

        ReservaRequestDto dto = new ReservaRequestDto();
        dto.setFechaReserva(java.time.LocalDate.parse(fechaReserva));
        dto.setHora(java.time.LocalTime.parse(hora));
        dto.setNumeroPersonas(numeroPersonas);
        dto.setEstado(estado);
        dto.setIdCliente(idCliente);
        dto.setIdTrabajador(idTrabajador);
        dto.setIdMesa(idMesa);

        reservaService.actualizar(id, dto);
        return "redirect:/empleado/reservas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return "redirect:/empleado/reservas";
    }
}

