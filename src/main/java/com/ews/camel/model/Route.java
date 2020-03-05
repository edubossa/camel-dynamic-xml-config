package com.ews.camel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(value = "route")
@AllArgsConstructor(staticName = "of")
public class Route {

    @Id
    private String id;
    private String applicationName;
    private String route;

}
