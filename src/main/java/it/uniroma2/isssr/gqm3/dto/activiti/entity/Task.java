package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "id", "url", "owner", "assignee", "delegationState", "name", "description", "createTime",
		"dueDate", "priority", "suspended", "taskDefinitionKey", "tenantId", "category", "formKey", "parentTaskId",
		"parentTaskUrl", "executionId", "executionUrl", "processInstanceId", "processInstanceUrl",
		"processDefinitionId", "processDefinitionUrl", "variables" })
public class Task implements ActivitiEntity {

	@JsonProperty("id")
	private String id;
	@JsonProperty("url")
	private String url;
	@JsonProperty("owner")
	private Object owner;
	@JsonProperty("assignee")
	private Object assignee;
	@JsonProperty("delegationState")
	private Object delegationState;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("createTime")
	private String createTime;
	@JsonProperty("dueDate")
	private Object dueDate;
	@JsonProperty("priority")
	private Integer priority;
	@JsonProperty("suspended")
	private Boolean suspended;
	@JsonProperty("taskDefinitionKey")
	private String taskDefinitionKey;
	@JsonProperty("tenantId")
	private String tenantId;
	@JsonProperty("category")
	private Object category;
	@JsonProperty("formKey")
	private Object formKey;
	@JsonProperty("parentTaskId")
	private Object parentTaskId;
	@JsonProperty("parentTaskUrl")
	private Object parentTaskUrl;
	@JsonProperty("executionId")
	private String executionId;
	@JsonProperty("executionUrl")
	private String executionUrl;
	@JsonProperty("processInstanceId")
	private String processInstanceId;
	@JsonProperty("processInstanceUrl")
	private String processInstanceUrl;
	@JsonProperty("processDefinitionId")
	private String processDefinitionId;
	@JsonProperty("processDefinitionUrl")
	private String processDefinitionUrl;
	@JsonProperty("variables")
	private List<Variable> variables = new ArrayList<Variable>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The url
	 */
	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url
	 *            The url
	 */
	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * @return The owner
	 */
	@JsonProperty("owner")
	public Object getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param owner
	 *            The owner
	 */
	@JsonProperty("owner")
	public void setOwner(Object owner) {
		this.owner = owner;
	}

	/**
	 * 
	 * @return The assignee
	 */
	@JsonProperty("assignee")
	public Object getAssignee() {
		return assignee;
	}

	/**
	 * 
	 * @param assignee
	 *            The assignee
	 */
	@JsonProperty("assignee")
	public void setAssignee(Object assignee) {
		this.assignee = assignee;
	}

	/**
	 * 
	 * @return The delegationState
	 */
	@JsonProperty("delegationState")
	public Object getDelegationState() {
		return delegationState;
	}

	/**
	 * 
	 * @param delegationState
	 *            The delegationState
	 */
	@JsonProperty("delegationState")
	public void setDelegationState(Object delegationState) {
		this.delegationState = delegationState;
	}

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The createTime
	 */
	@JsonProperty("createTime")
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime
	 *            The createTime
	 */
	@JsonProperty("createTime")
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return The dueDate
	 */
	@JsonProperty("dueDate")
	public Object getDueDate() {
		return dueDate;
	}

