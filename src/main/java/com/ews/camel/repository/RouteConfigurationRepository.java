package com.ews.camel.repository;

import com.ews.camel.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteConfigurationRepository extends MongoRepository<Route, String> {


}
