package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class ModelXmlNotFoundException extends Exception {

	private static String messagePrefix = "No workflow model xml returned";
	
	private static String buildMessage(){
		
		return messagePrefix;
	}
	
	public ModelXmlNotFoundException() {
		super(buildMessage());
	}

	public ModelXmlNotFoundException(Throwable cause) {
		super(buildMessage(), cause);
	}

	public ModelXmlNotFoundException(String modelId, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(), cause, enableSuppression, writableStackTrace);
	}


}
