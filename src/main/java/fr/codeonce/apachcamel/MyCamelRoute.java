package fr.codeonce.apachcamel;

import org.apache.camel.builder.RouteBuilder;



public class MyCamelRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:start")
            .routeId("my-camel-route")
            .log("Received message: ${body}")
            .to("mock:result");
    }

}
