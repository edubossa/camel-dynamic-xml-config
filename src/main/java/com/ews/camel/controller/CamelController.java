package com.ews.camel.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/camel")
public class CamelController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping(value = "/route")
    JsonNode route(@RequestBody JsonNode body) {
        JsonNode json = (JsonNode) producerTemplate.requestBody("direct:httpMultcastAsync", body.asText());
        log.info("Response called Http Multicast : " + json.toString());
        return json;
    }

}
