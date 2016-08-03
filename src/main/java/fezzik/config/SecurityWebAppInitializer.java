package fezzik.config;

import org.springframework.security.web.context.*;

/**
 * Registers the security configuration class with web.xml.  That is, it will:
 * Automatically register the springSecurityFilterChain Filter for every URL in the application
 * Add a ContextLoaderListener that loads the SpringSecurityConfig. (this one doesn't happen without constructor) - 
 * 		but we already have a WebApplicationInitializer (i.e. WebAppInitializer), so register it there.
 * 
 * The Spring security servlet filter intercepts requests for securing the application, 
 * so we need to load it upon application’s start up.
 */
public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {
	// NOT NEEDED with WebAppConfig
	// public SecurityWebAppInitializer() { super(SpringSecurityConfig.class); }
}