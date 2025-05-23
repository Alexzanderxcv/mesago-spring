package com.mesago.mesago.service;

import com.mesago.mesago.dto.menu.MenuRequestDto;
import com.mesago.mesago.dto.menu.MenuResponseDto;

import java.util.List;

public interface MenuService {

    MenuResponseDto crear(MenuRequestDto dto);
    MenuResponseDto obtenerPorId(Long id);
    List<MenuResponseDto> listarTodos();
    MenuResponseDto actualizar(Long id, MenuRequestDto dto);
    List<MenuResponseDto> obtenerPorCategoria(String nombre);
    void eliminar(Long id);
    long count(); // ← este nuevo método

}
