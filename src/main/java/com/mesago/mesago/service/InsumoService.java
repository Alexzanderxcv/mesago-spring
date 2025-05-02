package com.mesago.mesago.service;

import com.mesago.mesago.dto.insumo.InsumoRequestDto;
import com.mesago.mesago.dto.insumo.InsumoResponseDto;

import java.util.List;

public interface InsumoService {
    InsumoResponseDto crear(InsumoRequestDto dto);
    List<InsumoResponseDto> listar();
    InsumoResponseDto obtenerPorId(Long id);
    InsumoResponseDto actualizar(Long id, InsumoRequestDto dto);
    void eliminar(Long id);
}
