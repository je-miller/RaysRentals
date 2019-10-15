package com.bbn.lwit.raysrentals.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A VisitorLocation.
 */
@Document(collection = "visitor_location")
public class VisitorLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("ip")
    private String ip;

    @Field("country_code")
    private String countryCode;

    @Field("country_name")
    private String countryName;

    @Field("region")
    private String region;

    @Field("city")
    private String city;

    @Field("latitude")
    private Float latitude;

    @Field("longitude")
    private Float longitude;

    @Field("zipcode")
    private String zipcode;

    @Field("timezone")
    private String timezone;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public VisitorLocation ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public VisitorLocation countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public VisitorLocation countryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegion() {
        return region;
    }

    public VisitorLocation region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public VisitorLocation city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getLatitude() {
        return latitude;
    }

    public VisitorLocation latitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public VisitorLocation longitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getZipcode() {
        return zipcode;
    }

    public VisitorLocation zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTimezone() {
        return timezone;
    }

    public VisitorLocation timezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VisitorLocation)) {
            return false;
        }
        return id != null && id.equals(((VisitorLocation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VisitorLocation{" +
            "id=" + getId() +
            ", ip='" + getIp() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", countryName='" + getCountryName() + "'" +
            ", region='" + getRegion() + "'" +
            ", city='" + getCity() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", zipcode='" + getZipcode() + "'" +
            ", timezone='" + getTimezone() + "'" +
            "}";
    }
}
