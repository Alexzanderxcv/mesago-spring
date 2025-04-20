package com.mesago.mesago.service;


import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuRequestDTO;
import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuResponseDTO;

import java.util.List;

public interface CategoriaMenuService {
    List<CategoriaMenuResponseDTO> listar();
    CategoriaMenuResponseDTO guardar(CategoriaMenuRequestDTO requestDTO);
    CategoriaMenuResponseDTO obtenerPorId(Long id);
    void eliminar(Long id);

}
