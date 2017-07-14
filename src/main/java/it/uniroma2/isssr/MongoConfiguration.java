package it.uniroma2.isssr;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Arrays;

/**
 * Mongo configuration
 *
 */
@Configuration
class MongoConfiguration extends AbstractMongoConfiguration {

	@Autowired
	private HostSettings hostSettings;

	protected String user;
	protected String password;
	protected String database;
	protected Integer port;
	protected String host;

	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		user = hostSettings.getMongodbUsername();
		password = hostSettings.getMongodbPassword();
		database = hostSettings.getMongodbDatabase();
		port = hostSettings.getMongodbPort();
		host = hostSettings.getMongodbHost();

		MongoClient mongoClient = null;
		ServerAddress serverAddress = new ServerAddress(host, port);

		if (user != null) {
			// Set credentials
			MongoCredential credential = MongoCredential.createCredential(user, getDatabaseName(),
					password.toCharArray());

			// Mongo Client
			mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
		} else {

			mongoClient = new MongoClient(serverAddress);

		}

		// Mongo DB Factory
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, getDatabaseName());

		return simpleMongoDbFactory;

	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

		mongoTemplate.setWriteConcern(WriteConcern.SAFE);

		return mongoTemplate;
	}

}