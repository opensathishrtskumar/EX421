package com.camelcert.ex421.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author RTS Sathish Kumar
 *
 *         Demonstrates Rest API using servlet and other component
 */
@Component
public class ServletRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		/**
		 * Rest API using Servlet component
		 */

		//@formatter:off
		from("servlet:servletone")
			.routeId("servlethelloroute")
					.setBody(header("Accept"))
						.to("direct-vm:servletlogger");
		
		from("servlet:servletprocessor")
			.routeId("servleproccessorroute")
				.process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							exchange.getMessage().setHeader("COMPONENT", "SERVLET-PROCESSOR");
						}
					})
					.setBody(header("Accept"))
						.to("direct-vm:servletlogger");
		//@formatter:on

		from("direct-vm:servletlogger").log("Servlet Logger : ${body}");

	}
}