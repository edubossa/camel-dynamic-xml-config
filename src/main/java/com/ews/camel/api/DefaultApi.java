package com.ews.camel.api;

import com.ews.camel.model.ApiErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.camel.ExchangeException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

// https://github.com/bszeti/camel-springboot/blob/master/camel-rest-complex/src/main/java/my/company/route/RestEndpoints.java
// https://github.com/Talend/apache-camel/tree/master/examples/camel-example-spring-boot-rest-swagger
//https://github.com/Talend/apache-camel/blob/master/examples/camel-example-servlet-rest-blueprint/src/main/resources/OSGI-INF/blueprint/camel.xml

//@Component("defaultApi")
public class DefaultApi extends RouteBuilder {

    //@Override
    public void configure() throws Exception {

        /************************
         * common exception handlers for all routes defined in this RouteBuilder
         ************************/

        /*onException(HttpOperationFailedException.class)
                .handled(true)
                .to("log:"+ DefaultApi.class.getName()+"?showAll=true&multiline=true&level=ERROR")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(452))
                .bean("defaultApi","errorResponse('ERROR')");*/


        /*onException(HttpOperationFailedException.class)
                .onWhen(exchange -> {
                    HttpOperationFailedException exe = exchange.getException(HttpOperationFailedException.class);
                    return 204 == exe.getStatusCode();
                })
                .log("HTTP exception handled CHUPA")
                .handled(true)
                //.continued(true)
                .setBody(constant("{{\n" +
                        "    \"code\" : \"7897234\",\n" +
                        "    \"message\" : \"FUDEU\"\n" +
                        "  }}"));*/

       /* onException(Exception.class)
                .handled(true)
                .to("log:"+ DefaultApi.class.getName()+"?showAll=true&multiline=true&level=ERROR")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(500))
                .bean("defaultApi","errorResponse(*)");*/


        rest("/connector")
                .produces("application/json")
                .consumes("application/json")
                //.skipBindingOnErrorCode(false) //Enable json marshalling for body in case of errors

        .post("/quotation")
                .type(JsonNode.class)
                .route().routeId("post-quotation")
                    //.log("User received: ${body}").id("received-user") //This step gets an id, so we can refer it in test
                .to("direct:steps")
                .marshal().json(JsonLibrary.Jackson)

                    //https://stackoverflow.com/questions/41008023/apache-camel-unable-to-get-the-exception-body
                    /*.setHeader(Exchange.HTTP_METHOD, constant("GET"))
                    .to("http://www.mocky.io/v2/5e62bd803600007100e8dc20?bridgeEndpoint=true&throwExceptionOnFailure=false")
                    .choice()
                        .when(header(Exchange.HTTP_RESPONSE_CODE).isLessThan(300))
                            // HTTP status < 300
                            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                            .to("http://www.mocky.io/v2/5e50287e3000009000226e4f?bridgeEndpoint=true")
                        .otherwise()
                            // HTTP status >= 300 : would throw an exception if we had "throwExceptionOnFailure=true"
                            .log("FUDEU Error response: ${body}")
                            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                            .to("http://www.mocky.io/v2/5e5028643000009000226e4e?bridgeEndpoint=true")*/

                .endRest();

    }

    public static ApiErrorResponse errorResponse(String message){
        return new ApiErrorResponse(message);
    }

    public static ApiErrorResponse errorResponse(@ExchangeException Exception ex){
        return new ApiErrorResponse(ex.getMessage());
    }

}
