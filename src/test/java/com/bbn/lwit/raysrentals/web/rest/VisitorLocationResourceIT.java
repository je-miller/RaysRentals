package com.bbn.lwit.raysrentals.web.rest;

import com.bbn.lwit.raysrentals.RaysRentalsApp;
import com.bbn.lwit.raysrentals.domain.VisitorLocation;
import com.bbn.lwit.raysrentals.repository.VisitorLocationRepository;
import com.bbn.lwit.raysrentals.service.VisitorLocationService;
import com.bbn.lwit.raysrentals.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.util.List;

import static com.bbn.lwit.raysrentals.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link VisitorLocationResource} REST controller.
 */
@SpringBootTest(classes = RaysRentalsApp.class)
public class VisitorLocationResourceIT {

    private static final String DEFAULT_IP = "AAAAAAAAAA";
    private static final String UPDATED_IP = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final Float DEFAULT_LATITUDE = 1F;
    private static final Float UPDATED_LATITUDE = 2F;

    private static final Float DEFAULT_LONGITUDE = 1F;
    private static final Float UPDATED_LONGITUDE = 2F;

    private static final String DEFAULT_ZIPCODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIPCODE = "BBBBBBBBBB";

    private static final String DEFAULT_TIMEZONE = "AAAAAAAAAA";
    private static final String UPDATED_TIMEZONE = "BBBBBBBBBB";

    @Autowired
    private VisitorLocationRepository visitorLocationRepository;

    @Autowired
    private VisitorLocationService visitorLocationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restVisitorLocationMockMvc;

