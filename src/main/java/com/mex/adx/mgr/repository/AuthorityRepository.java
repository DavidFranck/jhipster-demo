package com.mex.adx.mgr.repository;

import com.mex.adx.mgr.domain.Authority;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("select distinct authority from Authority authority left join fetch authority.menus")
    List<Authority> findAllWithEagerRelationships();

    @Query("select authority from Authority authority left join fetch authority.menus where authority.id =:id")
    Authority findOneWithEagerRelationships(@Param("id") Long id);

    Authority findByName(String name);
}
