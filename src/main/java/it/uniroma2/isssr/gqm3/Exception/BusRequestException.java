package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class BusRequestException extends Exception {

	private static String messagePrefix = "Error with Bus; STATUS CODE returned ";
	
	private static String buildMessage(String message){
		
		return messagePrefix + message;
	}
	
	public BusRequestException() {
	}

	public BusRequestException(String message) {
		super(buildMessage(message));
	}

	public BusRequestException(Throwable cause) {
		super(cause);
	}

	public BusRequestException(String message, Throwable cause) {
		super(buildMessage(message), cause);
	}

	public BusRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(message), cause, enableSuppression, writableStackTrace);
	}

}
