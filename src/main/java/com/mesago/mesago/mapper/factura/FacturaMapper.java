package com.mesago.mesago.mapper.factura;


import com.mesago.mesago.dto.factura.FacturaRequestDto;
import com.mesago.mesago.dto.factura.FacturaResponseDto;
import com.mesago.mesago.entity.Factura;
import com.mesago.mesago.repository.PedidoRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Builder
public class FacturaMapper {

    private final PedidoRepository pedidoRepository;


    public FacturaResponseDto toResponseDto(Factura entity){
        if (entity == null){
            return null;
        }
        return FacturaResponseDto.builder()
                .id(entity.getId())
                .fechaEmision(entity.getFechaEmision())
                .montoTotal(entity.getMontoTotal())
                .metodoPago(entity.getMetodoPago())
                .estado(entity.getEstado())
                .numeroFactura(entity.getNumeroFactura())
                .tipo(entity.getTipo())
                .idPedido(entity.getPedido().getId())
                .build();
    }

    public Factura toEntity(FacturaRequestDto dto){
        if (dto==null){
            return null;
        }
        return Factura.builder()
                .montoTotal(dto.getMontoTotal())
                .metodoPago(dto.getMetodoPago())
                .estado(dto.getEstado())
                .numeroFactura(dto.getNumeroFactura())
                .tipo(dto.getTipo())
                .pedido(pedidoRepository.findById(dto.getIdPedido())
                        .orElseThrow(() -> new RuntimeException("Pedido no encontrado")))
                .build();
    }

    public void updateEntityFromDto(FacturaRequestDto dto, Factura entity){
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getMontoTotal() != null) {
            entity.setMontoTotal(dto.getMontoTotal());
        }
        if (dto.getMetodoPago() != null) {
            entity.setMetodoPago(dto.getMetodoPago());
        }
        if (dto.getEstado() != null) {
            entity.setEstado(dto.getEstado());
        }
        if (dto.getNumeroFactura() != null) {
            entity.setNumeroFactura(dto.getNumeroFactura());
        }
        if (dto.getTipo() != null) {
            entity.setTipo(dto.getTipo());
        }
        if (dto.getIdPedido() != null) {
            entity.setPedido(pedidoRepository.findById(dto.getIdPedido())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado")));
        }
    }
}
