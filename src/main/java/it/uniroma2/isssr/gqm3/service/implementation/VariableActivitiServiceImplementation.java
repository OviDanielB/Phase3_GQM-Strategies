package it.uniroma2.isssr.gqm3.service.implementation;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.service.VariableActivitiService;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.VariableActiviti;
import it.uniroma2.isssr.gqm3.model.rest.DTO;
import it.uniroma2.isssr.gqm3.model.rest.DTOVariableActiviti;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseVariableActiviti;
import it.uniroma2.isssr.gqm3.repository.VariableActivitiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: VariableActivitiServiceImplementation</p>
 * <p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>Class description:
 * <p>
 * Qui si offre l'implementazione dei metodi dell'interfaccia
 * VariableActivitiService.
 *
 * @author Fabio Alberto Coira
 * @version 1.0
 */

@Service("VariableActivitiService")
public class VariableActivitiServiceImplementation implements VariableActivitiService {
    @Autowired
    VariableActivitiRepository variableActivitiRepository;
    @Autowired
    HostSettings hostSettings;

    /**
     * La get di una Variable Activiti
     *
     * @throws EntityNotFoundException
     * @throws AnomalySystemException
     */
    @Override
    public ResponseEntity<?> getVariableActiviti(String taskId) throws EntityNotFoundException, AnomalySystemException {
        // TODO Auto-generated method stub

        List<VariableActiviti> variableActivitiList =
                variableActivitiRepository.findByTaskId(taskId);

        if (variableActivitiList.isEmpty()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Non è stata trovata nessuna "
                            + "VariableActivity associata al taskId di runtime " + taskId);

        }

