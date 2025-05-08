package com.mesago.mesago.service;

import com.mesago.mesago.dto.usuario.UsuarioRequestDto;
import com.mesago.mesago.dto.usuario.UsuarioResponseDto;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDto crear(UsuarioRequestDto dto);

    UsuarioResponseDto obtenerPorId(Long id);

    List<UsuarioResponseDto> listarTodos();

    UsuarioResponseDto actualizar(Long id, UsuarioRequestDto dto);
    void resetearPassword(Long id);

    void eliminar(Long id);
}