package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class JsonRequestException extends Exception {

	private static String messagePrefix = "Error with Activiti REST server; STATUS CODE returned ";
	
	private static String buildMessage(String message){
		
		return messagePrefix + message;
	}
	
	public JsonRequestException() {
	}

	public JsonRequestException(String message) {
		super(buildMessage(message));
	}

	public JsonRequestException(Throwable cause) {
		super(cause);
	}

	public JsonRequestException(String message, Throwable cause) {
		super(buildMessage(message), cause);
	}

	public JsonRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(message), cause, enableSuppression, writableStackTrace);
	}

}
