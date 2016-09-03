package fezzik.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.AbstractEnvironment;

import fezzik.domain.PkiUser;
import fezzik.domain.User;
import fezzik.service.PkiUserService;
import fezzik.service.BasicAuthUserService;

/**
 * Specific configuration code to run whenever the profile is set to the devlopment configuration.
 */
@Configuration
@Profile("dev")
public class DevConfiguration implements CommandLineRunner {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(DevConfiguration.class);

    @Autowired
    private BasicAuthUserService basicAuthUserService;
    
    @Autowired
    private PkiUserService pkiUserService;
    
	/**
	 * Access properties via environment.
	 */
	@Autowired
	private AbstractEnvironment environment;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(String... args) throws Exception {	
    	if (StringUtils.equals("pki", environment.getProperty("spring.profiles.include"))) {
    		insertFakeDevelopmentPkiRecords();
    	} else {
    		insertFakeDevelopmentBasicAuthRecords();
    	}
    }
    
    /**
     * Inserts some fake records into the development database that can be used for testing purposes using
     * basic authentication technique.
     */
    private void insertFakeDevelopmentBasicAuthRecords() {
    	// TODO: once it is determined what the user collection should look like, create a loop and insert some dev records
    	//       but first query to see if any exist and only insert after (no need to delete)
    	
    	basicAuthUserService.removeAllUsers();
    	
        // save a couple of customers
    	basicAuthUserService.addUser(new User("jjsmith", "Jane", "Smith", "yo mama"));
    	basicAuthUserService.addUser(new User("jrsmith", "John", "Smith", "yo mama2"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User user : basicAuthUserService.getAllUsers()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Get user by ID:");
        System.out.println("--------------------------------");
        System.out.println(basicAuthUserService.getUser("jjsmith"));
        System.out.println(basicAuthUserService.getUser("jrsmith"));
    }
    
    /**
     * Inserts some fake records into the development database that can be used for testing purposes using
     * pki technique.
     */
    private void insertFakeDevelopmentPkiRecords() {
    	// TODO: once it is determined what the user collection should look like, create a loop and insert some dev records
    	//       but first query to see if any exist and only insert after (no need to delete)
    	
    	pkiUserService.removeAllUsers();
    	
        // save a couple of customers
    	pkiUserService.addUser(new PkiUser("jjsmith"));
    	pkiUserService.addUser(new PkiUser("jrsmith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (PkiUser user : pkiUserService.getAllUsers()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Get user by ID:");
        System.out.println("--------------------------------");
        System.out.println(pkiUserService.getUser("jjsmith"));
        System.out.println(pkiUserService.getUser("jrsmith"));
    }

}

