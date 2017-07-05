package it.uniroma2.isssr.gqm3.service;

import it.uniroma2.isssr.gqm3.model.rest.DTOMeasureTask;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseMeasureTask;
import org.springframework.http.ResponseEntity;

public interface MeasureTaskService {

	public ResponseEntity<DTOResponseMeasureTask> createMeasureTask(
            DTOMeasureTask dtoMeasureTask);

}
