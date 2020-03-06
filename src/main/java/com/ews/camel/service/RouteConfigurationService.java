package com.ews.camel.service;

import com.ews.camel.model.Route;
import com.ews.camel.repository.RouteConfigurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Profile("!dev")
public class RouteConfigurationService implements RouteConfiguration {

    @Autowired
    private RouteConfigurationRepository repository;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public InputStream routes() {
        final List<Route> routes = this.repository.findAllByApplicationName(this.applicationName);

        StringBuilder sb = new StringBuilder();
        sb.append("<routes xmlns=\"http://camel.apache.org/schema/spring\">");
        final List<String> listRoute = routes.stream().map(r -> r.getRoute()).collect(Collectors.toList());
        sb.append(listRoute);
        sb.append("</routes>");

        log.info("Loading routes ...");
        log.info(sb.toString());

        return new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
