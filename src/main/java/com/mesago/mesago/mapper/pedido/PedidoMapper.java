package com.mesago.mesago.mapper.pedido;

import com.mesago.mesago.dto.pedido.PedidoRequestDto;
import com.mesago.mesago.dto.pedido.PedidoResponseDto;
import com.mesago.mesago.entity.Pedido;
import com.mesago.mesago.repository.ClienteRepository;
import com.mesago.mesago.repository.MesaRepository;
import com.mesago.mesago.repository.TrabajadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoMapper {

    private final TrabajadorRepository trabajadorRepository;
    private final MesaRepository mesaRepository;
    private final ClienteRepository clienteRepository;

    public PedidoResponseDto toResponseDto(Pedido entity) {
        if (entity == null) {
            return null;
        }
        return PedidoResponseDto.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .estado(entity.getEstado())
                .total(entity.getTotal())
                .tiempoPreparado(entity.getTiempoPreparado())
                .idTrabajador(entity.getTrabajador().getId())
                .idMesa(entity.getMesa().getId())
                .idCliente(entity.getCliente().getId())
                .build();
    }

    public Pedido toEntity(PedidoRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Pedido.builder()
                .fecha(dto.getFecha())
                .estado(dto.getEstado())
                .total(dto.getTotal())
                .tiempoPreparado(dto.getTiempoPreparado())
                .trabajador(trabajadorRepository.findById(dto.getIdTrabajador())
                        .orElseThrow(() -> new RuntimeException("Trabajador no encontrado")))
                .mesa(mesaRepository.findById(dto.getIdMesa())
                        .orElseThrow(() -> new RuntimeException("Mesa no encontrada")))
                .cliente(clienteRepository.findById(dto.getIdCliente())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado")))
                .build();
    }

    public void updateEntityFromDto(PedidoRequestDto dto, Pedido entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getFecha() != null) {
            entity.setFecha(dto.getFecha());
        }
        if (dto.getEstado() != null) {
            entity.setEstado(dto.getEstado());
        }
        if (dto.getTotal() != null) {
            entity.setTotal(dto.getTotal());
        }
        if (dto.getTiempoPreparado() != null) {
            entity.setTiempoPreparado(dto.getTiempoPreparado());
        }
        if (dto.getIdTrabajador() != null) {
            entity.setTrabajador(trabajadorRepository.findById(dto.getIdTrabajador())
                    .orElseThrow(() -> new RuntimeException("Trabajador no encontrado")));
        }
        if (dto.getIdMesa() != null) {
            entity.setMesa(mesaRepository.findById(dto.getIdMesa())
                    .orElseThrow(() -> new RuntimeException("Mesa no encontrada")));
        }
        if (dto.getIdCliente() != null) {
            entity.setCliente(clienteRepository.findById(dto.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));
        }
    }

}
