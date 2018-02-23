package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class ProcessDefinitionNotFoundException extends Exception {

	private static String messagePrefix = "No process definition found with deploymentId ";

	private static String buildMessage(String deploymentId) {

		return messagePrefix + deploymentId;
	}

	public ProcessDefinitionNotFoundException(String deploymentId) {
		super(buildMessage(deploymentId));
	}

	public ProcessDefinitionNotFoundException(String deploymentId, Throwable cause) {
		super(buildMessage(deploymentId), cause);
	}

	public ProcessDefinitionNotFoundException(String deploymentId, String modelId, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(buildMessage(deploymentId), cause, enableSuppression, writableStackTrace);
	}

}
