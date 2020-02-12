package com.camelcert.ex421;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:contextroutes/*.xml"})
@EnableAutoConfiguration(exclude  = {HibernateJpaAutoConfiguration.class})
public class Ex421Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex421Application.class, args);
	}

}
