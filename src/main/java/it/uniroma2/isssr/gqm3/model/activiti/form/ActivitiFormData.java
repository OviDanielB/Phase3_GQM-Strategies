package it.uniroma2.isssr.gqm3.model.activiti.form;

import java.util.List;

public class ActivitiFormData {
	
	private String formKey;
    private String deploymentId;
    private String processDefinitionId;
    private String processDefinitionUrl;
    private String taskId; //è proprio l'id del Runtime task
    private String taskUrl;
    
    private List<ActivitiFormProperty> formProperties;

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionUrl() {
		return processDefinitionUrl;
	}

	public void setProcessDefinitionUrl(String processDefinitionUrl) {
		this.processDefinitionUrl = processDefinitionUrl;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}

	public List<ActivitiFormProperty> getFormProperties() {
		return formProperties;
	}

	public void setFormProperties(List<ActivitiFormProperty> formProperties) {
		this.formProperties = formProperties;
	}
    
      
}