        if (variableActivitiList.size() > 1) {
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Sono state trovate più VariableActiviti, non dovrebbe essere possibile! "
                            + "Serio errore interno del sistema");
        }

        VariableActiviti variableActiviti = variableActivitiList.get(0);

        if (variableActiviti == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Non è stata trovata nessuna "
                            + "VariableActivity associata al taskId di runtime " + taskId);

        }

        DTOResponseVariableActiviti dtoResponseVariableActiviti =
                new DTOResponseVariableActiviti();

        dtoResponseVariableActiviti.setId(variableActiviti.getId());
        dtoResponseVariableActiviti.setTaskId(variableActiviti.getTaskId());
        dtoResponseVariableActiviti.setTaskDefinitionKey(
                variableActiviti.getTaskDefinitionKey());

        dtoResponseVariableActiviti.setProperties(
                variableActiviti.getProperties());

        ResponseEntity<DTOResponseVariableActiviti> responseEntity =
                new ResponseEntity<DTOResponseVariableActiviti>(
                        dtoResponseVariableActiviti, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * La create di una VariableActiviti
     *
     * @throws BodyEmptyException
     * @throws IdKeyNullException
     * @throws AnomalySystemException
     * @throws ConflictException
     */
    @Override
    public ResponseEntity<?> createVariableActiviti(DTOVariableActiviti dtoVariableActiviti) throws BodyEmptyException, IdKeyNullException, AnomalySystemException, ConflictException {
        // TODO Auto-generated method stub
        DTOResponseVariableActiviti dtoResponseVariableActiviti =
                new DTOResponseVariableActiviti();
        if (dtoVariableActiviti == null) {
            //ritorna HttpStatus.BAD_REQUEST
            throw new BodyEmptyException(HttpStatus.BAD_REQUEST.value(),
                    "Il body della post non è corretto e la deserializzazione"
                            + "ha generato una istanza null");

        }

        if (dtoVariableActiviti.getTaskId() == null) {
            //ritorna HttpStatus.BAD_REQUEST
            throw new IdKeyNullException(HttpStatus.BAD_REQUEST.value(),
                    "Il parametro 'taskId' non può essere null");
        }

        if (dtoVariableActiviti.getTaskDefinitionKey() == null) {
            //ritorna HttpStatus.BAD_REQUEST
            throw new IdKeyNullException(HttpStatus.BAD_REQUEST.value(),
                    "Il parametro 'taskDefinitionKey' non può essere null");
        }

        if (dtoVariableActiviti.getProperties().isEmpty() ||
                dtoVariableActiviti.getProperties() == null) {
            //ritorna HttpStatus.BAD_REQUEST
            throw new IdKeyNullException(HttpStatus.BAD_REQUEST.value(),
                    "Il parametro 'properties' non può essere null!"
                            + " Probabilmente o hai inserito [], null, oppure "
                            + "un qualsiasi altro valore che ha portato ad una deserializzazione"
                            + "settando tale campo a null");
        }

        List<VariableActiviti> variableActivitiList =
                variableActivitiRepository.findByTaskId(dtoVariableActiviti.getTaskId());

        if (variableActivitiList.size() > 1) {
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Sono state trovate più VariableActiviti, non dovrebbe essere possibile! "
                            + "Serio errore interno del sistema");
        }
        if (variableActivitiList.size() == 1) {
            throw new ConflictException(HttpStatus.CONFLICT.value(),
                    "E' già presente una VariableActiviti con quel 'taskId'. Una delle"
                            + " cause potrebbe risiedere nel fatto che il task associato sia ancora run, quindi si ricade in una situazione"
                            + " più che normale. Un altro motivo potrebbe essere che non "
                            + "si è cancellato il documento quando il task associato al taskId "
                            + "è stato completato. In questo secondo caso la situazione è critica. In ogni caso"
                            + " è opportuno indagare");
        }
        /**
         * caso in cui non è presente nessun documento con quel taskId,
         * allora procedo e ne creo uno
         */
        VariableActiviti variableActiviti = new VariableActiviti();

        variableActiviti.setTaskId(dtoVariableActiviti.getTaskId());
        variableActiviti.setTaskDefinitionKey(dtoVariableActiviti.getTaskDefinitionKey());
        variableActiviti.setProperties(dtoVariableActiviti.getProperties());

        variableActivitiRepository.save(variableActiviti);

        dtoResponseVariableActiviti.setId(variableActiviti.getId());
        dtoResponseVariableActiviti.setTaskId(variableActiviti.getTaskId());
        dtoResponseVariableActiviti.setTaskDefinitionKey(
                variableActiviti.getTaskDefinitionKey());
        dtoResponseVariableActiviti.setProperties(
                variableActiviti.getProperties());

        ResponseEntity<DTOResponseVariableActiviti> responseEntity =
                new ResponseEntity<DTOResponseVariableActiviti>(
                        dtoResponseVariableActiviti, HttpStatus.CREATED);
        return responseEntity;

    }

    @Override
    public ResponseEntity<?> updateVariableActiviti(String taskId
    ) {
        // TODO Auto-generated method stub

        return null;
    }

    /**
     * La delete della Variable Activiti, non contempla la presenza di
     * due taskId e ne segnala la situazione fornendo un'eccezione seria
     * di cui dover tenere conto.
     */
    @Override
    public ResponseEntity<?> deleteVariableActiviti(String taskId) throws EntityNotFoundException, AnomalySystemException {
        // TODO Auto-generated method stub
        List<VariableActiviti> variableActivitiList =
                variableActivitiRepository.findByTaskId(taskId);
        if (variableActivitiList.isEmpty()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Non è stata trovata nessuna "
                            + "VariableActivity associata al taskId di runtime " + taskId);
        }
        if (variableActivitiList.size() > 1) {
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Sono state trovate più VariableActiviti, non dovrebbe essere possibile! "
                            + "Serio errore interno del sistema");
        }
        //ne può essere presente solo una!
        VariableActiviti variableActiviti = variableActivitiList.get(0);

        if (variableActiviti == null) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Non è stata trovata nessuna "
                            + "VariableActivity associata al taskId di runtime " + taskId);

        }
        variableActivitiRepository.delete(variableActiviti);
        DTO dto = new DTO();
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        dto, HttpStatus.NO_CONTENT);
        return responseEntity;
    }

    @Override
    public ResponseEntity<List<DTOResponseVariableActiviti>> getAllVariablesActiviti() {
        // TODO Auto-generated method stub
        List<VariableActiviti> variablesActiviti = variableActivitiRepository.findAll();

        List<DTOResponseVariableActiviti> dtoResponseVariableActivitiList =
                new ArrayList<DTOResponseVariableActiviti>();
        for (VariableActiviti variable : variablesActiviti) {
            DTOResponseVariableActiviti dtoResponseVariableActiviti =
                    new DTOResponseVariableActiviti();
            dtoResponseVariableActiviti.setId(variable.getId());
            dtoResponseVariableActiviti.setTaskId(variable.getTaskId());
            dtoResponseVariableActiviti.setTaskDefinitionKey(variable.getTaskDefinitionKey());
            dtoResponseVariableActiviti.setProperties(
                    variable.getProperties());
            dtoResponseVariableActivitiList.add(dtoResponseVariableActiviti);
        }
        ResponseEntity<List<DTOResponseVariableActiviti>> responseEntity =
                new ResponseEntity<List<DTOResponseVariableActiviti>>(
                        dtoResponseVariableActivitiList, HttpStatus.OK);
        return responseEntity;
    }

}
