package com.mesago.mesago.service;

import com.mesago.mesago.dto.incidencia.IncidenciaRequestDto;
import com.mesago.mesago.dto.incidencia.IncidenciaResponseDto;

import java.util.List;

public interface IncidenciaService {

    IncidenciaResponseDto crear(IncidenciaRequestDto requestDto);

    IncidenciaResponseDto obtenerPorId(Long id);

    List<IncidenciaResponseDto> listarTodos();

    IncidenciaResponseDto actualizar(Long id, IncidenciaRequestDto requestDto);

    void eliminar(Long id);
}
