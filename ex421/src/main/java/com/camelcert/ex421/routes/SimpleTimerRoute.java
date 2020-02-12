package com.camelcert.ex421.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author RTS Sathish Kumar
 *
 *         Demonstrates about Exchange / In / Out / Message - and their payload
 *         method
 */
@Component
public class SimpleTimerRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		//@formatter:off
		from("timer:foo?period=3h&fixedRate=true")
			.routeId("jdsltimer")
				.setBody(constant("Timer executed " + System.currentTimeMillis()))
					.to("direct-vm:timerprinter");
		//@formatter:on

		from("direct-vm:timerprinter").routeId("direct-vm:timerprinter").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				Message in = exchange.getIn();
				// getOut and getMessage having no value here, but getIn made available as
				// payload when invoking endpoint
				//System.out.println(exchange.getFromRouteId() + " ->" + in.getBody());

				in.setBody(in.getBody() + " out enriched ");
				exchange.setOut(in);
				exchange.setMessage(in);
			}
		}).to("direct-vm:printer");

		from("direct-vm:printer").routeId("direct-vm:printer").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				Message in = exchange.getIn();
				//System.out.println(exchange.getFromRouteId() + " ->" + in.getBody());
			}
		}).log("${body}");
	}
}