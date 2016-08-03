package fezzik.config;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import fezzik.common.security.model.WebApplicationSecurityTypeEnum;

/**
 * Java configuration for spring security. Alternate annotation types:
 * EnableGlobalMethodSecurity and EnableGlobalAuthentication
 */
@Configuration
@EnableWebSecurity
@PropertySource("classpath:/fezzik.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityConfig.class);
	
	/**
	 * Access properties via environment.
	 */
	@Inject
	private Environment environment;

	/**
	 * Configure service to handle authentication (i.e. an implementation of the UserDetailsService).
	 * @param authManagerBuilder
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		// TODO: change to userDetailsService(userDetailsService) and use a custom DAO to handle the authentication
		// for right now just declare users and roles directly here
		authManagerBuilder.inMemoryAuthentication()
			.withUser("emp").password("e").roles("EMPLOYEE").and()
			.withUser("admin").password("a").roles("EMPLOYEE", "ADMIN");
	
	}
	
	/**
	 * Returns the value of the web application security type according to the value in the property file.
	 * The goal is to use it to make the security that is used "pluggable"; this is due to an uncertainty for
	 * what type of security should be used in the prototype (thus all three will be configured).
	 * @return
	 */
	@Bean 
	public WebApplicationSecurityTypeEnum getSecurityTypeEnum() {
		String propertyName = "web.application.security.type";
		
		WebApplicationSecurityTypeEnum securityType = WebApplicationSecurityTypeEnum.valueOf(
			environment.getRequiredProperty(propertyName)
		);
		
		return securityType;
	}
	
	
	/**
	 * Overriding the adapter method to not allow security to apply to certain pages
	 * i.e. same as <http pattern="/blah/**" security="none" />
	 * @param security The web security reference.
	 */
	@Override
	public void configure(WebSecurity security) throws Exception {
		// add resources to be ignored entirely by Spring security
		security.ignoring().antMatchers(
			"/resources/**"
		);
	}	
	
	/**
	 * Configures the given HttpSecurity object for all of the authentication / authorization requests.
	 * @param http The HttpSecurity Object that will be configured.
	 * @throws Exception If an error occurs.
	 */	
	private void configureHttpForAuthorizeRequests(HttpSecurity http) throws Exception {
		// configure all the authorizeRequests the order of the rules matters
		http.authorizeRequests()
				
			// allow for all the web.xml declared error pages to be public (no authentication)
			.antMatchers( "/404", "/401", "/403", "/500").permitAll() 
		
			// allow the follow pages to be public (no authentication)
			.antMatchers( "/", "/signup", "/info").permitAll() 
			
			// all requests must have the EMPLOYEE role
			.anyRequest().hasRole("EMPLOYEE")
		
			// admin requests must also have the ADMIN role
			.antMatchers("/admin/**").hasRole("ADMIN");	
	}
	
	/**
	 * Configures the given HttpSecurity object for form login.
	 * @param http The HttpSecurity Object that will have form login applied to it.
	 * @throws Exception If an error occurs.
	 */
	private void configureHttpForFormLogin(HttpSecurity http) throws Exception {
//		http.formLogin()
//			.loginPage("/login")
//			.defaultSuccessUrl("/home/time")
//			.failureUrl("/login?error")
//			.permitAll()		
//		.and()
//	        .logout()
//	        .logoutUrl("/")
//	        .permitAll();
		
		http.formLogin();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		WebApplicationSecurityTypeEnum securityTypeEnum = this.getSecurityTypeEnum();
		LOGGER.info("Configuring web application security [" + securityTypeEnum + "]!");		
		
		configureHttpForAuthorizeRequests(http);
			
		switch (securityTypeEnum) {
			case basicAuth:
				http.httpBasic();	
				break;
			case x509: 
				//TODO:
				http.exceptionHandling().accessDeniedPage("/403");
				break;
			case formLogin:
				configureHttpForFormLogin(http);
				break;
			default:
				LOGGER.error(
					"Unable to configure web application security for [" + securityTypeEnum + "] because it hasn't been implemented!"
				);
				break;
		}
			
		// configure all the common things that should come at the end
		http.csrf();
		
		
	}	
}


// MY USELESS NOTES OR CODE (remove after this class is finished)


//@Bean
//public UserDetailsManager userDetailsManager(DataSource dataSource) {
//JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
//userDetailsManager.setDataSource(dataSource);
//userDetailsManager.setEnableAuthorities(true);
//return userDetailsManager;
//}
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//auth.userDetailsService(userDetailsManager(null));
//}


//http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

/*
http://www.baeldung.com/spring-data-mongodb-tutorial
https://www.mkyong.com/spring/spring-propertysources-example/
https://spring.io/guides/gs/accessing-data-mongodb/
http://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
https://www.mkyong.com/mongodb/spring-data-mongodb-hello-world-example/

// java configuration (aka annotation based config)
@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "dataBaseName";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "foo.bar.domain";
    }
}

@Repository
public class PersonRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public long countAllPersons() {
        return mongoTemplate.count(null, Person.class);
    }
}


*/
