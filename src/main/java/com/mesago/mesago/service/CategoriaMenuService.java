package com.mesago.mesago.service;


import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuRequestDto;
import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuResponseDto;

import java.util.List;

public interface CategoriaMenuService {

    CategoriaMenuResponseDto crear(CategoriaMenuRequestDto request);

    CategoriaMenuResponseDto obtenerPorId(Long id);

    List<CategoriaMenuResponseDto> listarTodas();

    CategoriaMenuResponseDto actualizar(Long id, CategoriaMenuRequestDto request);

    void eliminar(Long id);

}
