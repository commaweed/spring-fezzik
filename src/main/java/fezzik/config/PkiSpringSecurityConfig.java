package fezzik.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import fezzik.service.security.FezzikUserDetailsService;

/**
 * Spring application context configuration class for everything related to spring security; that
 * is, anything related to how security is handled in this rest application.
 * Alternate annotation types: EnableGlobalMethodSecurity and EnableGlobalAuthentication
 */
@Configuration
@EnableWebSecurity
@Profile("pki")
public class PkiSpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PkiSpringSecurityConfig.class);
	
	/**
	 * Access properties via environment.
	 */
	@SuppressWarnings("unused")
	@Autowired
	private Environment environment;

	/**
	 * Configure service to handle authentication (i.e. an implementation of the UserDetailsService).
	 * @param authManagerBuilder
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
	}
	
	/**
	 * Overriding the adapter method to not allow security to apply to certain pages
	 * i.e. same as <http pattern="/blah/**" security="none" />
	 * Note:  Since this is only a rest application, we may not need to ignore anything.
	 * @param security The web security reference.
	 */
	@Override
	public void configure(WebSecurity security) throws Exception {
//		// add resources to be ignored entirely by Spring security
//		security.ignoring().antMatchers(
//			"/resources/**"
//		);
	}	
	
	private static final String NAME_UUID_REGEX = "CN=(.*?)(?:,|$)";
	@SuppressWarnings("unused")
	private static final String DN_UUID_REGEX = "DN=(.*?)$";

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.info("setting up PKI authentication...");
		
        http.authorizeRequests().anyRequest().authenticated()
        .and()
        .x509()
          .subjectPrincipalRegex(NAME_UUID_REGEX)	
          .userDetailsService(getUserDetailsService());
	}
    
    @Bean
    public UserDetailsService getUserDetailsService() {
    	return new FezzikUserDetailsService();
    }

}