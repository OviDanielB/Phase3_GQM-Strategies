package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.TaskVariable;

import javax.annotation.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
	"assignee",
	"unassigned",
    "candidateGroup",
    "includeTaskLocalVariables",
    "taskVariables"
})

public class PostQueryTask {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("assignee")
	private String assignee;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("unassigned")
	private Boolean unassigned;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("candidateUser")
	private String candidateUser;
	@JsonProperty("includeTaskLocalVariables")
	private boolean includeTaskLocalVariables;
	@JsonProperty("name")
	private String name;
	@JsonProperty("processDefinitionId")
	private String processDefinitionId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("taskVariables")
	private List<TaskVariable> taskVariables;
	
	
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Boolean isUnassigned() {
		return unassigned;
	}
	public void setUnassigned(Boolean unassigned) {
		this.unassigned = unassigned;
	}
	public String getCandidateUser() {
		return candidateUser;
	}
	public void setCandidateUser(String candidateUser) {
		this.candidateUser = candidateUser;
	}
	public boolean getIncludeTaskLocalVariables() {
		return includeTaskLocalVariables;
	}
	public void setIncludeTaskLocalVariables(boolean includeTaskLocalVariables) {
		this.includeTaskLocalVariables = includeTaskLocalVariables;
	}
	public List<TaskVariable> getTaskVariables() {
		return taskVariables;
	}
	public void setTaskVariables(List<TaskVariable> taskVariables) {
		this.taskVariables = taskVariables;
	}

	public Boolean getUnassigned() {
		return unassigned;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
}
