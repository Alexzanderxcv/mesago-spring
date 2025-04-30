package com.mesago.mesago.service;

import com.mesago.mesago.dto.factura.FacturaRequestDto;
import com.mesago.mesago.dto.factura.FacturaResponseDto;

import java.util.List;

public interface FacturaService {

    FacturaResponseDto crear(FacturaRequestDto requestDto);
    FacturaResponseDto obtenerPorId(Long id);
    List<FacturaResponseDto> listarTodos();
    FacturaResponseDto actualizar(Long id, FacturaRequestDto requestDto);

    void eliminar(Long id);
}
