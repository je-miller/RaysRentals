package com.bbn.lwit.raysrentals.repository;
import com.bbn.lwit.raysrentals.domain.VisitorLocation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the VisitorLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VisitorLocationRepository extends MongoRepository<VisitorLocation, String> {

}
