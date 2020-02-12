package com.camelcert.ex421.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.RestConfiguration.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * @author RTS Sathish Kumar
 *
 *         Demonstrates Rest API using servlet and other component
 */
@Component
public class JettyRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		/**
		 * Rest API using Servlet component
		 */

		restConfiguration("jetty").contextPath("/jetty").host("localhost").port(8082)
				.bindingMode(org.apache.camel.model.rest.RestBindingMode.off);

		//@formatter:off
						
		rest().get("/hello/{id}").produces("text/html").route()
			.setHeader("COMPONENT", simple("REST")).setBody(simple("rest javadsl method ${header.id}"))
				.to("direct-vm:jettyvmtest");
		
		from("jetty:http://localhost:8082/jetty/fromapi")
			.setHeader("COMPONENT", simple("FROM")).setBody(constant("jetty from URI component"))
				.to("direct-vm:jettyvmtest");
		
		//@formatter:on

		from("direct-vm:jettyvmtest").setBody(simple("Welcome to enrriched loggerr :  ${body}"))
				.log("${body} enriched jetty payload");
	}
}