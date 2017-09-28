package com.mex.adx.mgr.service;

import com.mex.adx.mgr.domain.Menu;
import com.mex.adx.mgr.repository.MenuRepository;
import com.mex.adx.mgr.service.dto.MenuDTO;
import com.mex.adx.mgr.service.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Menu.
 */
@Service
@Transactional
public class MenuService {

    private final Logger log = LoggerFactory.getLogger(MenuService.class);

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    /**
     * Save a menu.
     *
     * @param menuDTO the entity to save
     * @return the persisted entity
     */
    public MenuDTO save(MenuDTO menuDTO) {
        log.debug("Request to save Menu : {}", menuDTO);
        Menu menu = menuMapper.toEntity(menuDTO);
        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    /**
     *  Get all the menus.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MenuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Menus");
        return menuRepository.findAll(pageable)
            .map(menuMapper::toDto);
    }

    /**
     *  Get one menu by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public MenuDTO findOne(Long id) {
        log.debug("Request to get Menu : {}", id);
        Menu menu = menuRepository.findOne(id);
        return menuMapper.toDto(menu);
    }

    /**
     *  Delete the  menu by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Menu : {}", id);
        menuRepository.delete(id);
    }
}
