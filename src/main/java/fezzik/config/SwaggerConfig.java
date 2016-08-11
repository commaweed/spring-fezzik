package fezzik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
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
	
	/**
	 * Returns the builder which is intended to be the primary interface into the swagger-springmvc framework.
	 * @return
	 */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    /**
     * Returns the metadata about the application.
     * @return
     */
    private ApiInfo apiInfo() {
        @SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo(
            "Fezzik",
            "P2P Application",
            "1.0.0",
            "",
            "Leroy Jenkins",
            "",
            ""
        );
        return apiInfo;
    }
}
