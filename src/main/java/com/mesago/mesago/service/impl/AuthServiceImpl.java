package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.login.LoginRequestDto;
import com.mesago.mesago.dto.login.LoginResponseDto;
import com.mesago.mesago.entity.Usuario;
import com.mesago.mesago.repository.UsuarioRepository;
import com.mesago.mesago.security.CustomUserDetailService;
import com.mesago.mesago.security.util.JwtUtil;
import com.mesago.mesago.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService userDetailService;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Generar token JWT
        UserDetails userDetails = userDetailService.loadUserByUsername(usuario.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        String nombreTrabajador = usuario.getTrabajador().getNombre();
        String cargo = usuario.getTrabajador().getRol().getCargo();

        return new LoginResponseDto(
                usuario.getId(),
                usuario.getUsername(),
                nombreTrabajador,
                cargo,
                token
        );

    }
}
