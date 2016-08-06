package fezzik.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

/**
 * Spring application context configuration class for everything related to mongo.
 */
@Configuration
@EnableMongoRepositories("fezzik.component.repository")
@PropertySource({ WebAppInitializer.PROPERTY_FILE_SOURCE })
public class MongoDbConfig extends AbstractMongoConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbConfig.class);
	
	/**
	 * Access properties via environment.
	 */
	@Autowired
	private Environment environment;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getDatabaseName() {
		return environment.getRequiredProperty("fezzik.mongo.dbname");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mongo mongo() throws Exception {
		final String MONGO_DB_NAME = environment.getRequiredProperty("fezzik.mongo.dbname");
		final String MONGO_HOST = environment.getRequiredProperty("fezzik.mongo.host");
		final int MONGO_PORT = Integer.parseInt(environment.getRequiredProperty("fezzik.mongo.port"));		
		
		LOGGER.info(
			String.format("Configuring mongo with properties db [%s], host [%s], port [%s]", 
				MONGO_DB_NAME,
				MONGO_HOST,
				MONGO_PORT
			)
		);
		
		ServerAddress serverAddress = new ServerAddress(MONGO_HOST, MONGO_PORT);
		
		//TODO: what options do we want
		MongoClientOptions options = new MongoClientOptions.Builder().build();
		return new MongoClient(serverAddress, options);
		
//      ServerAddress serverAddress = new ServerAddress(environment.getRequiredProperty("fezzik.mongo.host"));
//      List<MongoCredential> credentials = new ArrayList<>();
//      credentials.add(MongoCredential.createScramSha1Credential(
//      		environment.getRequiredProperty("fezzik.mongo.username"),
//      		environment.getRequiredProperty("fezzik.mongo.dbname"),
//      		environment.getRequiredProperty("fezzik.mongo.password").toCharArray()
//      ));
//      MongoClientOptions options = new MongoClientOptions.Builder().build();
//      return new MongoClient(serverAddress, credentials, options);
	}
}
