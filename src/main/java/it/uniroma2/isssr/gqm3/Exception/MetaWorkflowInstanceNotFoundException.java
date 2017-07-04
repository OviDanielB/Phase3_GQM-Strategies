package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class MetaWorkflowInstanceNotFoundException extends Exception {

	private static String messagePrefix = "Error with Activiti REST server; "
			+ "No metaworkflow process instance found corresponding "
			+ "to the workflow process instance id ";
	
	private static String buildMessage(String workflowProcessInstanceId){
		
		return messagePrefix + workflowProcessInstanceId;
	}
	
	public MetaWorkflowInstanceNotFoundException() {
	}

	public MetaWorkflowInstanceNotFoundException(String workflowProcessInstanceId) {
		super(buildMessage(workflowProcessInstanceId));
	}

	public MetaWorkflowInstanceNotFoundException(Throwable cause) {
		super(cause);
	}

	public MetaWorkflowInstanceNotFoundException(String workflowProcessInstanceId, Throwable cause) {
		super(buildMessage(workflowProcessInstanceId), cause);
	}

	public MetaWorkflowInstanceNotFoundException(String workflowProcessInstanceId, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(workflowProcessInstanceId), cause, enableSuppression, writableStackTrace);
	}

}
