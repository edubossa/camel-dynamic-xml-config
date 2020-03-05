package com.ews.camel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * To monitor http://localhost:8080/actuator/hawtio
 */
@Slf4j
@RestController
@RequestMapping("/camel")
public class CamelController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @SneakyThrows(JsonProcessingException.class)
    @PostMapping(value = "/route")
    JsonNode route(@RequestBody JsonNode body)  {
        String json = (String) producerTemplate.requestBody("direct:httpMultcastAsync", body.asText());
        log.info("Response called Http Multicast : " +json);
        return new ObjectMapper().readTree(json);
    }

}
