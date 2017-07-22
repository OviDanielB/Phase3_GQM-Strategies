package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "name", "value", "valueUrl", "scope", "dueDate", "type"})
public class Variable implements ActivitiEntity {
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("value")
	private Object value;
	@JsonProperty("valueUrl")
	private String valueUrl;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("dueDate")
	private String dueDate;
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("value")
	public Object getValue() {
		return value;
	}
	@JsonProperty("value")
	public void setValue(Object value) {
		this.value = value;
	}
	
	@JsonProperty("valueUrl")
	public String getValueUrl() {
		return valueUrl;
	}
	@JsonProperty("valueUrl")
	public void setValueUrl(String valueUrl) {
		this.valueUrl = valueUrl;
	}
	
	@JsonProperty("scope")
	public String getScope() {
		return scope;
	}
	@JsonProperty("scope")
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@JsonProperty("dueDate")
	public String getDueDate() {
		return dueDate;
	}
	@JsonProperty("dueDate")
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
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
