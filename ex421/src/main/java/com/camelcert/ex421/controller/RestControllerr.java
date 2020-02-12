package com.camelcert.ex421.controller;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class RestControllerr {

	@Autowired
	private ProducerTemplate producerTemplate;

	@Autowired
	private ConsumerTemplate consumerTemplate;

	@GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = {
			"/welcome/{name}" })
	public String getWelcomeMessage(@PathVariable("name") String name) {
		return "Welcome " + name;
	}

}
