package it.uniroma2.isssr.gqm3.Exception;

/**
 * Eccezione che gestisce il caso in cui non è stata trovata
 * l'entity definita nel package Model, salvata nel DB 
 * 
 * @author Fabio Alberto Coira
 *
 */
public class EntityNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String message;
	
	public EntityNotFoundException(int errorCode, String message) {
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

