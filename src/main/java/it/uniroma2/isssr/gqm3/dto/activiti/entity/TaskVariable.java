package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "name", "value", "operation", "type"})
public class TaskVariable implements ActivitiEntity {

	@JsonProperty("name")
	private String name;
	@JsonProperty("value")
	private String value;
	@JsonProperty("operation")
	private String operation;
	@JsonProperty("type")
	private String type;
	
	public TaskVariable(){}
	
	public TaskVariable(String name, String value, String operation, String type) {
		super();
		this.name = name;
		this.value = value;
		this.operation = operation;
		this.type = type;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("value")
	public String getValue() {
		return value;
	}
	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}
	
	@JsonProperty("operation")
	public String getOperationl() {
		return operation;
	}
	@JsonProperty("operation")
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@JsonProperty("type")
	public String getType() {
		return type;
	}
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
}
