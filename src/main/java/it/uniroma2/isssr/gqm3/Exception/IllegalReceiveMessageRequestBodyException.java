package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class IllegalReceiveMessageRequestBodyException extends Exception {

	private static String messagePrefix = "Incomplete request; please specify at least business workflow instance Id and issue message.";
	
	
	public IllegalReceiveMessageRequestBodyException() {
		super(messagePrefix);
	}

	public IllegalReceiveMessageRequestBodyException(Throwable cause) {
		super(messagePrefix, cause);
	}
}
