package it.uniroma2.isssr.gqm3.Exception;
/**
 * Eccezione estremamente importante, che gestisce il caso in cui siano presenti
 * anomalie di sistema non previste, che contraddicono il buon senso.
 * Un uso particolare viene fatto per segnalare la presenza nel sistema di due
 * entity con lo stesso taskId. Il taskId, quello di runtime è univoco,
 * e viene riassegnato da Activiti una volta in che il task non è più a runtime.
 * In quel caso si ipotizza che quando scade il vecchio taskId, allora ciò che per
 * natura è legato a quel taskId e deve poter essere rimosso perché
 * di natura transitoria sia cancellato ad esempio dal DB (è il caso di VariableActiviti
 * che è uno storage temporaneo)
 * 
 * @author falberto
 *
 */
public class AnomalySystemException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String message;
	
	public AnomalySystemException(int errorCode, String message) {
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
