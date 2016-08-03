package fezzik.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Servlet 3.0 Engine allows us to replace the web.xml with this file. AbstractAnnotationConfigDispatcherServletInitializer
 * contains boiler plate code that simulates Spring dispatcher servlet creation in the web.xml.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  { //implements WebApplicationInitializer {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

	/**
	 * Register Spring security in the root application context.
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringSecurityConfig.class };
	}

	/**
	 * Register web related configuration files in the "dispatcher" servlet application context.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebAppConfig.class };
	}

	/**
	 * The "dispatcher" servlet mapping URLs.
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}

