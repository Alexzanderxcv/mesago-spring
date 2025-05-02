package com.mesago.mesago.service;

import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoRequestDto;
import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoResponseDto;

import java.util.List;

public interface ProveedorInsumoService {
    ProveedorInsumoResponseDto crear(ProveedorInsumoRequestDto dto);
    List<ProveedorInsumoResponseDto> listar();
    ProveedorInsumoResponseDto obtenerPorId(Long id);
    ProveedorInsumoResponseDto actualizar(Long id, ProveedorInsumoRequestDto dto);
    void eliminar(Long id);
}

