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

	private String businessWorkflowProcessInstanceId;
	private String messageType;
	private String messageBody;
	
	
	public WorkflowMessage(IssueMessage issueMessage) throws IllegalReceiveMessageRequestBodyException
	{
		
		if (issueMessage.getBusinessWorkflowProcessInstanceId() == null
				|| issueMessage.getBusinessWorkflowProcessInstanceId().isEmpty()
				|| issueMessage.getIssueMessage() == null || issueMessage.getIssueMessage().isEmpty()) {
			throw new IllegalReceiveMessageRequestBodyException();
		}
		
		this.businessWorkflowProcessInstanceId = issueMessage.getBusinessWorkflowProcessInstanceId();
		this.messageType = MESSAGE_EVENT_SUBSCRIPTION_NAME_ISSUE_MESSAGE;
		this.messageBody = issueMessage.getIssueMessage();
		
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
		
		this.businessWorkflowProcessInstanceId = endingMessage.getBusinessWorkflowProcessInstanceId();		
		this.messageType = MESSAGE_EVENT_SUBSCRIPTION_NAME_ENDING_MESSAGE;
		this.messageBody = null;
	}
	
	
	
	public String getBusinessWorkflowProcessInstanceId()
	{
		return businessWorkflowProcessInstanceId;
	}
	
	
	public String getMessageType()
	{
		return messageType;
	}
	
	
	public String getMessageBody()
	{
		return messageBody;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
