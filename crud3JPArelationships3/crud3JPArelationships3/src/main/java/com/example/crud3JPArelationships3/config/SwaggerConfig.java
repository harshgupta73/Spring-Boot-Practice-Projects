package com.example.crud3JPArelationships3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		// TODO Auto-generated method stub
		return new OpenAPI().info(new Info()
				.title("Student with Department Management API")
				.version("1.0")
				.description("Rest API for student management with JPA relationships: One to One and ManytoOne")
				.contact(new Contact()
						.name("Harshvardhan Gupta")
						.email("guptaharshvardhan673@gmail.com")
						.url("https://github.com/harshgupta73")
				)
				.license(new License()
						.name("Apache 2.0")
				)
		);
	}
}
