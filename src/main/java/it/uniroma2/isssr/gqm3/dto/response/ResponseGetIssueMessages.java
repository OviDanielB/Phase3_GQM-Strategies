
package it.uniroma2.isssr.gqm3.dto.response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "businessWorkflowInstanceId", "issueMessage", "issueMessageResources" })
public class ResponseGetIssueMessages {

	@JsonProperty("businessWorkflowName")
	private String businessWorkflowName;
	@JsonProperty("businessWorkflowInstanceId")
	private String businessWorkflowInstanceId;
	@JsonProperty("messageType")
	private String messageType;
	@JsonProperty("messageContent")
	private String messageContent;
	@JsonProperty("issueMessageResources")
	private String issueMessageResources;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The businessWorkflowName
	 */
	@JsonProperty("businessWorkflowName")
	public String getBusinessWorkflowName() {
		return businessWorkflowName;
	}

	/**
	 * 
	 * @param businessWorkflowName
	 *            The businessWorkflowName
	 */
	@JsonProperty("businessWorkflowName")
	public void setBusinessWorkflowName(String businessWorkflowName) {
		this.businessWorkflowName = businessWorkflowName;
	}

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

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
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