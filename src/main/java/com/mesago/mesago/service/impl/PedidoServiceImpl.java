package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.pedido.PedidoRequestDto;
import com.mesago.mesago.dto.pedido.PedidoResponseDto;
import com.mesago.mesago.entity.*;
import com.mesago.mesago.mapper.pedido.PedidoMapper;
import com.mesago.mesago.repository.*;
import com.mesago.mesago.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final TrabajadorRepository trabajadorRepository;
    private final MesaRepository mesaRepository;
    private final ClienteRepository clienteRepository;
    private final MenuRepository menuRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    @Override
    @Transactional
    public PedidoResponseDto crear(PedidoRequestDto request) {
        Trabajador trabajador = trabajadorRepository.findById(request.getIdTrabajador())
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));
        Mesa mesa = mesaRepository.findById(request.getIdMesa())
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada"));
        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        Pedido pedido = Pedido.builder()
                .fecha(request.getFecha())
                .estado(request.getEstado())
                .total(request.getTotal())
                .tiempoPreparado(request.getTiempoPreparado())
                .trabajador(trabajador)
                .mesa(mesa)
                .cliente(cliente)
                .build();

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // ⚠️ Verificamos si hay detalles para agregar
        if (request.getDetalles() != null && !request.getDetalles().isEmpty()) {
            List<DetallePedido> detalles = request.getDetalles().stream().map(detalleDto -> {
                Menu menu = menuRepository.findById(detalleDto.getIdMenu())
                        .orElseThrow(() -> new EntityNotFoundException("Menu no encontrado: " + detalleDto.getIdMenu()));

                return DetallePedido.builder()
                        .pedido(pedidoGuardado)
                        .menu(menu)
                        .cantidad(detalleDto.getCantidad())
                        .precioUnitario(detalleDto.getPrecioUnitario())
                        .subTotal(detalleDto.getSubTotal())
                        .build();
            }).collect(Collectors.toList());

            detallePedidoRepository.saveAll(detalles);
        }

        return pedidoMapper.toResponseDto(pedidoGuardado);
    }


    @Override
    public PedidoResponseDto obtenerPorId(Long id) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado: " + id));
        return pedidoMapper.toResponseDto(entity);
    }

    @Override
    public List<PedidoResponseDto> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDto actualizar(Long id, PedidoRequestDto request) {
        Pedido entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado: " + id));
        pedidoMapper.updateEntityFromDto(request, entity);
        Pedido updated = pedidoRepository.save(entity);
        return pedidoMapper.toResponseDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido no encontrado: " + id);
        }
        pedidoRepository.deleteById(id);
    }
}
