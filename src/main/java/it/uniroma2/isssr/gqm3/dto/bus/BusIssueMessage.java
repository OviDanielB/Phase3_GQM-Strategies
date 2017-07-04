
package it.uniroma2.isssr.gqm3.dto.bus;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "businessWorkflowInstanceId", "issueMessage", "issueMessageResources" })
public class BusIssueMessage {

	@JsonProperty("businessWorkflowInstanceId")
	private String businessWorkflowInstanceId;
	@JsonProperty("issueMessage")
	private String issueMessage;
	@JsonProperty("issueMessageResources")
	private String issueMessageResources;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The businessWorkflowInstanceId
	 */
	@JsonProperty("businessWorkflowInstanceId")
	public String getBusinessWorkflowInstanceId() {
		return businessWorkflowInstanceId;
	}

	/**
	 * 
	 * @param businessWorkflowInstanceId
	 *            The businessWorkflowInstanceId
	 */
	@JsonProperty("businessWorkflowInstanceId")
	public void setBusinessWorkflowInstanceId(String businessWorkflowInstanceId) {
		this.businessWorkflowInstanceId = businessWorkflowInstanceId;
	}

	/**
	 * 
	 * @return The issueMessage
	 */
	@JsonProperty("issueMessage")
	public String getIssueMessage() {
		return issueMessage;
	}

	/**
	 * 
	 * @param issueMessage
	 *            The issueMessage
	 */
	@JsonProperty("issueMessage")
	public void setIssueMessage(String issueMessage) {
		this.issueMessage = issueMessage;
	}

	/**
	 * 
	 * @return The issueMessageResources
	 */
	@JsonProperty("issueMessageResources")
	public String getIssueMessageResources() {
		return issueMessageResources;
	}

	/**
	 * 
	 * @param issueMessageResources
	 *            The issueMessageResources
	 */
	@JsonProperty("issueMessageResources")
	public void setIssueMessageResources(String issueMessageResources) {
		this.issueMessageResources = issueMessageResources;
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
