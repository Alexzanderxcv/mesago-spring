package com.mesago.mesago.mapper.categoriaMenu;

import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuRequestDto;
import com.mesago.mesago.dto.categoriaMenu.CategoriaMenuResponseDto;
import com.mesago.mesago.entity.CategoriaMenu;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMenuMapper {

    public CategoriaMenuResponseDto toResponseDto(CategoriaMenu entity){
        if (entity == null){
            return null;
        }
        return CategoriaMenuResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .build();

    }

    public CategoriaMenu toEntity(CategoriaMenuRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return CategoriaMenu.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }

    public void updateEntityFromDto(CategoriaMenuRequestDto dto, CategoriaMenu entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }
        if (dto.getDescripcion() != null) {
            entity.setDescripcion(dto.getDescripcion());
        }
    }
}

