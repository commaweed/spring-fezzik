package fezzik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Represents the main class for the fezzik spring-boot launch.  The fezzik war will be a restful web 
 * service and not a traditional web application.
 * 
 * Notes:
 * <pre>
 * @SpringBootApplication is the equivalent of the following three Spring annotations:
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 * 
 * Can run with maven or as an executable jar file: (it will contain an embedded tomcat - with hot deploy)
 * 1)  mvn clean package spring-boot:run
 * 2)  java -jar target/fezzik-{version}.jar 
 *     - or, to allow it to use components and configurations for a specif 
 *     java -jar target/fezzik-{version}.jar -Dspring.profiles.active={profile_name}
 * </pre>  
 *   
 */
@SpringBootApplication
public class Application {

	/**
	 * Main entry point of the fezzik war.  There can only be one class with a main method.
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
