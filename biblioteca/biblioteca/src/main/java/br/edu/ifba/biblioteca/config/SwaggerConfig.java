package br.edu.ifba.biblioteca.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API de Vendas de Selos")
						.version("1.0")
						.description("Documentação da API de Vendas de Selos"));
	}
}
