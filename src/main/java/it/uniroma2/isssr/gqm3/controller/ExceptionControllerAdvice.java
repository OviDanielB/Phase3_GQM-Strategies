package it.uniroma2.isssr.gqm3.controller;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A class that have the aim of manage all exceptions raised from the whole
 * application
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	private HostSettings hostSettings;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		if (hostSettings.isDebug())
			ex.printStackTrace();// DEBUG
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(error.getErrorCode());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}