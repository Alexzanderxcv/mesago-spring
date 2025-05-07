package com.mesago.mesago.service;

import com.mesago.mesago.dto.cliente.ClienteRequestDto;
import com.mesago.mesago.dto.cliente.ClienteResponseDto;

import java.util.List;

public interface ClienteService {

    ClienteResponseDto crear(ClienteRequestDto request);

    ClienteResponseDto obtenerPorId(Long id);

    List<ClienteResponseDto> listarTodos();

    ClienteResponseDto actualizar(Long id, ClienteRequestDto request);

    ClienteResponseDto obtenerPorNombre(String nombre);

    void eliminar(Long id);
}
