package com.mesago.mesago.controller;


import com.mesago.mesago.dto.detallePedido.DetallePedidoRequestDto;
import com.mesago.mesago.dto.detallePedido.DetallePedidoResponseDto;
import com.mesago.mesago.dto.pedido.PedidoRequestDto;
import com.mesago.mesago.dto.pedido.PedidoResponseDto;
import com.mesago.mesago.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empleado/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final DetallePedidoService detallePedidoService;
    private final TrabajadorService trabajadorService;
    private final MesaService mesaService;
    private final ClienteService clienteService;
    private final MenuService menuService;

    @GetMapping
    public String listar(Model model) {
        List<PedidoResponseDto> pedidos = pedidoService.listarTodos();

        Map<Long, List<DetallePedidoResponseDto>> detallePedidosMap = pedidos.stream()
                .collect(Collectors.toMap(
                        PedidoResponseDto::getId,
                        p -> detallePedidoService.listarPorPedido(p.getId())
                ));

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("detallePedidosMap", detallePedidosMap);

        return "pedidos/lista";
    }


    @GetMapping("/detalles/{idPedido}")
    public String mostrarDetalles(@PathVariable Long idPedido, Model model) {
        model.addAttribute("pedido", pedidoService.obtenerPorId(idPedido));
        model.addAttribute("detalles", detallePedidoService.listarPorPedido(idPedido)); // método nuevo
        return "pedidos/detalles"; // podría ser una vista o un fragmento modal
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("trabajadores", trabajadorService.listar());
        model.addAttribute("mesas", mesaService.listarTodas());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("menus", menuService.listarTodos());
        return "pedidos/crear";
    }

    @PostMapping
    public String crear(
            @RequestParam(defaultValue = "PENDIENTE") String estado,
            @RequestParam BigDecimal total,
            @RequestParam Integer tiempoPreparado,
            @RequestParam Long idTrabajador,
            @RequestParam Long idMesa,
            @RequestParam Long idCliente,
            @RequestParam(name = "idMenu") List<Long> idMenus,
            @RequestParam(name = "cantidad") List<Integer> cantidades,
            @RequestParam(name = "precioUnitario") List<BigDecimal> preciosUnitarios,
            @RequestParam(name = "subTotal") List<BigDecimal> subtotales
    ) {
        PedidoRequestDto dto = new PedidoRequestDto();
        dto.setFecha(LocalDateTime.now());
        dto.setEstado(estado);
        dto.setTotal(total);
        dto.setTiempoPreparado(tiempoPreparado);
        dto.setIdTrabajador(idTrabajador);
        dto.setIdMesa(idMesa);
        dto.setIdCliente(idCliente);

        List<DetallePedidoRequestDto> detalles = new java.util.ArrayList<>();
        for (int i = 0; i < idMenus.size(); i++) {
            DetallePedidoRequestDto detalle = new DetallePedidoRequestDto();
            detalle.setIdMenu(idMenus.get(i));
            detalle.setCantidad(cantidades.get(i));
            detalle.setPrecioUnitario(preciosUnitarios.get(i));
            detalle.setSubTotal(subtotales.get(i));
            detalles.add(detalle);
        }

        dto.setDetalles(detalles);

        pedidoService.crear(dto);
        return "redirect:/empleado/pedidos";
    }


    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("pedido", pedidoService.obtenerPorId(id));
        model.addAttribute("trabajadores", trabajadorService.listar());
        model.addAttribute("mesas", mesaService.listarTodas());
        model.addAttribute("clientes", clienteService.listarTodos());
        return "pedidos/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @RequestParam String estado,
            @RequestParam BigDecimal total,
            @RequestParam Integer tiempoPreparado,
            @RequestParam Long idTrabajador,
            @RequestParam Long idMesa,
            @RequestParam Long idCliente
    ) {
        PedidoRequestDto dto = new PedidoRequestDto();
        dto.setFecha(LocalDateTime.now());
        dto.setEstado(estado);
        dto.setTotal(total);
        dto.setTiempoPreparado(tiempoPreparado);
        dto.setIdTrabajador(idTrabajador);
        dto.setIdMesa(idMesa);
        dto.setIdCliente(idCliente);

        pedidoService.actualizar(id, dto);
        return "redirect:/empleado/pedidos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return "redirect:/empleado/pedidos";
    }
}

