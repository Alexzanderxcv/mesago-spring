package com.mesago.mesago.mapper.detallePedido;

import com.mesago.mesago.dto.detallePedido.DetallePedidoRequestDto;
import com.mesago.mesago.dto.detallePedido.DetallePedidoResponseDto;
import com.mesago.mesago.entity.DetallePedido;
import com.mesago.mesago.repository.MenuRepository;
import com.mesago.mesago.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DetallePedidoMapper {

    private final PedidoRepository pedidoRepository;
    private final MenuRepository menuRepository;

    public DetallePedidoResponseDto toResponseDto(DetallePedido entity) {
        if (entity == null) {
            return null;
        }
        return DetallePedidoResponseDto.builder()
                .id(entity.getId())
                .idPedido(entity.getPedido().getId())
                .idMenu(entity.getMenu().getId())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .subTotal(entity.getSubTotal())
                .build();
    }

    public DetallePedido toEntity(DetallePedidoRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return DetallePedido.builder()
                .pedido(pedidoRepository.findById(dto.getIdPedido())
                        .orElseThrow(() -> new RuntimeException("Pedido no encontrado")))
                .menu(menuRepository.findById(dto.getIdMenu())
                        .orElseThrow(() -> new RuntimeException("Menu no encontrado")))
                .cantidad(dto.getCantidad())
                .precioUnitario(dto.getPrecioUnitario())
                .subTotal(dto.getSubTotal())
                .build();
    }

    public void updateEntityFromDto(DetallePedidoRequestDto dto, DetallePedido entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getIdPedido() != null) {
            entity.setPedido(pedidoRepository.findById(dto.getIdPedido())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado")));
        }
        if (dto.getIdMenu() != null) {
            entity.setMenu(menuRepository.findById(dto.getIdMenu())
                    .orElseThrow(() -> new RuntimeException("Menu no encontrado")));
        }
        if (dto.getCantidad() != null) {
            entity.setCantidad(dto.getCantidad());
        }
        if (dto.getPrecioUnitario() != null) {
            entity.setPrecioUnitario(dto.getPrecioUnitario());
        }
        if (dto.getSubTotal() != null) {
            entity.setSubTotal(dto.getSubTotal());
        }
    }
}
