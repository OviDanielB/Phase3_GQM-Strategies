package it.uniroma2.isssr.gqm3.service;

import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.rest.DTOVariableActiviti;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseVariableActiviti;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VariableActivitiService {
	
	public ResponseEntity<?> getVariableActiviti(String id) throws EntityNotFoundException, AnomalySystemException;
	public ResponseEntity<?> createVariableActiviti(DTOVariableActiviti dtoVariableActiviti) throws BodyEmptyException, IdKeyNullException, AnomalySystemException, ConflictException;
	public ResponseEntity<?> updateVariableActiviti(String taskId);
	public ResponseEntity<?> deleteVariableActiviti(String taskId) throws EntityNotFoundException, AnomalySystemException;
	public ResponseEntity<List<DTOResponseVariableActiviti>> getAllVariablesActiviti();
	
}
