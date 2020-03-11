package com.ews.camel.strategy;

import com.ews.camel.model.Step;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StepsAggregationStrategy implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        List<Step> list = null;
        if (oldExchange == null) {
            list = new ArrayList<>();
            list.add(createStep(newExchange));
            newExchange.getIn().setBody(list, ArrayList.class);
            return newExchange;
        } else {
            list = oldExchange.getIn().getBody(ArrayList.class);
            list.add(createStep(newExchange));
            return oldExchange;
        }
    }

    @lombok.SneakyThrows(JsonProcessingException.class)
    private Step createStep(Exchange newExchange) {
        final String stepName = newExchange.getIn().getHeader("stepName", String.class);
        Assert.notNull(stepName, "Header [stepName] not found!");
        final String body = newExchange.getIn().getBody(String.class);
        return Step.builder()
                        .name(stepName)
                        .response(new ObjectMapper().readTree(body))
                        .startDate(new Date(newExchange.getCreated()))
                        .endDate(new Date())
                    .build();
    }

}
