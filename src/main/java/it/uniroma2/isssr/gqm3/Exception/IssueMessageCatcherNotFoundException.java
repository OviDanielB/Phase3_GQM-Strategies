package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class IssueMessageCatcherNotFoundException extends Exception {

	private static String messagePrefix = "Error with Activiti REST server;"
			+ " No issue message catcher found corresponding"
			+ " to the process instance id ";
	
	private static String buildMessage(String metaWorkflowProcessInstanceId){
		
		return messagePrefix + metaWorkflowProcessInstanceId;
	}
	
	public IssueMessageCatcherNotFoundException() {
	}

	public IssueMessageCatcherNotFoundException(String metaWorkflowProcessInstanceId) {
		super(buildMessage(metaWorkflowProcessInstanceId));
	}

	public IssueMessageCatcherNotFoundException(Throwable cause) {
		super(cause);
	}

	public IssueMessageCatcherNotFoundException(String metaWorkflowProcessInstanceId, Throwable cause) {
		super(buildMessage(metaWorkflowProcessInstanceId), cause);
	}

	public IssueMessageCatcherNotFoundException(String metaWorkflowProcessInstanceId, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(metaWorkflowProcessInstanceId), cause, enableSuppression, writableStackTrace);
	}

}
