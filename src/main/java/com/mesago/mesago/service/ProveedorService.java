package com.mesago.mesago.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mesago.mesago.dto.proveedor.ProveedorRequestDto;
import com.mesago.mesago.dto.proveedor.ProveedorResponseDto;

import java.util.List;

public interface ProveedorService {

    ProveedorResponseDto crear(ProveedorRequestDto dto);
    ProveedorResponseDto obtenerPorId(Long id);
    ProveedorResponseDto actualizar(Long id, ProveedorRequestDto dto);
    Page<ProveedorResponseDto> listar(Pageable pageable);
    void eliminar(Long id);
    long count(); // ← este nuevo método

}
