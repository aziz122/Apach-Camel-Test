package fr.codeonce.apachcamel;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;


public class MyCamelRouteTest extends CamelTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = new DefaultCamelContext();
        // Configure any additional Camel context settings required for your test
        return context;
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MyCamelRoute();
    }

    @Test
    public void testCamelRoute() throws Exception {
        // Use the `advicewithRouteBuilder` method to modify the route for testing purposes
        AdviceWithRouteBuilder.adviceWith(context, "my-camel-route", routeBuilder -> {
            // Replace the endpoint with a direct endpoint for testing
            routeBuilder.replaceFromWith("direct:start");
            // Add any additional modifications or mocks as necessary
        });

        // Start the Camel context
        context.start();

        // Define your test expectations and assertions
        // Use the `template` field provided by CamelTestSupport to send messages to the route
        template.sendBody("direct:start", "Test message");

   
        // Use the `getMockEndpoint` method to access the mock endpoint and validate the results
        getMockEndpoint("mock:result").expectedBodiesReceived("Test message");

      
        // Use the `assertMockEndpointsSatisfied` method to ensure all expectations are met
        
        assertNotNull(context.hasEndpoint("direct:start"));
        assertNotNull(context.hasEndpoint("mock:result"));
        assertMockEndpointsSatisfied();
    }
}
