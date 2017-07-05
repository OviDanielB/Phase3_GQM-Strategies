package it.uniroma2.isssr.gqm3.Exception;

/**
 * Il campo nel Json non può essere nullo
 * @author Fabio Alberto Coira
 *
 */
public class IdKeyNullException extends Exception{
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String message;
	
	public IdKeyNullException(int errorCode, String message) {
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
