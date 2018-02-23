package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class JsonRequestConflictException extends Exception {

	private static String messagePrefix = "Error with Activiti REST server; STATUS CODE returned ";
	
	private static String buildMessage(String message){
		
		return messagePrefix + message;
	}
	
	public JsonRequestConflictException() {
	}

	public JsonRequestConflictException(String message) {
		super(buildMessage(message));
	}

	public JsonRequestConflictException(Throwable cause) {
		super(cause);
	}

	public JsonRequestConflictException(String message, Throwable cause) {
		super(buildMessage(message), cause);
	}

	public JsonRequestConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(message), cause, enableSuppression, writableStackTrace);
	}

}
