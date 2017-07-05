package it.uniroma2.isssr.gqm3.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.ActivitiGetException;
import it.uniroma2.isssr.gqm3.Exception.ActivitiPostException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestConflictException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestException;
import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormVariableProperty;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ActivitiFormService {
	
	/**
	 * Metodo che restituisce tutte le properties di un task a partire dal suo TaskId
	 * @param taskId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws JSONException
	 * @throws ActivitiGetException
	 */
	public ResponseEntity<?> getActiviFormTaskById(String taskId)
            throws JsonParseException, JsonMappingException, IOException, JSONException, ActivitiGetException, JsonRequestException;

	public ResponseEntity<?> submitFormDataAndCompleteTask(String taskId,
                                                           List<ActivitiFormVariableProperty> activitiFormVariableProperties) throws ActivitiPostException, JsonRequestConflictException, JsonRequestException;

}
