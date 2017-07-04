package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "action", "variables", "assignee"})
public class TaskAction implements ActivitiEntity {
	
	@JsonProperty("action")
	private String action;
	@JsonProperty("variables")
	@JsonIgnore
	private List<Variable> variables;
	@JsonProperty("assignee")
	private String assignee;
	
	@JsonProperty("action")
	public String getAction() {
		return action;
	}
	@JsonProperty("action")
	public void setAction(String action) {
		this.action = action;
	}
	
	@JsonProperty("variables")
	public List<Variable> getVariables() {
		return variables;
	}
	@JsonProperty("variables")
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}
	
	@JsonProperty("assignee")
	public String getAssignee() {
		return assignee;
	}
	@JsonProperty("assignee")
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
}
