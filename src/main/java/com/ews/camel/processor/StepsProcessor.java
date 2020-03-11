package com.ews.camel.processor;

import com.ews.camel.model.Step;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StepsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        final ArrayList<Step> steps = exchange.getIn().getBody(ArrayList.class);
        addIndex(steps);
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.putPOJO("steps", steps);
        exchange.getIn().setBody(node.toString());
    }

    private void addIndex(ArrayList<Step> steps) {
        AtomicInteger index = new AtomicInteger(1);
        steps.forEach(step -> step.setIndex(index.getAndIncrement()));
    }

}
