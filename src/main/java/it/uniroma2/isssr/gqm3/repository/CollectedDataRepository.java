package it.uniroma2.isssr.gqm3.repository;


import it.uniroma2.isssr.gqm3.model.CollectedData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoRepository for CollectedData
 *
 */
public interface CollectedDataRepository extends MongoRepository<CollectedData, String>{

}
