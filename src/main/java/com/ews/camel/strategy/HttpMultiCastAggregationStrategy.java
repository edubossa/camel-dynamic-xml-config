package com.ews.camel.strategy;

import com.ews.camel.model.Step;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class HttpMultiCastAggregationStrategy implements AggregationStrategy {

    @lombok.SneakyThrows
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        final Date startDate = new Date(newExchange.getCreated());
        String newBody = newExchange.getIn().getBody(String.class);
        List<Step> list = null;
        if (oldExchange == null) {
            list = new ArrayList<>();
            Step step = Step.builder()
                    .name(newExchange.getIn().getHeader("stepName", String.class))
                    .response(new ObjectMapper().readTree(newBody))
                    .startDate(startDate)
                    .endDate(new Date())
                    .build();
            list.add(step);
            newExchange.getIn().setBody(list, ArrayList.class);
            return newExchange;
        } else {
            list = oldExchange.getIn().getBody(ArrayList.class);
            Step step = Step.builder()
                    .name(newExchange.getIn().getHeader("stepName", String.class))
                    .response(new ObjectMapper().readTree(newBody))
                    .startDate(startDate)
                    .endDate(new Date())
                    .build();
            list.add(step);
            return oldExchange;
        }
    }

}
