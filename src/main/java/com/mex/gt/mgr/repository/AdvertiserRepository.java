package com.mex.gt.mgr.repository;

import com.mex.gt.mgr.domain.Advertiser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Advertiser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {

}
