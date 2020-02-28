package com.ews.camel.service;

import com.ews.camel.model.Route;
import com.ews.camel.repository.RouteConfigurationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RouteConfigurationService {

    private RouteConfigurationRepository repository;

    public InputStream routes() {
        final List<Route> routes = this.repository.findAll();

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
