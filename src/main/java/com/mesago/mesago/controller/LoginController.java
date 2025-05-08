package com.mesago.mesago.controller;

import com.mesago.mesago.service.InsumoService;
import com.mesago.mesago.service.MenuService;
import com.mesago.mesago.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class LoginController {

    private final InsumoService insumoService;
    private final ProveedorService proveedorService;
    private final MenuService menuService;

    @GetMapping("/login")
    public String mostrarLoginForm() {
        return "login"; // Renderiza login.html
    }

    @GetMapping("/admin")
    public String mostrarVistaAdmin(Model model) {
        model.addAttribute("title", "Panel de Administración");

        model.addAttribute("totalInsumos", insumoService.count());
        model.addAttribute("totalProveedores", proveedorService.count());
        model.addAttribute("totalPlatos", menuService.count());

        return "admin";
    }

    @GetMapping("/empleado")
    public String mostrarVistaEmpleado(Model model) {
        model.addAttribute("title", "Panel de Empleado");
        return "empleado"; // Renderiza empleado.html
    }
}