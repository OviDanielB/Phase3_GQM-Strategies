package it.uniroma2.isssr.gqm3.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.rest.DTO;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;


/**
 * Questa classe è pensata per intercettare le eccezioni sollevate a runtime per poterle
 * gestire restituendo un Json contenente un messaggio di errore ed un errorCode.
 * <p>
 * Tra le eccezioni gestite, ci sono anche quelle customizzate all'interno dell'applicazione.
 * Si decide per le eccezioni a runtime restituire HttpStatus.INTERNAL_SERVER_ERROR
 * perché sono pensate come un errore dell'applicazione stessa.
 * <p>
 * Si riportano brevemente i significati degli stereotipi con cui è annotata la classe.
 *
 * @author Fabio Alberto Coira
 * @ControllerAdvice tells your spring application that this class will do the exception
 * handling for your application.
 * @RestController will make it a controller and let this class render the response.
 * Use @ExceptionHandler annotation to define the class of Exception it will catch.
 * (A Base class will catch all the Inherited and extended classes)
 * You can set the response status for exception using @ResponseStatus annotation.
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DTO> exceptionHandler(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getClass().toString() + ": " + ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //this will handle situation when there's exception during binding,
    // for example you except number and user passess string (A123.00 for example)
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<DTO> typeMismatchException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getClass().toString() + ": " + ex.getMessage());

        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<DTO> httpClientErrorExceptionHandler(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getClass().toString() + ": " + ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DTO> illegalArgumentException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DTO> missingServletRequestParameterException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<DTO> jsonParseException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<DTO> jsonMappingException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<DTO> iOException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<DTO> dataAccessException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DTO> httpMessageNotReadableException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DTO> httpRequestMethodNotSupportedException(Exception ex) {
        ex.printStackTrace();// DEBUG
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<DTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<DTO> methodArgumentNotValidExceptionn(Exception e) {
        DTO error = new DTO();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }

    /**
     * custom exception
     */
    //in realtà non serve
    @ExceptionHandler(value = ActivitiGetException.class)
    public ResponseEntity<DTO> activitiGetException(ActivitiGetException e) {
        DTO error = new DTO();
        error.setErrorCode(e.getErrorCode());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }

    //in realtà non serve
    @ExceptionHandler(value = ActivitiPostException.class)
    public ResponseEntity<DTO> activitiPostException(ActivitiGetException e) {
        DTO error = new DTO();
        error.setErrorCode(e.getErrorCode());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }

    @ExceptionHandler(value = ActivitiPutException.class)
    public ResponseEntity<DTO> activitiPutException(ActivitiPutException e) {
        DTO error = new DTO();
        error.setErrorCode(e.getErrorCode());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<DTO> entityNotFoundException(EntityNotFoundException e) {
        DTO error = new DTO();
        error.setErrorCode(e.getErrorCode());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }

    @ExceptionHandler(value = BodyEmptyException.class)
    public ResponseEntity<DTO> bodyEmptyException(BodyEmptyException e) {
        DTO error = new DTO();
        error.setErrorCode(e.getErrorCode());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }

    @ExceptionHandler(value = IdKeyNullException.class)
    public ResponseEntity<DTO> idKeyNullException(IdKeyNullException e) {
        DTO error = new DTO();
        error.setErrorCode(e.getErrorCode());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<DTO> conflictException(ConflictException e) {
        DTO error = new DTO();
        error.setErrorCode(e.getErrorCode());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }

    @ExceptionHandler(value = AnomalySystemException.class)
    public ResponseEntity<DTO> anomalySystemException(AnomalySystemException e) {
        DTO error = new DTO();
        error.setErrorCode(e.getErrorCode());
        error.setMessage(e.getMessage());
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        error, HttpStatus.valueOf(error.getErrorCode()));
        return responseEntity;

    }
}
/*
@ControllerAdvice  
@RestController
public class GlobalExceptionHandler {  
	  
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = BaseException.class)  
    public String handleBaseException(BaseException e){  
        return e.getMessage();  
    }  
  
    //custom exception
    @ExceptionHandler(value = ActivitiGetException.class)
    public ResponseEntity<DTO> httpRequestMethodNotSupportedException(ActivitiGetException e) {
    	DTO error = new DTO();
    	error.setStatus(e.getErrorCode());
    	error.setMessage(e.getMessage());
    	ResponseEntity<DTO> responseEntity = 
				new ResponseEntity<DTO>(
						error, HttpStatus.valueOf(error.getStatus()));
		return responseEntity;
    	 
    }
    
    @ExceptionHandler(value = Exception.class)  
    public String handleException(Exception e){return e.getMessage();}  
  
    
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(BaseException e){  
        return e.getMessage();  
    }
    
    //this will handle situation when there's exception during binding, 
    // for example you except number and user passess string (A123.00 for example)
    @ExceptionHandler(TypeMismatchException.class)
    public String typeMismatchException(BaseException e){  
        return e.getMessage();  
    }
   
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String missingServletRequestParameterException(BaseException e) {
    	return e.getMessage();  
    }  
    
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ExceptionHandler(JsonParseException.class)
    public String jsonParseException(BaseException e) {
    	return e.getMessage();  
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ExceptionHandler(JsonMappingException.class)
    public String jsonMappingException(BaseException e) {
    	return e.getMessage();  
    } 
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ExceptionHandler(IOException.class)
    public String iOException(BaseException e) {
    	return e.getMessage();  
    } 
    
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessException(BaseException e) {
        	return e.getMessage();  
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String httpMessageNotReadableException(BaseException e) {
    	return e.getMessage();  
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String httpRequestMethodNotSupportedException(BaseException e) {
    	return e.getMessage();  
    }
}  */