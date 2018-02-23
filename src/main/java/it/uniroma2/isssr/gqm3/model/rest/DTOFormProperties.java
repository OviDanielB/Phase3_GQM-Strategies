package it.uniroma2.isssr.gqm3.model.rest;

import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormProperty;

import java.util.List;

public class DTOFormProperties extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private String taskId;
	private List<ActivitiFormProperty> activitiFormProperties;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<ActivitiFormProperty> getActivitiFormProperties() {
		return activitiFormProperties;
	}
	public void setActivitiFormProperties(List<ActivitiFormProperty> activitiFormProperties) {
		this.activitiFormProperties = activitiFormProperties;
	}
	
	
}
