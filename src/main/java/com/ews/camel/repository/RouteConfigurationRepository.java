package com.ews.camel.repository;

import com.ews.camel.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteConfigurationRepository extends MongoRepository<Route, String> {

    List<Route> findAllByApplicationName(String applicationName);

}
