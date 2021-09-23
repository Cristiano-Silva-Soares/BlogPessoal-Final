package org.generation.blogPessoal.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.generation.blogPessoal.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metadata()).useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, responseMessageForGet())
				.globalResponses(HttpMethod.POST, responseMessageForGet())
				.globalResponses(HttpMethod.PUT, responseMessageForGet())
				.globalResponses(HttpMethod.DELETE, responseMessageForGet());

	}

	public static ApiInfo metadata() {

		return new ApiInfoBuilder().title("API - Blog Pessoal").description("Projeto API Spring - Blog Pessoal")
				.version("1.5.0").license("Apache license version 2.0").licenseUrl("http://localhost:8080/swagger-ui/")
				.contact(contact()).build();
	}

	private static Contact contact() {

		return new Contact("Cristiano da Silva Soares", "https://github.com/Cristiano-Silva-Soares",
				"criztianosoares99@Outlook.com");
	}

	private static List<Response> responseMessageForGet() {

		return new ArrayList<Response>() {

			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("200").description("Sucesso!").build());
				add(new ResponseBuilder().code("201").description("Objeto criado").build());
				add(new ResponseBuilder().code("401").description("Não autorizado!").build());
				add(new ResponseBuilder().code("403").description("Proibido!").build());
				add(new ResponseBuilder().code("404").description("Não encotrado!").build());
				add(new ResponseBuilder().code("500").description("Erro!").build());
			}
		};
	}

}
