
package it.uniroma2.isssr.gqm3.dto;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "businessWorkflowProcessInstanceId", "issueMessage", "issueMessageResources" })
public class IssueMessage {

	@JsonProperty("taskId")
	private String taskId;
	@JsonProperty("businessWorkflowProcessInstanceId")
	private String businessWorkflowProcessInstanceId;
	@JsonProperty("messageType")
	private String messageType;
	@JsonProperty("messageContent")
	private String messageContent;
	@JsonProperty("issueMessageResources")
	private List<IssueMessageResource> issueMessageResources = new ArrayList<IssueMessageResource>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getBusinessWorkflowProcessInstanceId() {
		return businessWorkflowProcessInstanceId;
	}

	public void setBusinessWorkflowProcessInstanceId(String businessWorkflowProcessInstanceId) {
		this.businessWorkflowProcessInstanceId = businessWorkflowProcessInstanceId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
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
	public List<IssueMessageResource> getIssueMessageResources() {
		return issueMessageResources;
	}

	/**
	 * 
	 * @param issueMessageResources
	 *            The issueMessageResources
	 */
	@JsonProperty("issueMessageResources")
	public void setIssueMessageResources(List<IssueMessageResource> issueMessageResources) {
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
