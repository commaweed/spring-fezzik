package fezzik.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Specific configuration code to run whenever the profile is set to the docker configuration.
 */
@Configuration
@Profile("docker")
public class DockerConfiguration {

}

