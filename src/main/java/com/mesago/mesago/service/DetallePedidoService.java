package com.mesago.mesago.service;

import com.mesago.mesago.dto.detallePedido.DetallePedidoRequestDto;
import com.mesago.mesago.dto.detallePedido.DetallePedidoResponseDto;

import java.util.List;

public interface DetallePedidoService {

    DetallePedidoResponseDto crear(DetallePedidoRequestDto request);

    DetallePedidoResponseDto obtenerPorId(Long id);

    List<DetallePedidoResponseDto> listarTodos();

    DetallePedidoResponseDto actualizar(Long id, DetallePedidoRequestDto request);

    void eliminar(Long id);

    List<DetallePedidoResponseDto> listarPorPedido(Long idPedido);

}
