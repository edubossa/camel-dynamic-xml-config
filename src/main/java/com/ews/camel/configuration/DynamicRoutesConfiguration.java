package com.ews.camel.configuration;

import com.ews.camel.service.RouteConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ExtendedCamelContext;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DynamicRoutesConfiguration implements CamelContextConfiguration {

    private RouteConfiguration routeConfiguration;

    @Override
    public void beforeApplicationStart(CamelContext camelContext) {
        log.info("DynamicRoutesConfiguration.beforeApplicationStart");
    }

    @Override
    public void afterApplicationStart(CamelContext camelContext) {
        log.info("DynamicRoutesConfiguration.afterApplicationStart");
        try {
            ExtendedCamelContext ecc = camelContext.adapt(ExtendedCamelContext.class);

            RoutesDefinition routeDefinition = (RoutesDefinition) ecc
                            .getXMLRoutesDefinitionLoader()
                            .loadRoutesDefinition(ecc, this.routeConfiguration.routes());

            ModelCamelContext adapt = camelContext.adapt(ModelCamelContext.class);
            adapt.addRouteDefinitions(routeDefinition.getRoutes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
