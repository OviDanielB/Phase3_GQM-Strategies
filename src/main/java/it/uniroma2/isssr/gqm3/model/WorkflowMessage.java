package it.uniroma2.isssr.gqm3.model;

import it.uniroma2.isssr.gqm3.Exception.IllegalReceiveMessageRequestBodyException;
import it.uniroma2.isssr.gqm3.dto.EndingMessage;
import it.uniroma2.isssr.gqm3.dto.IssueMessage;
import it.uniroma2.isssr.gqm3.dto.IssueMessageResource;

import java.util.List;

public class WorkflowMessage
{
	public static final String MESSAGE_EVENT_SUBSCRIPTION_NAME_ISSUE_MESSAGE = "workflowIssueMessage";

	public static final String MESSAGE_EVENT_SUBSCRIPTION_NAME_ENDING_MESSAGE = "workflowEndingMessage";

	private String taskId;
	private String businessWorkflowProcessInstanceId;
	private String activitiMessageType;
	private String messageType;
	private String messageBody;
	
	
	public WorkflowMessage(IssueMessage issueMessage) throws IllegalReceiveMessageRequestBodyException
	{
		
		if (issueMessage.getTaskId() == null
				|| issueMessage.getTaskId().isEmpty()
				|| issueMessage.getMessageType() == null || issueMessage.getMessageType().isEmpty()) {
			throw new IllegalReceiveMessageRequestBodyException();
		}
		this.businessWorkflowProcessInstanceId = issueMessage.getBusinessWorkflowProcessInstanceId();
		this.taskId = issueMessage.getTaskId();
		this.activitiMessageType = MESSAGE_EVENT_SUBSCRIPTION_NAME_ISSUE_MESSAGE;
		this.messageType = issueMessage.getMessageType();
		
		List<IssueMessageResource> issueMessageResources = issueMessage.getIssueMessageResources();

		for ( IssueMessageResource issueMessageResource : issueMessageResources){
			
			String link = "\n";
			link += issueMessageResource.getName() + "\n";
			link += issueMessageResource.getUrl() + "\n";
			link += issueMessageResource.getDescription() + "\n";
			
			this.messageBody += link;
		}
	}
	
	public WorkflowMessage(EndingMessage endingMessage) throws IllegalReceiveMessageRequestBodyException
	{
		if (endingMessage.getBusinessWorkflowProcessInstanceId() == null
				|| endingMessage.getBusinessWorkflowProcessInstanceId().isEmpty()) {
			throw new IllegalReceiveMessageRequestBodyException();
		}
		
		this.taskId = endingMessage.getBusinessWorkflowProcessInstanceId();
		this.activitiMessageType = MESSAGE_EVENT_SUBSCRIPTION_NAME_ENDING_MESSAGE;
		this.messageBody = null;
	}


	public String getTaskId() {
		return taskId;
	}

	public String getMessageType()
	{
		return messageType;
	}
	
	
	public String getMessageBody()
	{
		return messageBody;
	}


	public String getActivitiMessageType() {
		return activitiMessageType;
	}

	public String getBusinessWorkflowProcessInstanceId() {
		return businessWorkflowProcessInstanceId;
	}

	public void setBusinessWorkflowProcessInstanceId(String businessWorkflowProcessInstanceId) {
		this.businessWorkflowProcessInstanceId = businessWorkflowProcessInstanceId;
	}
}
