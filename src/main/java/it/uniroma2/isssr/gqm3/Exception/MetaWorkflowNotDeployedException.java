package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class MetaWorkflowNotDeployedException extends Exception {

	private static String messagePrefix = "Error with Activiti REST server;"
			+ " No metaworkflow process definition found corresponding"
			+ " to the deployment id ";
	
	private static String buildMessage(String metaworkflowDeploymentId){
		
		return messagePrefix + metaworkflowDeploymentId;
	}
	
	public MetaWorkflowNotDeployedException() {
	}

	public MetaWorkflowNotDeployedException(String metaworkflowDeploymentId) {
		super(buildMessage(metaworkflowDeploymentId));
	}

	public MetaWorkflowNotDeployedException(Throwable cause) {
		super(cause);
	}

	public MetaWorkflowNotDeployedException(String metaworkflowDeploymentId, Throwable cause) {
		super(buildMessage(metaworkflowDeploymentId), cause);
	}

	public MetaWorkflowNotDeployedException(String metaworkflowDeploymentId, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(metaworkflowDeploymentId), cause, enableSuppression, writableStackTrace);
	}

}
