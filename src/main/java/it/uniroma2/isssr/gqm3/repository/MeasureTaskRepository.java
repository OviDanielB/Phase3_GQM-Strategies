package it.uniroma2.isssr.gqm3.repository;

import it.uniroma2.isssr.gqm3.model.MeasureTask;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * MongoRepository for MeasureTask
 *
 */
public interface MeasureTaskRepository extends MongoRepository<MeasureTask, String> {

	public List<MeasureTask> findByTaskId(String taskId);



}
