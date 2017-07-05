package it.uniroma2.isssr.gqm3.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormVariableProperty;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface Gqm3141Service {
	
	public ResponseEntity<?> suspendTaskAndSaveFormVariablesInDB(String taskId, List<ActivitiFormVariableProperty> activitiFormVariableProperties)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, ActivitiPostException, BodyEmptyException, IdKeyNullException, AnomalySystemException, ConflictException, JsonRequestException;
	
	public ResponseEntity<?> completeTaskWithFormVariablesinDBByTaskId(
            String taskId) throws EntityNotFoundException, AnomalySystemException, ActivitiPostException, JsonRequestConflictException, JsonRequestException;

	public ResponseEntity<?> completeUserTask(String taskId,
                                              List<ActivitiFormVariableProperty> activitiFormVariableProperties) throws EntityNotFoundException, AnomalySystemException, JsonParseException, JsonMappingException, IOException, ActivitiGetException, ActivitiPostException, JsonRequestConflictException, JsonRequestException;
}
