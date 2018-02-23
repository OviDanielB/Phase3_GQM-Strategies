package it.uniroma2.isssr.gqm3.model.rest.response;

import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormVariableProperty;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.List;

public class DTOResponseVariableActiviti extends DTO{
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String taskId;
	private String taskDefinitionKey;
	private List<ActivitiFormVariableProperty> properties;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
