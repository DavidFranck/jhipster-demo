package com.mex.gt.mgr.service.mapper;

import com.mex.gt.mgr.domain.Menu;
import com.mex.gt.mgr.service.dto.MenuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Menu and its DTO MenuDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MenuMapper extends EntityMapper <MenuDTO, Menu> {

    @Mapping(source = "menu.id", target = "menuId")
    MenuDTO toDto(Menu menu);

    @Mapping(source = "menuId", target = "menu")
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Menu toEntity(MenuDTO menuDTO);
    default Menu fromId(Long id) {
        if (id == null) {
            return null;
        }
        Menu menu = new Menu();
        menu.setId(id);
        return menu;
    }
}
