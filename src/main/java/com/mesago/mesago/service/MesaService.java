package com.mesago.mesago.service;

import com.mesago.mesago.dto.mesa.MesaRequestDto;
import com.mesago.mesago.dto.mesa.MesaResponseDto;

import java.util.List;

public interface MesaService {

    MesaResponseDto crear(MesaRequestDto request);

    MesaResponseDto obtenerPorId(Long id);

    List<MesaResponseDto> listarTodas();

    MesaResponseDto actualizar(Long id, MesaRequestDto request);

    void eliminar(Long id);
}
