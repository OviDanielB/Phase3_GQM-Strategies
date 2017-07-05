package it.uniroma2.isssr.gqm3.model.rest;

import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormVariableProperty;

import java.util.List;

/**
 * <p>Title: DTOVariableActiviti</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * ...
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

public class DTOVariableActiviti extends DTO {
	private static final long serialVersionUID = 1L;
	
	private String taskId;
	private String taskDefinitionKey;
	private List<ActivitiFormVariableProperty> properties;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public List<ActivitiFormVariableProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<ActivitiFormVariableProperty> properties) {
		this.properties = properties;
	}
	
	
	
}
