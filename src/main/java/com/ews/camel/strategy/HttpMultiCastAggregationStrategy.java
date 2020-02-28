package com.ews.camel.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class HttpMultiCastAggregationStrategy implements AggregationStrategy {

    ObjectMapper mapper = new ObjectMapper();

    @lombok.SneakyThrows
    public Exchange aggregate(Exchange e1, Exchange e2) {
        if (e1 == null) {
            return e2;
        } else {
            String body1 = e1.getIn().getBody(String.class);
            String body2 = e2.getIn().getBody(String.class);

            JsonNode node1 = mapper.readTree(body1);
            JsonNode node2 = mapper.readTree(body2);

            /*String service1 = e1.getIn().getHeader("serviceName", String.class);
            System.out.println(service1);
            String service2 = e2.getIn().getHeader("serviceName", String.class);
            System.out.println(service2);

            ObjectNode merge = mapper.createObjectNode();
            merge.putPOJO(service1, node1);
            merge.putPOJO(service2, node2);
            System.out.println(merge.toString());*/

            ObjectNode merge = mapper.createObjectNode();
            merge.putPOJO("request01", node1);
            merge.putPOJO("request02", node2);
            System.out.println(merge.toString());

            e1.getIn().setBody(merge, JsonNode.class);
            return e1;
        }

    }


}
