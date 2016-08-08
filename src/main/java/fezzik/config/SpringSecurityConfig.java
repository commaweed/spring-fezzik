package fezzik.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Java configuration for spring security. Alternate annotation types:
 * EnableGlobalMethodSecurity and EnableGlobalAuthentication
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityConfig.class);

	/**
	 * Configure service to handle authentication (i.e. an implementation of the UserDetailsService).
	 * @param authManagerBuilder
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.inMemoryAuthentication()
			.withUser("inigo").password("montoya").roles("rest_service");
	}
	
	/**
	 * Overriding the adapter method to not allow security to apply to certain pages
	 * i.e. same as <http pattern="/blah/**" security="none" />
	 * @param security The web security reference.
	 */
	@Override
	public void configure(WebSecurity security) throws Exception {
//		// add resources to be ignored entirely by Spring security
//		security.ignoring().antMatchers(
//			"/resources/**"
//		);
	}	

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().hasRole("rest_service");
		http.httpBasic();	
	}	
}