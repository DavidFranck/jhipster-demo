package com.mex.adx.mgr.service;

import com.mex.adx.mgr.domain.Authority;
import com.mex.adx.mgr.repository.AuthorityRepository;
import com.mex.adx.mgr.service.dto.AuthorityDTO;
import com.mex.adx.mgr.service.mapper.AuthorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Authority.
 */
@Service
@Transactional
public class AuthorityService {

    private final Logger log = LoggerFactory.getLogger(AuthorityService.class);

    private final AuthorityRepository authorityRepository;

    private final AuthorityMapper authorityMapper;

    public AuthorityService(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    /**
     * Save a authority.
     *
     * @param authorityDTO the entity to save
     * @return the persisted entity
     */
    public AuthorityDTO save(AuthorityDTO authorityDTO) {
        log.debug("Request to save Authority : {}", authorityDTO);
        Authority authority = authorityMapper.toEntity(authorityDTO);
        authority = authorityRepository.save(authority);
        return authorityMapper.toDto(authority);
    }

    /**
     *  Get all the authorities.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Authorities");
        return authorityRepository.findAll(pageable)
            .map(authorityMapper::toDto);
    }

    /**
     *  Get one authority by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public AuthorityDTO findOne(Long id) {
        log.debug("Request to get Authority : {}", id);
        Authority authority = authorityRepository.findOneWithEagerRelationships(id);
        return authorityMapper.toDto(authority);
    }

    /**
     *  Delete the  authority by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Authority : {}", id);
        authorityRepository.delete(id);
    }
}
