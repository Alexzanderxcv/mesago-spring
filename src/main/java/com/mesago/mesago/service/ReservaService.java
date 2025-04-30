package com.mesago.mesago.service;

import com.mesago.mesago.dto.reserva.ReservaRequestDto;
import com.mesago.mesago.dto.reserva.ReservaResponseDto;

import java.util.List;

public interface ReservaService {

    List<ReservaResponseDto> listar();

    ReservaResponseDto obtenerPorId(Long id);

    ReservaResponseDto registrar(ReservaRequestDto dto);

    ReservaResponseDto actualizar(Long id, ReservaRequestDto dto);

    void eliminar(Long id);
}
