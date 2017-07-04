package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class IllegalSaveWorkflowRequestBodyException extends Exception {

	private static String messagePrefix = "Incomplete request; please specify modelId and name.";
	
	
	public IllegalSaveWorkflowRequestBodyException() {
		super(messagePrefix);
	}

	public IllegalSaveWorkflowRequestBodyException(Throwable cause) {
		super(messagePrefix, cause);
	}
}
