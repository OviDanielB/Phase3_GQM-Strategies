package it.uniroma2.isssr.gqm3.model.rest.response.activiti;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormProperty;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.List;

public class DTOResponseActivitiTaskForm extends DTO {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private String formKey;
	@JsonInclude(Include.NON_NULL)
    private String deploymentId;
	@JsonInclude(Include.NON_NULL)
    private String processDefinitionId;
	@JsonInclude(Include.NON_NULL)
    private String processDefinitionUrl;
	@JsonInclude(Include.NON_NULL)
    private String taskId; //è proprio l'id del Runtime task
	@JsonInclude(Include.NON_NULL)
    private String taskUrl;
    
	@JsonInclude(Include.NON_NULL)
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
