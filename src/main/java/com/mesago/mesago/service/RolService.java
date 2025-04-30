package com.mesago.mesago.service;

import com.mesago.mesago.dto.rol.RolRequestDto;
import com.mesago.mesago.dto.rol.RolResponseDto;

import java.util.List;

public interface RolService {

    RolResponseDto crear(RolRequestDto request);
    RolResponseDto obtenerPorId(Long id);
    List<RolResponseDto> listarTodos();
    RolResponseDto actualizar(Long id, RolRequestDto request);
    void eliminar(Long id);
}
