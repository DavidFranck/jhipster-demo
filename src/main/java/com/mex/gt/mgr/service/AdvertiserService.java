package com.mex.gt.mgr.service;

import com.mex.gt.mgr.domain.Advertiser;
import com.mex.gt.mgr.repository.AdvertiserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Advertiser.
 */
@Service
@Transactional
public class AdvertiserService {

    private final Logger log = LoggerFactory.getLogger(AdvertiserService.class);

    private final AdvertiserRepository advertiserRepository;

    public AdvertiserService(AdvertiserRepository advertiserRepository) {
        this.advertiserRepository = advertiserRepository;
    }

    /**
     * Save a advertiser.
     *
     * @param advertiser the entity to save
     * @return the persisted entity
     */
    public Advertiser save(Advertiser advertiser) {
        log.debug("Request to save Advertiser : {}", advertiser);
        return advertiserRepository.save(advertiser);
    }

    /**
     *  Get all the advertisers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Advertiser> findAll(Pageable pageable) {
        log.debug("Request to get all Advertisers");
        return advertiserRepository.findAll(pageable);
    }

    /**
     *  Get one advertiser by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Advertiser findOne(Long id) {
        log.debug("Request to get Advertiser : {}", id);
        return advertiserRepository.findOne(id);
    }

    /**
     *  Delete the  advertiser by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Advertiser : {}", id);
        advertiserRepository.delete(id);
    }
}
