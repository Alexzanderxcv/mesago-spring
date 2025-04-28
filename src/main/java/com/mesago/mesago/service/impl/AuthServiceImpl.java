package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.login.LoginRequestDto;
import com.mesago.mesago.dto.login.LoginResponseDto;
import com.mesago.mesago.entity.Usuario;
import com.mesago.mesago.repository.UsuarioRepository;
import com.mesago.mesago.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        Usuario usuario = usuarioRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (usuario == null) {
            throw new RuntimeException("Credenciales invalidas");
        }
        String nombreTrabajador = usuario.getTrabajador().getNombre();
        String cargo = usuario.getTrabajador().getRol().getCargo();

        return new LoginResponseDto(
                usuario.getId(),
                usuario.getUsername(),
                nombreTrabajador,
                cargo
        );

    }
}
