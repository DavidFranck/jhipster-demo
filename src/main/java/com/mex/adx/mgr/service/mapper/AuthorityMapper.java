package com.mex.adx.mgr.service.mapper;

import com.mex.adx.mgr.domain.*;
import com.mex.adx.mgr.service.dto.AuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Authority and its DTO AuthorityDTO.
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, })
public interface AuthorityMapper extends EntityMapper <AuthorityDTO, Authority> {
    
    
    default Authority fromId(Long id) {
        if (id == null) {
            return null;
        }
        Authority authority = new Authority();
        authority.setId(id);
        return authority;
    }
}
