package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class MetaWorkflowNotStartedException extends Exception {

	private static String messagePrefix = "Error with Activiti REST server; "
			+ "No metaworkflow process instance found corresponding "
			+ "to the process definition id ";
	
	private static String buildMessage(String metaworkflowProcessDefinitionId){
		
		return messagePrefix + metaworkflowProcessDefinitionId;
	}
	
	public MetaWorkflowNotStartedException() {
	}

	public MetaWorkflowNotStartedException(String metaworkflowProcessDefinitionId) {
		super(buildMessage(metaworkflowProcessDefinitionId));
	}

	public MetaWorkflowNotStartedException(Throwable cause) {
		super(cause);
	}

	public MetaWorkflowNotStartedException(String metaworkflowProcessDefinitionId, Throwable cause) {
		super(buildMessage(metaworkflowProcessDefinitionId), cause);
	}

	public MetaWorkflowNotStartedException(String metaworkflowProcessDefinitionId, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(metaworkflowProcessDefinitionId), cause, enableSuppression, writableStackTrace);
	}

}
