package com.lama.dsa.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Optional;

import static springfox.documentation.builders.PathSelectors.regex;
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
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.lama.rsa.controller")).paths(regex("/RSA/GET/.*"))
				.build().apiInfo(metaData()).useDefaultResponseMessages(false) // to avoid default value for unhandled HTTP status code
				.genericModelSubstitutes(Optional.class);
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Ramen Service REST API",
				"Ramen Service REST API for you to enjoy fresh food data",
				"1.0", "TeamLama",
				
				new Contact("Nice-Sophia-Antipolis University",
						"Master 2 IFI - AL",
						"2017-2018"),
				
				"�TeamLama",
				"http://www.amadeus.com/");
		
		return apiInfo;
	}

}
