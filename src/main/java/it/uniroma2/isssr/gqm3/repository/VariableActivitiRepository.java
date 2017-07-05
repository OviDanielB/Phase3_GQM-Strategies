package it.uniroma2.isssr.gqm3.repository;

import it.uniroma2.isssr.gqm3.model.VariableActiviti;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VariableActivitiRepository extends MongoRepository<VariableActiviti, String> {
	 public List<VariableActiviti> findByTaskId(String taskId);
	 public List <VariableActiviti> deleteByTaskId(String taskId);
}
