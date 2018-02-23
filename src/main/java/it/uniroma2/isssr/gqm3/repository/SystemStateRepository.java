package it.uniroma2.isssr.gqm3.repository;

import it.uniroma2.isssr.gqm3.model.SystemState;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoRepository for SystemState
 *
 */
public interface SystemStateRepository extends MongoRepository<SystemState, String>
{
	
	
}