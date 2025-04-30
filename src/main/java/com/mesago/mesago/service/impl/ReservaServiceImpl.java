package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.reserva.ReservaRequestDto;
import com.mesago.mesago.dto.reserva.ReservaResponseDto;
import com.mesago.mesago.entity.Cliente;
import com.mesago.mesago.entity.Mesa;
import com.mesago.mesago.entity.Reserva;
import com.mesago.mesago.entity.Trabajador;
import com.mesago.mesago.mapper.reserva.ReservaMapper;
import com.mesago.mesago.repository.ClienteRepository;
import com.mesago.mesago.repository.MesaRepository;
import com.mesago.mesago.repository.ReservaRepository;
import com.mesago.mesago.repository.TrabajadorRepository;
import com.mesago.mesago.service.ReservaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final MesaRepository mesaRepository;
    private final ReservaMapper reservaMapper;

    @Override
    public List<ReservaResponseDto> listar() {
        return reservaRepository.findAll().stream()
                .map(reservaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaResponseDto obtenerPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + id));
        return reservaMapper.toResponseDto(reserva);
    }

    @Override
    @Transactional
    public ReservaResponseDto registrar(ReservaRequestDto dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + dto.getIdCliente()));
        Trabajador trabajador = trabajadorRepository.findById(dto.getIdTrabajador())
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado: " + dto.getIdTrabajador()));
        Mesa mesa = mesaRepository.findById(dto.getIdMesa())
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada: " + dto.getIdMesa()));

        Reserva reserva = reservaMapper.toEntity(dto, cliente, trabajador, mesa);
        Reserva saved = reservaRepository.save(reserva);
        return reservaMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public ReservaResponseDto actualizar(Long id, ReservaRequestDto dto) {
        Reserva existing = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + id));
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + dto.getIdCliente()));
        Trabajador trabajador = trabajadorRepository.findById(dto.getIdTrabajador())
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado: " + dto.getIdTrabajador()));
        Mesa mesa = mesaRepository.findById(dto.getIdMesa())
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada: " + dto.getIdMesa()));

        reservaMapper.updateEntityFromDto(dto, existing, cliente, trabajador, mesa);
        Reserva updated = reservaRepository.save(existing);
        return reservaMapper.toResponseDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + id));
        reservaRepository.delete(reserva);
    }
}