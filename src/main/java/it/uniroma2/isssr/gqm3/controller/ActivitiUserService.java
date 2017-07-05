package it.uniroma2.isssr.gqm3.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.ActivitiGetException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * <p>Title: ActivitiUserService</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * Interfaccia per la dichiarazione di metodi che verranno invocati nella richiesta 
 * del servizio REST e restituiranno la relativa risposta.
 * 
 * Questa interfaccia espone i metodi relativi alle richieste agli users di Activiti
 * 
 * </p> 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */
public interface ActivitiUserService {
	/**
	 * Metodo per richiedere la lista degli utenti ad Activiti
	 * 
	 * @return ResponseEntity generica (la sua implementazione avrà per body la lista degli Users di Activiti e HttpStatus.OK
	 * 		in caso di successo)
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ActivitiGetException
	 */
	public ResponseEntity<?> getUsers()
			throws JsonParseException, JsonMappingException, IOException, ActivitiGetException;

}
