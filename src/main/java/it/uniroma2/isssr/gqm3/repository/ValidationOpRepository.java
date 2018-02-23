package it.uniroma2.isssr.gqm3.repository;

import it.uniroma2.isssr.gqm3.model.validation.ValidationOp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ValidationOpRepository extends MongoRepository<ValidationOp, String> {
	/*TODO: metodi necessari: find by measure...*/
}
