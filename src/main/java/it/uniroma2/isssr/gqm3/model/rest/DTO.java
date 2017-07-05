package it.uniroma2.isssr.gqm3.model.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * <p>Title: DTO</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * Questa classe rappresenta una DTO generica, che verrà estesa dalle 
 * classi di cui si ha interesse archiviare e recuperare dati.
 * 
 * I suoi attributi sono un messaggio di testo o un errore.
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */
public class DTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected String timestamp;
	
	@JsonInclude(Include.NON_EMPTY)
	protected int errorCode;//status;
	
	@JsonInclude(Include.NON_NULL)
	protected String error;
	
	@JsonInclude(Include.NON_NULL)
	protected String exception;
	
	@JsonInclude(Include.NON_NULL)
	protected String message;
	
	@JsonInclude(Include.NON_NULL)
	protected String path;
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/*
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}*/
	
	
	public String getPath() {
		return path;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Instantiates a new dto.
	 */
	public DTO(){};
	
	/**
	 * Instantiates a new dto.
	 *
	 * @param error the error
	 */
	public DTO(String error){
		this.error=error;
	}
	
	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	
	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	
}
