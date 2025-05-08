package com.mesago.mesago.controller;

import com.mesago.mesago.dto.usuario.UsuarioRequestDto;
import com.mesago.mesago.service.TrabajadorService;
import com.mesago.mesago.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TrabajadorService trabajadorService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("trabajadores", trabajadorService.listarSinUsuario());
        return "usuarios/crear";
    }


    @PostMapping
    public String crear(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String estado,
            @RequestParam Long idTrabajador,
            RedirectAttributes redirectAttributes
    ) {
        try {
            UsuarioRequestDto dto = new UsuarioRequestDto();
            dto.setUsername(username);
            dto.setPassword(password);
            dto.setEstado(estado);
            dto.setIdTrabajador(idTrabajador);

            usuarioService.crear(dto);
            redirectAttributes.addFlashAttribute("mensaje", "✅ Usuario creado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "alert-success");

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensaje", "❌ Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "alert-danger");
            return "redirect:/admin/usuarios/crear";
        }

        return "redirect:/admin/usuarios";
    }


    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.obtenerPorId(id));
        model.addAttribute("trabajadores", trabajadorService.listar());
        return "usuarios/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @RequestParam String username,
            @RequestParam String estado,
            @RequestParam Long idTrabajador
    ) {
        UsuarioRequestDto dto = new UsuarioRequestDto();
        dto.setUsername(username);
        dto.setEstado(estado);
        dto.setIdTrabajador(idTrabajador);

        usuarioService.actualizar(id, dto);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/reset-password/{id}")
    public String resetearPassword(@PathVariable Long id) {
        usuarioService.resetearPassword(id); // deberás implementar esto en el service
        return "redirect:/admin/usuarios";
    }
}
