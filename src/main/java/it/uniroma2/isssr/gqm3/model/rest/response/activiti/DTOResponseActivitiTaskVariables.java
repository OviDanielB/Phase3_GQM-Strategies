package it.uniroma2.isssr.gqm3.model.rest.response.activiti;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

public class DTOResponseActivitiTaskVariables extends DTO{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
    private String name;
	@JsonInclude(Include.NON_NULL)
    private String scope;
	@JsonInclude(Include.NON_NULL)
    private String type;
	@JsonInclude(Include.NON_NULL)
    private Object value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
