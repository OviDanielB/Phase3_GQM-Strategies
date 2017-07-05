package it.uniroma2.isssr.gqm3.service.implementation;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.activiti.ActivitiInterationImplementation;
import it.uniroma2.isssr.gqm3.service.ActivitiProcessService;
import it.uniroma2.isssr.gqm3.Exception.ActivitiGetException;
import it.uniroma2.isssr.gqm3.model.rest.response.activiti.DTOResponseActivitiProcess;
import it.uniroma2.isssr.gqm3.model.rest.response.activiti.DTOResponseActivitiProcessState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>Title: ProcessServiceImplementation</p>
 * <p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>Class description:
 * <p>
 * Qui si offre l'implementazione dei metodi dell'interfaccia
 * ProcessService. Poiché Activiti Rest e' un attore della
 * nostra applicazione, i servizi Rest offerti dalla
 * nostra applicazione invocano dei metodi messi a disposione
 * dalla classe ActivitiInterationImplementation la cui dipendenza
 * e' iniettatta all'interno del nostro Service.
 *
 * @author Fabio Alberto Coira, Luca Della Gatta, Gabriele Belli
 * @version 1.0
 */

@Service("ActivitiProcessService")
public class ActivitiProcessServiceImplementation implements ActivitiProcessService {

    @Autowired
    ActivitiInterationImplementation actitiviInterationImplementation;

    /**
     * Ritorno una png dello stato di runtime all'interno del processo
     */
    @Override
    public HttpEntity<byte[]> getProcessInstanceState(
            String id) throws IOException {
        // TODO Auto-generated method stub
        DTOResponseActivitiProcessState dtoResponseActivitiProcessState =
                new DTOResponseActivitiProcessState();
        byte[] image = actitiviInterationImplementation.getProcessInstanceState(id);
        dtoResponseActivitiProcessState.setImage(image);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(image.length);

        return new HttpEntity<byte[]>(image, headers);
    }

    /**
     * LUCA devi commentare questo metodo
     */
    @Override
    public ResponseEntity<DTOResponseActivitiProcess> getProcess(String username, String password) throws JsonParseException, JsonMappingException, IOException {
        // TODO Auto-generated method stub
        return actitiviInterationImplementation.getProcess(username, password);
    }
    /**
     *
     * GABRIELE devi commentare questo metodo
     *
     * @param username
     * @param password
     * @param businessWorkflowProcessDefinitionId
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    /*
	public ResponseEntity<DTOResponseActivitiFlowElement> getFlowElements(String username, String password, 
			String businessWorkflowProcessDefinitionId) throws JsonParseException, JsonMappingException, IOException, ActivitiGetException{
		List<FlowElement> listFlowElements = actitiviInterationImplementation.getFlowElementsList(username, password, businessWorkflowProcessDefinitionId);
		DTOResponseActivitiFlowElement dtoResponseListFlowElements = new DTOResponseActivitiFlowElement();
		dtoResponseListFlowElements.setListFlowElement(listFlowElements);
		DTOResponseActivitiFlowElement dtoResponseActivitiFlowElement = new DTOResponseActivitiFlowElement();
		ResponseEntity<DTOResponseActivitiFlowElement> responseEntity = 
				new ResponseEntity<DTOResponseActivitiFlowElement>(
						dtoResponseActivitiFlowElement, HttpStatus.OK);
		return responseEntity;
	}*/
}

