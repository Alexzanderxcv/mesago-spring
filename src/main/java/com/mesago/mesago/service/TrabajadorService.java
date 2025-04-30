package com.mesago.mesago.service;

import com.mesago.mesago.dto.trabajador.TrabajadorRequestDto;
import com.mesago.mesago.dto.trabajador.TrabajadorResponseDto;

import java.util.List;

public interface TrabajadorService {

    TrabajadorResponseDto crear(TrabajadorRequestDto request);
    TrabajadorResponseDto obtenerPorId(Long id);
    List<TrabajadorResponseDto> listar();
    TrabajadorResponseDto actualizar(Long id, TrabajadorRequestDto request);
    void eliminar(Long id);
}
