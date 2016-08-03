package fezzik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * Creates the spring application context and use an annotation
 * (i.e. @EnableWebMvc) to define it as the bean file known as the
 * dispatcher-servlet.xml file. You still have to hook it into either a web.xml
 * file of a WebAppInitializer file.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "fezzik.component")
public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * Delegate any resource not handled by spring back to the container.
	 */
    @Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * Add a view resolver for JSP pages.
	 * @return the appropriate view name resolver
	 */
	@Bean
	public ViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * Adds a bean name view resolver.
	 * @return the appropriate view name resolver
	 */
	@Bean
	public ViewResolver getBeanNameViewResolver() {
		return new BeanNameViewResolver();
	}
	
//	/**
//	 * Register views that are tiles templates.
//	 * @return The tiles view resolver
//	 */
//	@Bean
//	public ViewResolver getTilesViewResolver() {
//		return new TilesViewResolver();
//	}
	
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	/**
	 * Represents the location of the tiles configuration files.
	 * @return
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/views/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
	/**
	 * Register url-to-view mappings.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// web.xml error page mappings (view names are all JSP's - 
		registry.addViewController("/401").setViewName("unauthenticated");
		registry.addViewController("/403").setViewName("unauthorized");
		registry.addViewController("/404").setViewName("page_not_found");
		registry.addViewController("/500").setViewName("error_occurred");
		
		// web.xml welcome page mapping
		registry.addViewController("/").setViewName("welcome");
		
		// authentication-related mappings
		registry.addViewController("/login").setViewName("login");
	}

	// possibly add a tiles view resolver
}

