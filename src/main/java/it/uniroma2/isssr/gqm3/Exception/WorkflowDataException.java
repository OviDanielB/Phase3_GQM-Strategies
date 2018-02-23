package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class WorkflowDataException extends Exception {

	private static final String MESSAGE = "There was an consistency exception in the data concerning workflowDatas";

	
	public WorkflowDataException() {
		super(MESSAGE);
	}

	public WorkflowDataException(Throwable cause) {
		super(MESSAGE, cause);
	}

	public WorkflowDataException(Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(MESSAGE, cause, enableSuppression, writableStackTrace);
	}

}
