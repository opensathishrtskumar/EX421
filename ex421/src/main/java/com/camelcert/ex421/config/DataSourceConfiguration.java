package com.camelcert.ex421.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

	@Bean(name = "dataSource")
	@ConfigurationProperties("spring.datasource")
	public DataSource hikariDataSource() {
		return DataSourceBuilder.create().build();
	}
}