package com.questionpro.grocery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Grocery API",version = "1.0",description = "Grocery Management API"))
public class GroceryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryApplication.class, args);
	}

}
