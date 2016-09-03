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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring application context configuration class for everything related to spring security; that
 * is, anything related to how security is handled in this rest application.
 * Alternate annotation types: EnableGlobalMethodSecurity and EnableGlobalAuthentication
 */
@Configuration
@EnableWebSecurity
@Profile("basicauth")
public class BasicAuthSpringSecurity extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthSpringSecurity.class);
	
	/**
	 * Access properties via environment.
	 */
	@Autowired
	private Environment environment;

	/**
	 * Configure service to handle authentication (i.e. an implementation of the UserDetailsService).
	 * @param authManagerBuilder
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		final String USER = environment.getRequiredProperty("fezzik.rest.username");
		final String PWD = environment.getRequiredProperty("fezzik.rest.passwd");
		final String ROLE = environment.getRequiredProperty("fezzik.rest.role");
		
		// the password file contains the encrypted password, but the user provides the real password
		// dave's war would need to send us the non-encrypted password; 
		// if the same technique is used to encrypt, we'll probably have to create a UserDetailsService to handle it
		authManagerBuilder
			.inMemoryAuthentication()
			.passwordEncoder(getPasswordEncoder())
			.withUser(USER)
			.password(PWD)
			.roles(ROLE);
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.info("setting up basic authentication...");
		http.authorizeRequests().anyRequest().hasRole("rest_service");
		http.csrf().disable();
		http.httpBasic();	
	}
	
	/**
	 * Register the password encoder as a spring bean.  Note that all user logins will have passwords that will be
	 * encrypted.
	 * @return An instance of the password encoder we will be using for the basic authentication password from 
	 *         Dave's war and for user documents.
	 */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	// TODO: do we care if the encryption strength is in a property or not (probably not)
        return new BCryptPasswordEncoder(4); // strength = 16 (takes time to match, maybe reduce to 4??)
    }
  
}