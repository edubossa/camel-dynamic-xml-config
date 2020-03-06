package com.ews.camel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("description")
    private String description;

    @JsonProperty("errors")
    private List<Error> errors = new ArrayList<>();

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String message) {
        this.message = message;
    }

    public ApiErrorResponse(List<Error> errors) {
    	super();
    	this.errors = errors;
    }
}
