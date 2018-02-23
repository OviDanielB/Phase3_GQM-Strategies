package it.uniroma2.isssr.gqm3.Exception;

/**
 * Il Body della Post è presente ma vuoto, ad esempio un Json {}
 * @author Fabio Alberto Coira
 *
 */

public class BodyEmptyException extends Exception{
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String message;
	
	public BodyEmptyException(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
