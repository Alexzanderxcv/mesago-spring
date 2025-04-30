package com.mesago.mesago.mapper.menu;


import com.mesago.mesago.dto.menu.MenuRequestDto;
import com.mesago.mesago.dto.menu.MenuResponseDto;
import com.mesago.mesago.entity.CategoriaMenu;
import com.mesago.mesago.entity.Menu;
import com.mesago.mesago.repository.CategoriaMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuMapper {

    private final CategoriaMenuRepository categoriaMenuRepository;

    public Menu toEntity(MenuRequestDto dto) {
        CategoriaMenu categoria = categoriaMenuRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + dto.getIdCategoria()));

        return Menu.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .estado(dto.getEstado())
                .categoria(categoria)
                .build();
    }

    public MenuResponseDto toResponseDto(Menu entity) {
        return MenuResponseDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .precio(entity.getPrecio())
                .stock(entity.getStock())
                .estado(entity.getEstado())
                .nombreCategoria(entity.getCategoria().getNombre())
                .build();
    }

    public void updateEntity(MenuRequestDto dto, Menu entity) {
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setEstado(dto.getEstado());

        CategoriaMenu categoria = categoriaMenuRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + dto.getIdCategoria()));
        entity.setCategoria(categoria);
    }
}