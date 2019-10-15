package com.bbn.lwit.raysrentals.web.rest;

import com.bbn.lwit.raysrentals.domain.VisitorLocation;
import com.bbn.lwit.raysrentals.service.Ip2LocationService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * REST controller for managing {@link VisitorLocation}.
 */
@RestController
@RequestMapping("/api")
public class Ip2LocationResource {

    private final Logger log = LoggerFactory.getLogger(Ip2LocationResource.class);

    private static final String ENTITY_NAME = "visitorLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Ip2LocationService ip2LocationService = new Ip2LocationService("IP2LOCATION-LITE-DB11.BIN");

    public Ip2LocationResource() throws IOException {
    }

    @GetMapping("/ip2location/{ip}")
    public ResponseEntity<VisitorLocation> getVisitorLocation(@PathVariable String ip) throws IOException {
        log.debug("REST request to get VisitorLocation : {}", ip);
        Optional<VisitorLocation> visitorLocation = Optional.ofNullable(ip2LocationService.query(ip));
        return ResponseUtil.wrapOrNotFound(visitorLocation);
    }

////   Will only return 127.0.0.1 in dev mode
//    @GetMapping("/ip2location")
//    public ResponseEntity<VisitorLocation> getVisitorLocation(HttpServletRequest request) throws IOException {
//        String ip = request.getRemoteAddr();
//        log.debug("REST request to get VisitorLocation : {}", ip);
//        Optional<VisitorLocation> visitorLocation = Optional.ofNullable(ip2LocationService.query(ip));
//        return ResponseUtil.wrapOrNotFound(visitorLocation);
//    }
}
