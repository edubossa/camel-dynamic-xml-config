package com.ews.camel.configuration;

import com.ews.camel.service.RouteConfigurationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DynamicRoutesConfiguration implements CamelContextConfiguration {

    private RouteConfigurationService routeConfigurationService;

    @Override
    public void beforeApplicationStart(CamelContext camelContext) {
        log.info("DynamicRoutesConfiguration.beforeApplicationStart");
    }

    @Override
    public void afterApplicationStart(CamelContext camelContext) {
        log.info("DynamicRoutesConfiguration.afterApplicationStart");
        try {
            RoutesDefinition routes = camelContext.loadRoutesDefinition(this.routeConfigurationService.routes());
            camelContext.addRouteDefinitions(routes.getRoutes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
