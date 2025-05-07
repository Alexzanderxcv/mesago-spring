package com.mesago.mesago.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLoginForm() {
        return "login"; // Renderiza login.html
    }

    @GetMapping("/admin")
    public String mostrarVistaAdmin(Model model) {
        model.addAttribute("title", "Panel de Administración");
        return "admin"; // Renderiza admin.html
    }

    @GetMapping("/empleado")
    public String mostrarVistaEmpleado(Model model) {
        model.addAttribute("title", "Panel de Empleado");
        return "empleado"; // Renderiza empleado.html
    }
}