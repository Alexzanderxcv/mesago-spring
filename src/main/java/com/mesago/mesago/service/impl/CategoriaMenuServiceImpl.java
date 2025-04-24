package com.mesago.mesago.service.impl;

import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuRequestDTO;
import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuResponseDTO;
import com.mesago.mesago.service.CategoriaMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaMenuServiceImpl implements CategoriaMenuService {



    @Override
    public List<CategoriaMenuResponseDTO> listar() {
        return List.of();
    }

    @Override
    public CategoriaMenuResponseDTO guardar(CategoriaMenuRequestDTO requestDTO) {
        return null;
    }

    @Override
    public CategoriaMenuResponseDTO obtenerPorId(Long id) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
