package com.bbn.lwit.raysrentals.web.rest;

import com.bbn.lwit.raysrentals.domain.VisitorLocation;
import com.bbn.lwit.raysrentals.service.VisitorLocationService;
import com.bbn.lwit.raysrentals.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.bbn.lwit.raysrentals.domain.VisitorLocation}.
 */
@RestController
@RequestMapping("/api")
public class VisitorLocationResource {

    private final Logger log = LoggerFactory.getLogger(VisitorLocationResource.class);

    private static final String ENTITY_NAME = "visitorLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VisitorLocationService visitorLocationService;

    public VisitorLocationResource(VisitorLocationService visitorLocationService) {
        this.visitorLocationService = visitorLocationService;
    }

    /**
     * {@code POST  /visitor-locations} : Create a new visitorLocation.
     *
     * @param visitorLocation the visitorLocation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new visitorLocation, or with status {@code 400 (Bad Request)} if the visitorLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/visitor-locations")
    public ResponseEntity<VisitorLocation> createVisitorLocation(@RequestBody VisitorLocation visitorLocation) throws URISyntaxException {
        log.debug("REST request to save VisitorLocation : {}", visitorLocation);
        if (visitorLocation.getId() != null) {
            throw new BadRequestAlertException("A new visitorLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VisitorLocation result = visitorLocationService.save(visitorLocation);
        return ResponseEntity.created(new URI("/api/visitor-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /visitor-locations} : Updates an existing visitorLocation.
     *
     * @param visitorLocation the visitorLocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated visitorLocation,
     * or with status {@code 400 (Bad Request)} if the visitorLocation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the visitorLocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/visitor-locations")
    public ResponseEntity<VisitorLocation> updateVisitorLocation(@RequestBody VisitorLocation visitorLocation) throws URISyntaxException {
        log.debug("REST request to update VisitorLocation : {}", visitorLocation);
        if (visitorLocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VisitorLocation result = visitorLocationService.save(visitorLocation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, visitorLocation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /visitor-locations} : get all the visitorLocations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of visitorLocations in body.
     */
    @GetMapping("/visitor-locations")
    public ResponseEntity<List<VisitorLocation>> getAllVisitorLocations(Pageable pageable) {
        log.debug("REST request to get a page of VisitorLocations");
        Page<VisitorLocation> page = visitorLocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /visitor-locations/:id} : get the "id" visitorLocation.
     *
     * @param id the id of the visitorLocation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the visitorLocation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/visitor-locations/{id}")
    public ResponseEntity<VisitorLocation> getVisitorLocation(@PathVariable String id) {
        log.debug("REST request to get VisitorLocation : {}", id);
        Optional<VisitorLocation> visitorLocation = visitorLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(visitorLocation);
    }

    /**
     * {@code DELETE  /visitor-locations/:id} : delete the "id" visitorLocation.
     *
     * @param id the id of the visitorLocation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/visitor-locations/{id}")
    public ResponseEntity<Void> deleteVisitorLocation(@PathVariable String id) {
        log.debug("REST request to delete VisitorLocation : {}", id);
        visitorLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