	/**
	 * 
	 * @param dueDate
	 *            The dueDate
	 */
	@JsonProperty("dueDate")
	public void setDueDate(Object dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * 
	 * @return The priority
	 */
	@JsonProperty("priority")
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 
	 * @param priority
	 *            The priority
	 */
	@JsonProperty("priority")
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * 
	 * @return The suspended
	 */
	@JsonProperty("suspended")
	public Boolean getSuspended() {
		return suspended;
	}

	/**
	 * 
	 * @param suspended
	 *            The suspended
	 */
	@JsonProperty("suspended")
	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	/**
	 * 
	 * @return The taskDefinitionKey
	 */
	@JsonProperty("taskDefinitionKey")
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	/**
	 * 
	 * @param taskDefinitionKey
	 *            The taskDefinitionKey
	 */
	@JsonProperty("taskDefinitionKey")
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	/**
	 * 
	 * @return The tenantId
	 */
	@JsonProperty("tenantId")
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * 
	 * @param tenantId
	 *            The tenantId
	 */
	@JsonProperty("tenantId")
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * 
	 * @return The category
	 */
	@JsonProperty("category")
	public Object getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category
	 *            The category
	 */
	@JsonProperty("category")
	public void setCategory(Object category) {
		this.category = category;
	}

	/**
	 * 
	 * @return The formKey
	 */
	@JsonProperty("formKey")
	public Object getFormKey() {
		return formKey;
	}

	/**
	 * 
	 * @param formKey
	 *            The formKey
	 */
	@JsonProperty("formKey")
	public void setFormKey(Object formKey) {
		this.formKey = formKey;
	}

	/**
	 * 
	 * @return The parentTaskId
	 */
	@JsonProperty("parentTaskId")
	public Object getParentTaskId() {
		return parentTaskId;
	}

	/**
	 * 
	 * @param parentTaskId
	 *            The parentTaskId
	 */
	@JsonProperty("parentTaskId")
	public void setParentTaskId(Object parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	/**
	 * 
	 * @return The parentTaskUrl
	 */
	@JsonProperty("parentTaskUrl")
	public Object getParentTaskUrl() {
		return parentTaskUrl;
	}

	/**
	 * 
	 * @param parentTaskUrl
	 *            The parentTaskUrl
	 */
	@JsonProperty("parentTaskUrl")
	public void setParentTaskUrl(Object parentTaskUrl) {
		this.parentTaskUrl = parentTaskUrl;
	}

	/**
	 * 
	 * @return The executionId
	 */
	@JsonProperty("executionId")
	public String getExecutionId() {
		return executionId;
	}

	/**
	 * 
	 * @param executionId
	 *            The executionId
	 */
	@JsonProperty("executionId")
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	/**
	 * 
	 * @return The executionUrl
	 */
	@JsonProperty("executionUrl")
	public String getExecutionUrl() {
		return executionUrl;
	}

	/**
	 * 
	 * @param executionUrl
	 *            The executionUrl
	 */
	@JsonProperty("executionUrl")
	public void setExecutionUrl(String executionUrl) {
		this.executionUrl = executionUrl;
	}

	/**
	 * 
	 * @return The processInstanceId
	 */
	@JsonProperty("processInstanceId")
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * 
	 * @param processInstanceId
	 *            The processInstanceId
	 */
	@JsonProperty("processInstanceId")
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/**
	 * 
	 * @return The processInstanceUrl
	 */
	@JsonProperty("processInstanceUrl")
	public String getProcessInstanceUrl() {
		return processInstanceUrl;
	}

	/**
	 * 
	 * @param processInstanceUrl
	 *            The processInstanceUrl
	 */
	@JsonProperty("processInstanceUrl")
	public void setProcessInstanceUrl(String processInstanceUrl) {
		this.processInstanceUrl = processInstanceUrl;
	}

	/**
	 * 
	 * @return The processDefinitionId
	 */
	@JsonProperty("processDefinitionId")
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	/**
	 * 
	 * @param processDefinitionId
	 *            The processDefinitionId
	 */
	@JsonProperty("processDefinitionId")
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	/**
	 * 
	 * @return The processDefinitionUrl
	 */
	@JsonProperty("processDefinitionUrl")
	public String getProcessDefinitionUrl() {
		return processDefinitionUrl;
	}

	/**
	 * 
	 * @param processDefinitionUrl
	 *            The processDefinitionUrl
	 */
	@JsonProperty("processDefinitionUrl")
	public void setProcessDefinitionUrl(String processDefinitionUrl) {
		this.processDefinitionUrl = processDefinitionUrl;
	}

	/**
	 * 
	 * @return The variables
	 */
	@JsonProperty("variables")
	public List<Variable> getVariables() {
		return variables;
	}

	/**
	 * 
	 * @param variables
	 *            The variables
	 */
	@JsonProperty("variables")
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}