package com.mesago.mesago.mapper.proveedorInsumo;

import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoRequestDto;
import com.mesago.mesago.dto.proveedorInsumo.ProveedorInsumoResponseDto;
import com.mesago.mesago.entity.Insumo;
import com.mesago.mesago.entity.Proveedor;
import com.mesago.mesago.entity.ProveedorInsumo;
import com.mesago.mesago.repository.InsumoRepository;
import com.mesago.mesago.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProveedorInsumoMapper {

    private final ProveedorRepository proveedorRepository;
    private final InsumoRepository insumoRepository;

    public ProveedorInsumoResponseDto toResponseDto(ProveedorInsumo entity) {
        if (entity == null) {
            return null;
        }

        return ProveedorInsumoResponseDto.builder()
                .id(entity.getId())
                .precioUnitario(entity.getPrecioUnitario())
                .fechaEntrega(entity.getFechaEntrega())
                .cantidad(entity.getCantidad())
                .idProveedor(entity.getProveedor().getId())
                .nombreProveedor(entity.getProveedor().getNombre())
                .idInsumo(entity.getInsumo().getId())
                .nombreInsumo(entity.getInsumo().getNombre())
                .build();
    }

    public ProveedorInsumo toEntity(ProveedorInsumoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        Insumo insumo = insumoRepository.findById(dto.getIdInsumo())
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        return ProveedorInsumo.builder()
                .precioUnitario(dto.getPrecioUnitario())
                .fechaEntrega(dto.getFechaEntrega())
                .cantidad(dto.getCantidad())
                .proveedor(proveedor)
                .insumo(insumo)
                .build();
    }

    public void updateEntityFromDto(ProveedorInsumoRequestDto dto, ProveedorInsumo entity) {
        if (dto == null || entity == null) {
            return;
        }

        if (dto.getPrecioUnitario() != null) entity.setPrecioUnitario(dto.getPrecioUnitario());
        if (dto.getFechaEntrega() != null) entity.setFechaEntrega(dto.getFechaEntrega());
        if (dto.getCantidad() != null) entity.setCantidad(dto.getCantidad());

        if (dto.getIdProveedor() != null) {
            Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            entity.setProveedor(proveedor);
        }

        if (dto.getIdInsumo() != null) {
            Insumo insumo = insumoRepository.findById(dto.getIdInsumo())
                    .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
            entity.setInsumo(insumo);
        }
    }
}
