package com.mesago.mesago.mapper.reserva;

import com.mesago.mesago.dto.reserva.ReservaRequestDto;
import com.mesago.mesago.dto.reserva.ReservaResponseDto;
import com.mesago.mesago.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    public Reserva toEntity(ReservaRequestDto dto, Cliente cliente, Trabajador trabajador, Mesa mesa) {
        return Reserva.builder()
                .fechaReserva(dto.getFechaReserva())
                .hora(dto.getHora())
                .numeroPersonas(dto.getNumeroPersonas())
                .estado(dto.getEstado())
                .cliente(cliente)
                .trabajador(trabajador)
                .mesa(mesa)
                .build();
    }

    public ReservaResponseDto toResponseDto(Reserva entity) {
        return ReservaResponseDto.builder()
                .id(entity.getId())
                .fechaReserva(entity.getFechaReserva())
                .hora(entity.getHora())
                .numeroPersonas(entity.getNumeroPersonas())
                .estado(entity.getEstado())
                .idCliente(entity.getCliente().getId())
                .idTrabajador(entity.getTrabajador().getId())
                .idMesa(entity.getMesa().getId())
                .build();
    }

    public void updateEntityFromDto(ReservaRequestDto dto, Reserva entity, Cliente cliente, Trabajador trabajador, Mesa mesa) {
        entity.setFechaReserva(dto.getFechaReserva());
        entity.setHora(dto.getHora());
        entity.setNumeroPersonas(dto.getNumeroPersonas());
        entity.setEstado(dto.getEstado());
        entity.setCliente(cliente);
        entity.setTrabajador(trabajador);
        entity.setMesa(mesa);
    }
}