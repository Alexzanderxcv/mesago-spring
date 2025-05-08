package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.usuario.UsuarioRequestDto;
import com.mesago.mesago.dto.usuario.UsuarioResponseDto;
import com.mesago.mesago.entity.Usuario;
import com.mesago.mesago.mapper.usuario.UsuarioMapper;
import com.mesago.mesago.repository.UsuarioRepository;
import com.mesago.mesago.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDto crear(UsuarioRequestDto dto) {
        if (repository.existsByTrabajadorId(dto.getIdTrabajador())) {
            throw new IllegalArgumentException("Este trabajador ya tiene un usuario asignado.");
        }

        if (repository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username ya existe: " + dto.getUsername());
        }
        // 1) Cifrar la contraseña en el DTO
        String raw = dto.getPassword();
        String hashed = passwordEncoder.encode(raw);
        dto.setPassword(hashed);

        // 2) Mapear y guardar
        Usuario entidad = mapper.toEntity(dto);
        Usuario saved = repository.save(entidad);
        return mapper.toResponseDto(saved);

    }

    @Override
    public UsuarioResponseDto obtenerPorId(Long id) {
        Usuario entidad = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + id));
        return mapper.toResponseDto(entidad);
    }

    @Override
    public List<UsuarioResponseDto> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDto actualizar(Long id, UsuarioRequestDto dto) {
        Usuario entidad = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + id));
        mapper.updateEntityFromDto(dto, entidad);
        Usuario updated = repository.save(entidad);
        return mapper.toResponseDto(updated);
    }
    @Override
    public void resetearPassword(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + id));

        String nuevaClave = "mesa2025"; // puedes hacer esto dinámico o configurable si quieres
        String claveEncriptada = passwordEncoder.encode(nuevaClave);

        usuario.setPassword(claveEncriptada);
        repository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado: " + id);
        }
        repository.deleteById(id);
    }
}