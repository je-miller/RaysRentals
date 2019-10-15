package com.bbn.lwit.raysrentals.service;

import com.bbn.lwit.raysrentals.domain.VisitorLocation;
import com.bbn.lwit.raysrentals.repository.VisitorLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VisitorLocation}.
 */
@Service
public class VisitorLocationService {

    private final Logger log = LoggerFactory.getLogger(VisitorLocationService.class);

    private final VisitorLocationRepository visitorLocationRepository;

    public VisitorLocationService(VisitorLocationRepository visitorLocationRepository) {
        this.visitorLocationRepository = visitorLocationRepository;
    }

    /**
     * Save a visitorLocation.
     *
     * @param visitorLocation the entity to save.
     * @return the persisted entity.
     */
    public VisitorLocation save(VisitorLocation visitorLocation) {
        log.debug("Request to save VisitorLocation : {}", visitorLocation);
        return visitorLocationRepository.save(visitorLocation);
    }

    /**
     * Get all the visitorLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<VisitorLocation> findAll(Pageable pageable) {
        log.debug("Request to get all VisitorLocations");
        return visitorLocationRepository.findAll(pageable);
    }


    /**
     * Get one visitorLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<VisitorLocation> findOne(String id) {
        log.debug("Request to get VisitorLocation : {}", id);
        return visitorLocationRepository.findById(id);
    }

    /**
     * Delete the visitorLocation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete VisitorLocation : {}", id);
        visitorLocationRepository.deleteById(id);
    }
}
