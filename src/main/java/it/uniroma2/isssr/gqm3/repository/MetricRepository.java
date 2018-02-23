package it.uniroma2.isssr.gqm3.repository;

import it.uniroma2.isssr.gqm3.model.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoRepository for Metric
 *
 */
public interface MetricRepository extends MongoRepository<Metric, String> {
	
}