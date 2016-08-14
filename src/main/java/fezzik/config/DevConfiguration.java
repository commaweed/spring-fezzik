package fezzik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fezzik.domain.User;
import fezzik.service.UserService;

/**
 * Specific configuration code to run whenever the profile is set to the devlopment configuration.
 */
@Configuration
@Profile("dev")
public class DevConfiguration implements CommandLineRunner {

    @Autowired
    private UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(String... args) throws Exception {
    	insertFakeDevelopmentRecords();
    }
    
    /**
     * Inserts some fake records into the development database that can be used for testing purposes.
     */
    private void insertFakeDevelopmentRecords() {
    	// TODO: once it is determined what the user collection should look like, create a loop and insert some dev records
    	//       but first query to see if any exist and only insert after (no need to delete)
    	
    	userService.removeAllUsers();
    	
        // save a couple of customers
    	userService.addUser(new User("jjsmith", "Jane", "Smith", "yo mama"));
    	userService.addUser(new User("jrsmith", "John", "Smith", "yo mama2"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Get user by ID:");
        System.out.println("--------------------------------");
        System.out.println(userService.getUser("jjsmith"));
        System.out.println(userService.getUser("jrsmith"));
    }

}