    private VisitorLocation visitorLocation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VisitorLocationResource visitorLocationResource = new VisitorLocationResource(visitorLocationService);
        this.restVisitorLocationMockMvc = MockMvcBuilders.standaloneSetup(visitorLocationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VisitorLocation createEntity() {
        VisitorLocation visitorLocation = new VisitorLocation()
            .ip(DEFAULT_IP)
            .countryCode(DEFAULT_COUNTRY_CODE)
            .countryName(DEFAULT_COUNTRY_NAME)
            .region(DEFAULT_REGION)
            .city(DEFAULT_CITY)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .zipcode(DEFAULT_ZIPCODE)
            .timezone(DEFAULT_TIMEZONE);
        return visitorLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VisitorLocation createUpdatedEntity() {
        VisitorLocation visitorLocation = new VisitorLocation()
            .ip(UPDATED_IP)
            .countryCode(UPDATED_COUNTRY_CODE)
            .countryName(UPDATED_COUNTRY_NAME)
            .region(UPDATED_REGION)
            .city(UPDATED_CITY)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .zipcode(UPDATED_ZIPCODE)
            .timezone(UPDATED_TIMEZONE);
        return visitorLocation;
    }

    @BeforeEach
    public void initTest() {
        visitorLocationRepository.deleteAll();
        visitorLocation = createEntity();
    }

    @Test
    public void createVisitorLocation() throws Exception {
        int databaseSizeBeforeCreate = visitorLocationRepository.findAll().size();

        // Create the VisitorLocation
        restVisitorLocationMockMvc.perform(post("/api/visitor-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(visitorLocation)))
            .andExpect(status().isCreated());

        // Validate the VisitorLocation in the database
        List<VisitorLocation> visitorLocationList = visitorLocationRepository.findAll();
        assertThat(visitorLocationList).hasSize(databaseSizeBeforeCreate + 1);
        VisitorLocation testVisitorLocation = visitorLocationList.get(visitorLocationList.size() - 1);
        assertThat(testVisitorLocation.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testVisitorLocation.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testVisitorLocation.getCountryName()).isEqualTo(DEFAULT_COUNTRY_NAME);
        assertThat(testVisitorLocation.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testVisitorLocation.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testVisitorLocation.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testVisitorLocation.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testVisitorLocation.getZipcode()).isEqualTo(DEFAULT_ZIPCODE);
        assertThat(testVisitorLocation.getTimezone()).isEqualTo(DEFAULT_TIMEZONE);
    }

    @Test
    public void createVisitorLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = visitorLocationRepository.findAll().size();

        // Create the VisitorLocation with an existing ID
        visitorLocation.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restVisitorLocationMockMvc.perform(post("/api/visitor-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(visitorLocation)))
            .andExpect(status().isBadRequest());

        // Validate the VisitorLocation in the database
        List<VisitorLocation> visitorLocationList = visitorLocationRepository.findAll();
        assertThat(visitorLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllVisitorLocations() throws Exception {
        // Initialize the database
        visitorLocationRepository.save(visitorLocation);

        // Get all the visitorLocationList
        restVisitorLocationMockMvc.perform(get("/api/visitor-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(visitorLocation.getId())))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP)))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].countryName").value(hasItem(DEFAULT_COUNTRY_NAME)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].zipcode").value(hasItem(DEFAULT_ZIPCODE)))
            .andExpect(jsonPath("$.[*].timezone").value(hasItem(DEFAULT_TIMEZONE)));
    }
    
    @Test
    public void getVisitorLocation() throws Exception {
        // Initialize the database
        visitorLocationRepository.save(visitorLocation);

        // Get the visitorLocation
        restVisitorLocationMockMvc.perform(get("/api/visitor-locations/{id}", visitorLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(visitorLocation.getId()))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.countryName").value(DEFAULT_COUNTRY_NAME))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.zipcode").value(DEFAULT_ZIPCODE))
            .andExpect(jsonPath("$.timezone").value(DEFAULT_TIMEZONE));
    }

    @Test
    public void getNonExistingVisitorLocation() throws Exception {
        // Get the visitorLocation
        restVisitorLocationMockMvc.perform(get("/api/visitor-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateVisitorLocation() throws Exception {
        // Initialize the database
        visitorLocationService.save(visitorLocation);

        int databaseSizeBeforeUpdate = visitorLocationRepository.findAll().size();

        // Update the visitorLocation
        VisitorLocation updatedVisitorLocation = visitorLocationRepository.findById(visitorLocation.getId()).get();
        updatedVisitorLocation
            .ip(UPDATED_IP)
            .countryCode(UPDATED_COUNTRY_CODE)
            .countryName(UPDATED_COUNTRY_NAME)
            .region(UPDATED_REGION)
            .city(UPDATED_CITY)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .zipcode(UPDATED_ZIPCODE)
            .timezone(UPDATED_TIMEZONE);

        restVisitorLocationMockMvc.perform(put("/api/visitor-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVisitorLocation)))
            .andExpect(status().isOk());

        // Validate the VisitorLocation in the database
        List<VisitorLocation> visitorLocationList = visitorLocationRepository.findAll();
        assertThat(visitorLocationList).hasSize(databaseSizeBeforeUpdate);
        VisitorLocation testVisitorLocation = visitorLocationList.get(visitorLocationList.size() - 1);
        assertThat(testVisitorLocation.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testVisitorLocation.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testVisitorLocation.getCountryName()).isEqualTo(UPDATED_COUNTRY_NAME);
        assertThat(testVisitorLocation.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testVisitorLocation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testVisitorLocation.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testVisitorLocation.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testVisitorLocation.getZipcode()).isEqualTo(UPDATED_ZIPCODE);
        assertThat(testVisitorLocation.getTimezone()).isEqualTo(UPDATED_TIMEZONE);
    }

    @Test
    public void updateNonExistingVisitorLocation() throws Exception {
        int databaseSizeBeforeUpdate = visitorLocationRepository.findAll().size();

        // Create the VisitorLocation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVisitorLocationMockMvc.perform(put("/api/visitor-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(visitorLocation)))
            .andExpect(status().isBadRequest());

        // Validate the VisitorLocation in the database
        List<VisitorLocation> visitorLocationList = visitorLocationRepository.findAll();
        assertThat(visitorLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteVisitorLocation() throws Exception {
        // Initialize the database
        visitorLocationService.save(visitorLocation);

        int databaseSizeBeforeDelete = visitorLocationRepository.findAll().size();

        // Delete the visitorLocation
        restVisitorLocationMockMvc.perform(delete("/api/visitor-locations/{id}", visitorLocation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VisitorLocation> visitorLocationList = visitorLocationRepository.findAll();
        assertThat(visitorLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VisitorLocation.class);
        VisitorLocation visitorLocation1 = new VisitorLocation();
        visitorLocation1.setId("id1");
        VisitorLocation visitorLocation2 = new VisitorLocation();
        visitorLocation2.setId(visitorLocation1.getId());
        assertThat(visitorLocation1).isEqualTo(visitorLocation2);
        visitorLocation2.setId("id2");
        assertThat(visitorLocation1).isNotEqualTo(visitorLocation2);
        visitorLocation1.setId(null);
        assertThat(visitorLocation1).isNotEqualTo(visitorLocation2);
    }
}
