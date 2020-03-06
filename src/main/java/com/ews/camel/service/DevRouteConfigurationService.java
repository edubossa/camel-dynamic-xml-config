package com.ews.camel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
@Profile("dev")
public class DevRouteConfigurationService implements RouteConfiguration {

    @Override
    public InputStream routes() {
        return getClass().getClassLoader().getResourceAsStream("camel-config.xml");
    }

}
