package it.uniroma2.isssr.gqm3.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.rest.DTOValidationOp;
import it.uniroma2.isssr.gqm3.model.validation.Phase;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


public interface ValidationOpService {

	public ResponseEntity<?> getValidationOpListByMeasureTaskId(String measureTaskId, Phase phase) throws EntityNotFoundException, AnomalySystemException;

	public ResponseEntity<?> createValidationOp(DTOValidationOp dtoValidationOp) throws BodyEmptyException, IdKeyNullException, EntityNotFoundException, AnomalySystemException;
	
	public ResponseEntity<?> updateValidationOp(String id,
                                                DTOValidationOp dtoValidationOp) throws BodyEmptyException, EntityNotFoundException, IdKeyNullException, AnomalySystemException;

	public ResponseEntity<?> deleteValidationOp(String id) throws AnomalySystemException, EntityNotFoundException;

	
	public ResponseEntity<?> getValidationOp(String id) throws EntityNotFoundException;

	public ResponseEntity<?> getMeasureTask(String businessWorkflowProcessDefinitionId)
			throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

}
