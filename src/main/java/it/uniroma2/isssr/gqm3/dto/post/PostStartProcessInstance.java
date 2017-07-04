
package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "processDefinitionId" })
public class PostStartProcessInstance {

	@JsonProperty("processDefinitionId")
	private String processDefinitionId;

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

}