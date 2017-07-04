
package it.uniroma2.isssr.gqm3.dto;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "businessWorkflowProcessInstanceId" })
public class EndingMessage {

	@JsonProperty("businessWorkflowProcessInstanceId")
	private String businessWorkflowProcessInstanceId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The businessWorkflowProcessInstanceId
	 */
	@JsonProperty("businessWorkflowProcessInstanceId")
	public String getBusinessWorkflowProcessInstanceId() {
		return businessWorkflowProcessInstanceId;
	}

	/**
	 * 
	 * @param businessWorkflowProcessInstanceId
	 *            The businessWorkflowProcessInstanceId
	 */
	@JsonProperty("businessWorkflowProcessInstanceId")
	public void setBusinessWorkflowProcessInstanceId(String businessWorkflowProcessInstanceId) {
		this.businessWorkflowProcessInstanceId = businessWorkflowProcessInstanceId;
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
