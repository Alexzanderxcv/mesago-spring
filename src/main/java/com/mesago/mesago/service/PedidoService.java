package com.mesago.mesago.service;

import com.mesago.mesago.dto.pedido.PedidoRequestDto;
import com.mesago.mesago.dto.pedido.PedidoResponseDto;

import java.util.List;

public interface PedidoService {

    PedidoResponseDto crear(PedidoRequestDto request);

    PedidoResponseDto obtenerPorId(Long id);

    List<PedidoResponseDto> listarTodos();

    PedidoResponseDto actualizar(Long id, PedidoRequestDto request);

    void eliminar(Long id);
}
