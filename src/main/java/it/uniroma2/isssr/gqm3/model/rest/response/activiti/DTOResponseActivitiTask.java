package it.uniroma2.isssr.gqm3.model.rest.response.activiti;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTask;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.List;

/**
 * <p>Title: DTOResponseActivitiTask</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * Oggetto che rappresenta la risposta di una chiamata Rest,
 * ovvero viene restituito nelle ResponseEntity
 * (ad esempio quando si fa una get alla nostra applicazione)
 * 
 * Estende DTO che a sua volta implementa Serializable. 
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */


public class DTOResponseActivitiTask extends DTO {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	private List<ActivitiTask> activitiTask;
	
    
    @JsonInclude(Include.NON_NULL)
	private String id;

	@JsonInclude(Include.NON_NULL)
	private String url;

	@JsonInclude(Include.NON_NULL)
	private String owner;

	@JsonInclude(Include.NON_NULL)
	private String assignee;

	@JsonInclude(Include.NON_NULL)
	private String delegationState;

	@JsonInclude(Include.NON_NULL)
	private String name;

	@JsonInclude(Include.NON_NULL)
	private String description;

	@JsonInclude(Include.NON_NULL)
	private String createTime;

	@JsonInclude(Include.NON_NULL)
	private String dueDate;

	@JsonInclude(Include.NON_NULL)
	private String priority;

	@JsonInclude(Include.NON_NULL)
	private String suspended;

	@JsonInclude(Include.NON_NULL)
	private String taskDefinitionKey;

	@JsonInclude(Include.NON_NULL)
	private String tenantId;

	@JsonInclude(Include.NON_NULL)
	private String category;

	@JsonInclude(Include.NON_NULL)
	private String formKey;

	@JsonInclude(Include.NON_NULL)
	private String parentTaskId;

	@JsonInclude(Include.NON_NULL)
	private String parentTaskUrl;

	@JsonInclude(Include.NON_NULL)
	private String executionId;

	@JsonInclude(Include.NON_NULL)
	private String executionUrl;

	@JsonInclude(Include.NON_NULL)
	private String processInstanceId;

	@JsonInclude(Include.NON_NULL)
    private String processInstanceUrl;

	@JsonInclude(Include.NON_NULL)
    private String processDefinitionId;

	@JsonInclude(Include.NON_NULL)
    private String processDefinitionUrl;
	
	public DTOResponseActivitiTask(String error, String message) {
		this.error = error;
		this.message = message;
	}
	
	public DTOResponseActivitiTask() {

	}

	public List<ActivitiTask> getActivitiTask() {
		return activitiTask;
	}

	public void setActivitiTask(List<ActivitiTask> activitiTask) {
		this.activitiTask = activitiTask;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getDelegationState() {
		return delegationState;
	}

	public void setDelegationState(String delegationState) {
		this.delegationState = delegationState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSuspended() {
		return suspended;
	}

	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getParentTaskUrl() {
		return parentTaskUrl;
	}

	public void setParentTaskUrl(String parentTaskUrl) {
		this.parentTaskUrl = parentTaskUrl;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getExecutionUrl() {
		return executionUrl;
	}

	public void setExecutionUrl(String executionUrl) {
		this.executionUrl = executionUrl;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessInstanceUrl() {
		return processInstanceUrl;
	}

	public void setProcessInstanceUrl(String processInstanceUrl) {
		this.processInstanceUrl = processInstanceUrl;
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


}

