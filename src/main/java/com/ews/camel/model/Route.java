package com.ews.camel.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(value = "route")
public class Route {

    @Id
    private String id;
    private String route;

}
