package fezzik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Represents the main class for the fezzik spring-boot launch.  For development, run:
 * <code>mvn clean package spring-boot:run</code>
 * The fezzik war will be a restful web service and not a traditional web application.  
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	/**
	 * Main entry point of the fezzik war.  There can only be one class with a main method.
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
