package fezzik.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.ImmutableMap;

import fezzik.web.controller.model.FezzikResponse;
import fezzik.web.controller.model.WebAttributeError;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring application context configuration class for everything related to swagger-ui.  We are using
 * swagger as a web application rest endpoint testing page.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);
	
	private static List<ResponseMessage> GLOBAL_RESPONSE_MESSAGES = new ArrayList<>();
	private static List<ResponseMessage> GLOBAL_GET_RESPONSE_MESSAGES = new ArrayList<>();
	static {		
		
		ResponseMessage response200 = new ResponseMessageBuilder()
			.code(HttpStatus.OK.value())
			.message(HttpStatus.OK.getReasonPhrase())
			.build();
		
		// TODO: THIS IS NOT APPEAR TO BE WORKING (http://localhost:8080/browser/index.html#/rest/user/all)
		ResponseMessage response304 = new ResponseMessageBuilder()
			.code(HttpStatus.NOT_MODIFIED.value())
			.message(HttpStatus.NOT_MODIFIED.getReasonPhrase() + "; requires header If-Modified-Since or If-None-Match")
			.headers(ImmutableMap.<String, ModelReference>of("ETag", new ModelRef("")))
			.build();	
		
		ResponseMessage response401 = new ResponseMessageBuilder()
				.code(HttpStatus.UNAUTHORIZED.value())
				.message(HttpStatus.UNAUTHORIZED.getReasonPhrase() + "; should only happen when basic auth fails from client war!")
				.responseModel(new ModelRef(WebAttributeError.class.getSimpleName())) // TODO: figure out why this doesn't work (tried default constructor, setters, etc.)
				.build();
		
		ResponseMessage response500 = new ResponseMessageBuilder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + "; status in response will be false")
				.responseModel(new ModelRef(FezzikResponse.class.getSimpleName())) 
				.build();		
		
		// all request methods get the following
		GLOBAL_RESPONSE_MESSAGES.add(response200);
		GLOBAL_RESPONSE_MESSAGES.add(response401);
		GLOBAL_RESPONSE_MESSAGES.add(response500);
		
		// GET requests get additional ones
		GLOBAL_GET_RESPONSE_MESSAGES.addAll(GLOBAL_RESPONSE_MESSAGES);
		GLOBAL_GET_RESPONSE_MESSAGES.add(response304);	
	}
	
	/**
	 * Returns the builder which is intended to be the primary interface into the swagger-springmvc framework.
	 * @return
	 */
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        	.groupName("user-api")
        	.apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(regex("/rest/user.*"))
            .build()
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, GLOBAL_GET_RESPONSE_MESSAGES)
            .globalResponseMessage(RequestMethod.POST, GLOBAL_RESPONSE_MESSAGES)
            .globalResponseMessage(RequestMethod.PUT, GLOBAL_RESPONSE_MESSAGES)
            .globalResponseMessage(RequestMethod.PATCH, GLOBAL_RESPONSE_MESSAGES)
            .globalResponseMessage(RequestMethod.DELETE, GLOBAL_RESPONSE_MESSAGES);
    }

    /**
     * Returns the metadata about the application.
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Fezzik Restful Interface")
            .description("P2P Application")
            .contact(new Contact(
        		"Leroy Jenkins", 
        		"http://elegantts.com/", 
        		"fezzik@elegantts.com"
            ))
            .version("1.0.0")
        	.build();        
    }
}
