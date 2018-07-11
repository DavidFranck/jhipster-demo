package com.mex.gt.mgr.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mex.gt.mgr.domain.Advertiser;
import com.mex.gt.mgr.service.AdvertiserService;
import com.mex.gt.mgr.web.rest.util.HeaderUtil;
import com.mex.gt.mgr.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Advertiser.
 */
@RestController
@RequestMapping("/api")
public class AdvertiserResource {

    private final Logger log = LoggerFactory.getLogger(AdvertiserResource.class);

    private static final String ENTITY_NAME = "advertiser";

    private final AdvertiserService advertiserService;

    public AdvertiserResource(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }

    /**
     * POST  /advertisers : Create a new advertiser.
     *
     * @param advertiser the advertiser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new advertiser, or with status 400 (Bad Request) if the advertiser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/advertisers")
    @Timed
    public ResponseEntity<Advertiser> createAdvertiser(@Valid @RequestBody Advertiser advertiser) throws URISyntaxException {
        log.debug("REST request to save Advertiser : {}", advertiser);
        if (advertiser.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new advertiser cannot already have an ID")).body(null);
        }
        Advertiser result = advertiserService.save(advertiser);
        return ResponseEntity.created(new URI("/api/advertisers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /advertisers : Updates an existing advertiser.
     *
     * @param advertiser the advertiser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated advertiser,
     * or with status 400 (Bad Request) if the advertiser is not valid,
     * or with status 500 (Internal Server Error) if the advertiser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/advertisers")
    @Timed
    public ResponseEntity<Advertiser> updateAdvertiser(@Valid @RequestBody Advertiser advertiser) throws URISyntaxException {
        log.debug("REST request to update Advertiser : {}", advertiser);
        if (advertiser.getId() == null) {
            return createAdvertiser(advertiser);
        }
        Advertiser result = advertiserService.save(advertiser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, advertiser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /advertisers : get all the advertisers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of advertisers in body
     */
    @GetMapping("/advertisers")
    @Timed
    public ResponseEntity<List<Advertiser>> getAllAdvertisers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Advertisers");
        Page<Advertiser> page = advertiserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/advertisers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /advertisers/:id : get the "id" advertiser.
     *
     * @param id the id of the advertiser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the advertiser, or with status 404 (Not Found)
     */
    @GetMapping("/advertisers/{id}")
    @Timed
    public ResponseEntity<Advertiser> getAdvertiser(@PathVariable Long id) {
        log.debug("REST request to get Advertiser : {}", id);
        Advertiser advertiser = advertiserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(advertiser));
    }

    /**
     * DELETE  /advertisers/:id : delete the "id" advertiser.
     *
     * @param id the id of the advertiser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/advertisers/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdvertiser(@PathVariable Long id) {
        log.debug("REST request to delete Advertiser : {}", id);
        advertiserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
