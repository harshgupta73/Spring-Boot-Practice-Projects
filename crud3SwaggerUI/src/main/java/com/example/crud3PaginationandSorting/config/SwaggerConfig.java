package com.example.crud3PaginationandSorting.config;

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
				.title("Student Management API")
				.version("1.0")
				.description("Rest API for student management system")
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
