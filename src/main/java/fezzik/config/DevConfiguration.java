package fezzik.config;

import fezzik.domain.User;
import fezzik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Specific configuration code to run whenever the profile is set to the devlopment configuration.
 */
@Configuration
@Profile("dev")
public class DevConfiguration implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

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
    	
        repository.deleteAll();

        // save a couple of customers
        repository.save(new User("jjsmith", "Jane", "Smith", "yo mama"));
        repository.save(new User("jrsmith", "John", "Smith", "yo mama2"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User user : repository.findAll()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("John"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (User user : repository.findByLastName("Smith")) {
            System.out.println(user);
        }    	
    }

}

