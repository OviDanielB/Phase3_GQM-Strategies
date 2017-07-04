package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class ProcessDefinitionImageNotFoundException extends Exception {

	private static String messagePrefix = "Process Definition id= ";

	private static String messageSuffix = " not found";

	private static String buildMessage(String processDefinitionId) {

		return messagePrefix + processDefinitionId + messageSuffix;
	}

	public ProcessDefinitionImageNotFoundException(String processDefinitionId) {
		super(buildMessage(processDefinitionId));
	}

}
