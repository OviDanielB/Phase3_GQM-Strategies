package it.uniroma2.isssr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("it.uniroma2.isssr.gqm3.controller")).paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("GQM Phase 3 project backend",
				"Università degli studi di Roma Tor Vergata", "0.9", "",
				new Contact("Facoltà di Ingegneria", "ing.uniroma2.it", "info@uniroma2.it"), "Apache License", "");

		return apiInfo;
	}
}