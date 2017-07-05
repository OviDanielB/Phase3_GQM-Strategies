package it.uniroma2.isssr.gqm3.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.model.rest.response.activiti.DTOResponseActivitiProcess;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * <p>Title: ProcessService</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * 
 * <p>Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * Interfaccia per la dichiarazione di metodi che verranno invocati nella richiesta 
 * del servizio REST e restituiranno la relativa risposta.
 * 
 * Questa interfaccia espone dei metodi per interagire con i processi di Activiti</p> 
 * 
 * @author Fabio Alberto Coira, Luca Della Gatta
 * @version 1.0
 *
 */

public interface ActivitiProcessService {

	public HttpEntity<byte[]> getProcessInstanceState(String id) throws IOException;

	public ResponseEntity<DTOResponseActivitiProcess> getProcess(String username, String password)
			throws JsonParseException, JsonMappingException, IOException;
	
}

