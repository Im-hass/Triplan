package com.triplan.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 
public class SwaggerConfiguration {
//	Swagger-UI 3.x 확인
//	http://localhost:9999/swagger-ui/index.html
 
private String version = "V1";
private String title = "TriPlan API " + version;
 
@Bean
public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2)
			.consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
			.apiInfo(apiInfo()).groupName(version).select()
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			.paths(PathSelectors.any())
			.build()
			.useDefaultResponseMessages(false);
}

private Set<String> getConsumeContentTypes() {
    Set<String> consumes = new HashSet<>();
    consumes.add("application/json;charset=UTF-8");
//  consumes.add("application/xml;charset=UTF-8");
    consumes.add("application/x-www-form-urlencoded");
    return consumes;
}

private Set<String> getProduceContentTypes() {
    Set<String> produces = new HashSet<>();
    produces.add("application/json;charset=UTF-8");
    return produces;
}

private ApiInfo apiInfo() {
	return new ApiInfoBuilder().title(title)
			.description("<h3>TriPlan API Reference for Developers</h3>Swagger를 이용한 TriPlan API<br><p><img src=\"/assets/img/TriPlan_logo.png\" width=\"150\"></p>") 
//			.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
//			.license("SSAFY License")
//			.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
			.version("1.0").build();
}





}
