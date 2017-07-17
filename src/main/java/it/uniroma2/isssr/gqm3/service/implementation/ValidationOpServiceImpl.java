package it.uniroma2.isssr.gqm3.service.implementation;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.model.FlowElement;
import it.uniroma2.isssr.gqm3.activiti.ActivitiInterationImplementation;
import it.uniroma2.isssr.gqm3.service.ValidationOpService;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.hermes.Bus2fase3;
import it.uniroma2.isssr.gqm3.model.*;
import it.uniroma2.isssr.gqm3.model.rest.DTO;
import it.uniroma2.isssr.gqm3.model.rest.DTOValidationOp;
import it.uniroma2.isssr.gqm3.model.rest.response.*;
import it.uniroma2.isssr.gqm3.model.validation.ValidationOp;
import it.uniroma2.isssr.gqm3.repository.CollectedDataRepository;
import it.uniroma2.isssr.gqm3.repository.ValidationOpRepository;
import it.uniroma2.isssr.gqm3.repository.MeasureTaskRepository;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;
import it.uniroma2.isssr.gqm3.model.validation.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ValidationOpServiceImpl</p>
 * <p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>Class description:
 * Classe che implementa la relativa interfaccia, ed è annotata con @Service per
 * indicare a Spring che è un service bean. La dependency injection di
 * strategyRepository è operata attraverso l'annotazione @Autowired. In
 * particolare questa classe si occupa di gestire tutto ciò che riguarda le
 * interazioni con la classe ValidationOp da parte di altri. E infatti richiama il
 * repository relativo a ValidationOp.
 * <p>
 * In più offre i servizi relativi alla validazione da operare nella fase 4.
 *
 * @author Fabio Alberto Coira, Gabriele Belli
 * @version 1.0
 */

@Service("ValidationOpService")
public class ValidationOpServiceImpl implements ValidationOpService {

    // Define the logger object for this class
    private static final Logger log = LoggerFactory.getLogger(ValidationOpServiceImpl.class);

    @Autowired
    ValidationOpRepository validationOpRepository;

    @Autowired
    MeasureTaskRepository measureTaskRepository;

    @Autowired
    WorkflowDataRepository workflowDataRepository;

    @Autowired
    ActivitiInterationImplementation activitiInterationImplementation;

    @Autowired
    CollectedDataRepository collectedDataRepository;

    @Autowired
    Bus2fase3 bus2Fase3;

    @Autowired
    HostSettings hostSettings;

    /**
     * Metodo che restituisce una ResponseEntity, che contiene nel body un
     * JSON array contentente la lista di tutti i ValidationOp associati
     * ad un MeasureTask
     *
     * @throws EntityNotFoundException
     * @throws AnomalySystemException
     */
    //va testato
    @Override
    public ResponseEntity<?> getValidationOpListByMeasureTaskId(String measureTaskId, Phase phase) throws EntityNotFoundException, AnomalySystemException {
        /**
         * passo 1: trovo il measure task
         */
        List<MeasureTask> measureTaskList = measureTaskRepository.findByTaskId(measureTaskId);
        if (measureTaskList.isEmpty()) {
            //ritorna HttpStatus.NOT_FOUND
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Non è presente alcun Measure Task con "
                            + "'taskId' nel DB");
        }
        if (measureTaskList.size() > 1) {
            //ritorna HttpStatus.NOT_FOUND
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Sono stati trovati più MeasureTask, non dovrebbe essere possibile in"
                            + "quanto in activiti i taskDefinitionKey dei task sono univoci"
                            + " e i MeasureTask non vengono cancellati per mantenerne la storia passata! "
                            + "Si sospetta un serio errore interno del sistema");
        }

        MeasureTask measureTask = measureTaskList.get(0);


        List<ValidationOp> validationOpList = measureTask.getValidationIdList();
        if (validationOpList == null) {
            validationOpList = new ArrayList<ValidationOp>();
            measureTaskList.get(0).setValidationIdList(validationOpList);
            measureTaskRepository.save(measureTaskList.get(0));
        }
        if (phase.equals(Phase.PHASE_4)) {
            //filtro quelle da mostrare rispetto a quelle che hanno un flag
            //settato a true
            List<ValidationOp> validationOpFilterList = new ArrayList<ValidationOp>();

            if (!validationOpList.isEmpty()) {
                for (ValidationOp validationOp : validationOpList) {
                    if (!validationOp.isContromeasureDone()) {
                        validationOpFilterList.add(validationOp);
                    }
                }

            }
            validationOpList = validationOpFilterList;
        }
        List<DTOResponseValidationOp> dtoResponseValidationOpList =
                new ArrayList<DTOResponseValidationOp>();

        for (ValidationOp validationOp : validationOpList) {
            DTOResponseValidationOp dtoResponseValidationOp = new DTOResponseValidationOp();
            dtoResponseValidationOp.setId(validationOp.getId());
            dtoResponseValidationOp.setName(validationOp.getName());
            dtoResponseValidationOp.setType(validationOp.getType());
            dtoResponseValidationOp.setDescription(validationOp.getDescription());
            dtoResponseValidationOp.setCardinality(validationOp.getCardinality());
            dtoResponseValidationOp.setCompType(validationOp.getCompType());
            dtoResponseValidationOp.setMeasureTaskId(validationOp.getMeasureTaskId());
            dtoResponseValidationOp.setRefMeasureTaskId(validationOp.getRefMeasureTaskId());
            dtoResponseValidationOp.setReferenceParams(validationOp.getReferenceParams());
            dtoResponseValidationOp.setCountermeasures(validationOp.getCountermeasures());
            dtoResponseValidationOp.setThisOp(validationOp.getThisOp());
            dtoResponseValidationOp.setRefOp(validationOp.getRefOp());
            dtoResponseValidationOp.setUserDefined(validationOp.getUserDefined());
            dtoResponseValidationOp.setContromeasureDone(validationOp.isContromeasureDone());

            dtoResponseValidationOpList.add(dtoResponseValidationOp);
        }

        //le devo ritornare in modo che siano una lista di oggetti con HttpStatus.Ok

        return new ResponseEntity<List<DTOResponseValidationOp>>(
                dtoResponseValidationOpList, HttpStatus.OK);

    }

    /**
     * Metodo che restituisce una ResponseEntity, che contiene nel body un
     * JSON object del ValidationOp creato nel DB
     * <p>
     * L'ipotesi è che sia presente un MeasureTask associato a quel TaskId. Se
     * così non fosse viene restituito un errore. E' contemplato
     * il caso in cui un RefMeasureTask non sia settato in ValidationOp (measureTaskid
     * è null) Infatti è ammessa la possibilità di aggiornarlo in seguito
     * In ogni caso se il taskId è corretto, automaticamente viene aggiunto
     * il ValidationOp alla lista di MeasureTask
     *
     * @throws BodyEmptyException
     * @throws IdKeyNullException
     * @throws EntityNotFoundException
     * @throws AnomalySystemException
     */
    @Override
    public ResponseEntity<?> createValidationOp(DTOValidationOp dtoValidationOp) throws BodyEmptyException, IdKeyNullException, EntityNotFoundException, AnomalySystemException {

        /**
         *  Leggo il DTO del body e setto ValidationOp
         */
        if (dtoValidationOp == null) {
            //return eccezione requestBody, httpstatus BadRequest
            throw new BodyEmptyException(HttpStatus.BAD_REQUEST.value(),
                    "Il body della post non è corretto e la deserializzazione"
                            + "ha generato una istanza null");
        }
        if (dtoValidationOp.getMeasureTaskId() == null) {
            //return eccezione, dovevi settare TaskId , httpstatus BadRequest
            //ovvero, non puoi creare un ValidationOp se non passi
            //un MeasureTask con un taskId non null (che quindi esiste)
            throw new IdKeyNullException(HttpStatus.FORBIDDEN.value(),
                    "Dovevi settare TaskId," +
                            " non puoi creare un ValidationOp se non passi" +
                            " un MeasureTask con un taskId non null (che quindi esiste)");

        }

        /**
         * passo 1: trovo il measure task
         */
        List<MeasureTask> measureTaskList = measureTaskRepository.findByTaskId(dtoValidationOp.getMeasureTaskId());
        if (measureTaskList.isEmpty()) {
            //return eccezione, measureTask non esiste, httpstatus Conflict
            throw new EntityNotFoundException(HttpStatus.BAD_REQUEST.value(),
                    "Non è presente alcun MeasureTask con "
                            + "questo 'taskId' nel DB");
        }
        if (measureTaskList.size() > 1) {
            //ritorna c'è un problema,
            //non possono esistere due measure task con stesso taskId
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Sono stati trovati più MeasureTask, non dovrebbe essere possibile in"
                            + "quanto in activiti i taskDefinitionKey dei task sono univoci"
                            + " e i MeasureTask non vengono cancellati per mantenerne la storia passata! "
                            + "Si sospetta un serio errore interno del sistema");
        }
        List<MeasureTask> refMeasureTaskList;
        if (dtoValidationOp.getRefMeasureTaskId() != null) {
            refMeasureTaskList =
                    measureTaskRepository.findByTaskId(dtoValidationOp.getRefMeasureTaskId());

            if (refMeasureTaskList.size() > 1) {
                //ritorna c'è un problema,
                //non possono esistere due measure task con stesso taskId
                throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Sono stati trovati più MeasureTask, non dovrebbe essere possibile in"
                                + "quanto in activiti i taskDefinitionKey dei task sono univoci"
                                + " e i MeasureTask non vengono cancellati per mantenerne la storia passata! "
                                + "Si sospetta un serio errore interno del sistema");
            }
        }

        MeasureTask measureTask = measureTaskList.get(0);
        /**
         * passo 2: lo devo poter aggiornare:
         * farò la seguente operazione:
         * -salvo in validationOp il taskId (non è necessario perché me lo passa così
         * già lui nel body)
         * -salvo validationOp nel db
         * -ora che ho generato l'id, faccio l'update di MeasureTask
         */

        ValidationOp validationOp = new ValidationOp();
        validationOp.setName(dtoValidationOp.getName());
        validationOp.setType(dtoValidationOp.getType());
        validationOp.setDescription(dtoValidationOp.getDescription());
        validationOp.setCardinality(dtoValidationOp.getCardinality());
        validationOp.setCompType(dtoValidationOp.getCompType());
        validationOp.setMeasureTaskId(dtoValidationOp.getMeasureTaskId());
        validationOp.setRefMeasureTaskId(dtoValidationOp.getRefMeasureTaskId());
        validationOp.setReferenceParams(dtoValidationOp.getReferenceParams());
        validationOp.setCountermeasures(dtoValidationOp.getCountermeasures());

        validationOp.setThisOp(dtoValidationOp.getThisOp());
        validationOp.setRefOp(dtoValidationOp.getRefOp());
        validationOp.setUserDefined(dtoValidationOp.isUserDefined());
        //potrei fare una analisi per vedere se il campo è false o true, ma potrei
        //anche ignorarlo
        validationOp.setContromeasureDone(false);
        /**
         * Salvo ValidationOp nel DB
         * */
        //qui potrei fare un eventuale controllo per vedere se già è presente
        //un validatioOp uguale per qualche ragione, nel caso restituisco Httpstatus.CONFLICT
        validationOpRepository.save(validationOp);

        /*******************************************************************************
         * FASE 2: devo aggiornare MeasureTask
         *
         * Qui curo la relazione tra ValidationOp e MeasureTask
         */

        //qui forse dovrei fare un controllo sul fatto che MeasureTask
        //è stato misteriosamente rimosso
        List<ValidationOp> validationOpList =
                measureTaskList.get(0).getValidationIdList();
        //do per scontato che non è null, perché chi la crea la setta come []
        if (validationOpList == null) {
            validationOpList = new ArrayList<ValidationOp>();
        }
        validationOpList.add(validationOp);
        measureTaskList.get(0).setValidationIdList(validationOpList);
        measureTaskRepository.save(measureTaskList.get(0));

        /**
         * Qui setto il valore della risposta di ritorno
         */
        /*
        DTOResponseValidationOp dtoResponseValidationOp = new DTOResponseValidationOp();
		dtoResponseValidationOp.setId(validationOp.getId());
		dtoResponseValidationOp.setName(validationOp.getName()); 
		dtoResponseValidationOp.setType(validationOp.getType());
		dtoResponseValidationOp.setDescription(validationOp.getDescription());
		dtoResponseValidationOp.setCardinality(validationOp.getCardinality());
		dtoResponseValidationOp.setCompType(validationOp.getCompType());
		dtoResponseValidationOp.setIdMeasureTask(validationOp.getMeasureTask().getTaskId());
		dtoResponseValidationOp.setIdRefMeasureTask(validationOp.getRefMeasureTask().getTaskId());
		dtoResponseValidationOp.setReferenceParams(validationOp.getReferenceParams());
		dtoResponseValidationOp.setCountermeasures(validationOp.getCountermeasures());
		dtoResponseValidationOp.setSelectedCountermeasures(
				validationOp.getSelectedCountermeasures());
		dtoResponseValidationOp.setThisOp(validationOp.getThisOp());
		dtoResponseValidationOp.setRefOp(validationOp.getRefOp());
		dtoResponseValidationOp.setUserDefined(validationOp.getUserDefined());
		
		ResponseEntity<DTOResponseValidationOp> responseEntity = new ResponseEntity<DTOResponseValidationOp>(
				dtoResponseValidationOp,HttpStatus.CREATED);
		return responseEntity;
		*/
        //cosa restituisco come valore di ritorno? Forse è più significativo
        //restituire il MeasureTask aggiornato (non è un DTO.. rivedere questa cosa )
        ResponseEntity<MeasureTask> responseEntity = new ResponseEntity<MeasureTask>(
                measureTask, HttpStatus.OK);
        return responseEntity;
    }

	
	/* Esempio body put (basta mandare il body corrente come se fosse una nuova create,
     * ma l'id resta uguale). Il taskId deve essere uguale, perché il sistema
	 * è fatto che da errore se si cambia taskId ad un documento che ha già quell'id!
	 * 
	 * 
	{
	    "name": "post1",
		"type": "ABNORMAL_DATA",
		"description": "a description",
	    "cardinality": "COMPARE_MANY_TO_MANY",
	    "compType": "GREATER_THAN",
	    "measureTaskId":"userTaskA",
		"refMeasureTask": null,
		"referenceParams": null,
		"countermeasures": null,
		"selectedCountermeasures": null,
		"thisOp": "SUM",
	    "refOp": "AVERAGE",
		"userDefined": false
	}
	 */

    /**
     * Metodo che restituisce una ResponseEntity, che contiene nel body un
     * JSON object del ValidationOp aggiornato nel DB
     *
     * @throws BodyEmptyException
     * @throws EntityNotFoundException
     * @throws IdKeyNullException
     * @throws AnomalySystemException
     */

    @Override
    public ResponseEntity<?> updateValidationOp(String id,
                                                DTOValidationOp dtoValidationOp) throws BodyEmptyException, EntityNotFoundException, IdKeyNullException, AnomalySystemException {
        ValidationOp validationOp = validationOpRepository.findOne(id);

        if (dtoValidationOp == null) {
            /*gestire l'eccezione trovando una soluzione definitiva
            return new ResponseEntity<User>(HttpStatus.NOT_CONTENT);*/
            throw new BodyEmptyException(HttpStatus.BAD_REQUEST.value(),
                    "Il body della post non è corretto e la deserializzazione"
                            + "ha generato una istanza null");
        }
        if (dtoValidationOp.getMeasureTaskId() == null) {
            //return eccezione, dovevi settare TaskId , httpstatus BadRequest
            //ovvero, non puoi creare un ValidationOp se non passi
            //un MeasureTask con un taskId non null (che quindi esiste)
            throw new IdKeyNullException(HttpStatus.FORBIDDEN.value(),
                    "Dovevi settare TaskId," +
                            " non puoi fare l'update di un ValidationOp se non passi" +
                            " un MeasureTaskId con un valore diverso (che quindi esiste)");

        }
        if (validationOp == null) {
            /*gestire l'eccezione trovando una soluzione definitiva
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);*/

            throw new EntityNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Non è presente nessun documento con questo 'id'"
                            + "' nel DB");
        }

        List<MeasureTask> measureTaskList = measureTaskRepository.findByTaskId(dtoValidationOp.getMeasureTaskId());
        if (measureTaskList.isEmpty()) {
            //return eccezione, measureTask non esiste, httpstatus Conflict
            throw new EntityNotFoundException(HttpStatus.BAD_REQUEST.value(),
                    "Non è presente alcun Measure Task con "
                            + "'taskId' nel DB");
        }
        if (measureTaskList.size() > 1) {
            //ritorna c'è un problema,
            //non possono esistere due measure task con stesso taskId
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Sono stati trovati più MeasureTask, non dovrebbe essere possibile in"
                            + "quanto in activiti i taskDefinitionKey dei task sono univoci"
                            + " e i MeasureTask non vengono cancellati per mantenerne la storia passata! "
                            + "Si sospetta un serio errore interno del sistema");
        }

        List<MeasureTask> refMeasureTaskList = measureTaskRepository.findByTaskId(dtoValidationOp.getRefMeasureTaskId());

        if (refMeasureTaskList.size() > 1) {
            //ritorna c'è un problema,
            //non possono esistere due measure task con stesso taskId
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Sono stati trovati più MeasureTask, non dovrebbe essere possibile in"
                            + "quanto in activiti i taskDefinitionKey dei task sono univoci"
                            + " e i MeasureTask non vengono cancellati per mantenerne la storia passata! "
                            + "Si sospetta un serio errore interno del sistema");
        }

        if (!dtoValidationOp.getMeasureTaskId().equals(validationOp.getMeasureTaskId())) {
	            /*gestire l'eccezione trovando una soluzione definitiva
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);*/
            throw new IdKeyNullException(HttpStatus.FORBIDDEN.value(),
                    "Non puoi cambiare il TaskId di un ValidationOp." +
                            " Devi inserire nel body lo stesso taskId");

        }
        //verificare se va bene la save di mongorepository per fare l'update
        //perché potrebbe cambiare l'id, dovrei aggiornare Measure Task, e
        // non mi piace questa cosa, oppure devo ricorredere alle update
        // di MongoTemplate
        //https://www.mkyong.com/mongodb/spring-data-mongodb-update-document/

        //measureTask deve essere aggiornato in ogni caso credo, forse l'aggiornamento
        //dei campi nuovi non viene fatto in automatico anche mantenendo il riferimento

        //Verificato => va bene così, non serve neanche la save, l'id resta lo stesso,
        //quindi punta al record aggiornato, quindi una get su MeasureTask resituisce

        validationOp.setName(dtoValidationOp.getName());
        validationOp.setType(dtoValidationOp.getType());
        validationOp.setDescription(dtoValidationOp.getDescription());
        validationOp.setCardinality(dtoValidationOp.getCardinality());
        validationOp.setCompType(dtoValidationOp.getCompType());
        validationOp.setMeasureTaskId(dtoValidationOp.getMeasureTaskId()); //ovvero lo stesso
        validationOp.setRefMeasureTaskId(dtoValidationOp.getRefMeasureTaskId());
        validationOp.setReferenceParams(dtoValidationOp.getReferenceParams());
        validationOp.setCountermeasures(dtoValidationOp.getCountermeasures());

        validationOp.setThisOp(dtoValidationOp.getThisOp());
        validationOp.setRefOp(dtoValidationOp.getRefOp());
        validationOp.setUserDefined(dtoValidationOp.isUserDefined());
        validationOp.setContromeasureDone(dtoValidationOp.isContromeasureDone());

        //l'id resta uguale?
        validationOpRepository.save(validationOp);  //this will overwrite the document in database

        /*******************************************************************************
         * FASE 2: devo aggiornare MeasureTask
         *
         * Qui curo la relazione tra ValidationOp e MeasureTask
         */

        DTOResponseValidationOp dtoResponseValidationOp = new DTOResponseValidationOp();

        dtoResponseValidationOp.setId(validationOp.getId());
        dtoResponseValidationOp.setName(validationOp.getName());
        dtoResponseValidationOp.setType(validationOp.getType());
        dtoResponseValidationOp.setDescription(validationOp.getDescription());
        dtoResponseValidationOp.setCardinality(validationOp.getCardinality());
        dtoResponseValidationOp.setCompType(validationOp.getCompType());
        dtoResponseValidationOp.setMeasureTaskId(validationOp.getMeasureTaskId());
        dtoResponseValidationOp.setRefMeasureTaskId(validationOp.getRefMeasureTaskId());
        dtoResponseValidationOp.setReferenceParams(validationOp.getReferenceParams());
        dtoResponseValidationOp.setCountermeasures(validationOp.getCountermeasures());

        dtoResponseValidationOp.setThisOp(validationOp.getThisOp());
        dtoResponseValidationOp.setRefOp(validationOp.getRefOp());
        dtoResponseValidationOp.setUserDefined(validationOp.getUserDefined());
        dtoResponseValidationOp.setContromeasureDone(validationOp.isContromeasureDone());

        ResponseEntity<DTOResponseValidationOp> responseEntity = new ResponseEntity<DTOResponseValidationOp>(
                dtoResponseValidationOp, HttpStatus.OK);
        return responseEntity;

    }

    /**
     * delete ValidationOp
     *
     * @throws AnomalySystemException
     * @throws EntityNotFoundException
     */
    @Override
    public ResponseEntity<?> deleteValidationOp(String id) throws AnomalySystemException, EntityNotFoundException {

        ValidationOp validationOp = validationOpRepository.findOne(id);
        if (validationOp == null) {
            //solleva eccezione HttpStatus.NOT_FOUND
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Non è presente nessun documento con questo 'id'"
                            + "' nel DB");
        }
        if (validationOp.getMeasureTaskId() == null) {
            //solleva eccezione HttpStatus.INTERNAL_SERVER_ERROR
            //questo perché in teoria i measuretask sono pensati per non essere
            //cancellati, devono poter mantenere la storia..
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Non è stato trovato nessun MeasureTask associato a questa ValidationOp,"
                            + " ma non dovrebbe essere possibile in"
                            + " quanto si è fatta l'ipotesi di rendere il ValidationOp"
                            + " legato ad un MeasureTask, perciò non può esistere una ValidationOp"
                            + " se non è associata ad un MeasureTask. Non ha senso");
        }

        List<MeasureTask> measureTaskList = measureTaskRepository.findByTaskId(
                validationOp.getMeasureTaskId());

        if (measureTaskList.isEmpty()) {
            //solleva eccezione HttpStatus.INTERNAL_SERVER_ERROR
            //questo perché in teoria i measuretask sono pensati per non essere
            //cancellati, devono poter mantenere la storia, e inoltre un
            //validationOp esiste solo se esiste un MeasureTask a cui è associato
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "E' stato trovato un MeasureTaskId in ValidationOp ma "
                            + "non ha una corrispondenza in un MeasureTask salvato nel DB."
                            + " Non dovrebbe essere possibile questa cosa in quanto una ValidationOp"
                            + " una volta assegnata ad un MeasureTask non può essere assegnata ad un altro"
                            + " MeasureTask, tanto meno essere posta a null (esiste solo se esiste un Measure Task a lui associato). Inoltre"
                            + " legato ad un MeasureTask, perciò non può esistere una ValidationOp"
                            + " su Activiti non dovrebbe essere possibile cancellare i corrispondenti UserTask dei MeasureTask,"
                            + " questo perché comunque si deve mantenere traccia della storia passata");
        }
        if (measureTaskList.size() > 1) {
            //solleva eccezione HttpStatus.INTERNAL_SERVER_ERROR
            //questo perché in teoria i measuretask sono pensati per non essere
            //cancellati, devono poter mantenere la storia..
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Sono stati trovati più MeasureTask, non dovrebbe essere possibile in"
                            + "quanto in activiti i taskDefinitionKey dei task sono univoci"
                            + " e i MeasureTask non vengono cancellati per mantenerne la storia passata! "
                            + "Si sospetta un serio errore interno del sistema");
        }


        MeasureTask measureTask = measureTaskList.get(0);

        List<ValidationOp> validationOpList = measureTask.getValidationIdList();
        if (validationOpList.isEmpty()) {
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Nel MeasureTask associato a 'taskId' di ValidationOp non è stata "
                            + "alcuna ValidationOp, ma questa cosa non dovrebbe essere possibile, perché"
                            + " non dovrebbe esistere una ValidationOp associata ad un MeasureTask"
                            + " che esiste ma non è presente al suo interno. Evidentemente"
                            + " non è stata cancellata a dovere. Si sospetta un serio errore interno del sistema. Si consiglia"
                            + " di indagare");
        }
        int validationOpListLength = validationOpList.size();

        //è strano perché dovrei cancellare il base all'id della validationOp
        for (ValidationOp validationOpInMeasureTask : validationOpList) {
            if ((validationOpInMeasureTask.getId()).equals(
                    validationOp.getId())) {
                validationOpList.remove(validationOpInMeasureTask);
                measureTask.setValidationIdList(validationOpList);
                break;
            }

        }
        if (validationOpListLength == validationOpList.size()) {
            throw new AnomalySystemException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Nella lista di ValidationOp del MeasureTask non è stato possibile cancellare"
                            + " la ValidationOp perché non è presente, ma questa cosa non dovrebbe essere possibile, perché"
                            + " non dovrebbe esistere una ValidationOp associata ad un MeasureTask"
                            + " che esiste ma non è presente al suo interno. Evidentemente"
                            + " non è stata cancellata a dovere. Si sospetta un serio errore interno del sistema. Si consiglia"
                            + " di indagare");
        }
        measureTaskRepository.save(measureTask);
        validationOpRepository.delete(validationOp);

        DTO dto = new DTO();
        ResponseEntity<DTO> responseEntity =
                new ResponseEntity<DTO>(
                        dto, HttpStatus.NO_CONTENT);
        return responseEntity;
    }

    /**
     * Per ottenere una validationOp a partire dall'id del DB
     *
     * @throws EntityNotFoundException
     */
    @Override
    public ResponseEntity<?> getValidationOp(String id) throws EntityNotFoundException {
        ValidationOp validationOp = validationOpRepository.findOne(id);
        if (validationOp == null) {
            //ritorno HttpStatus.NOT_FOUND
            //solleva eccezione HttpStatus.NOT_FOUND
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Non è presente alcun Measure Task con questo"
                            + "'id' nel DB");

        }

        DTOResponseValidationOp dtoResponseValidationOp =
                new DTOResponseValidationOp();
        dtoResponseValidationOp.setId(validationOp.getId());
        dtoResponseValidationOp.setName(validationOp.getName());
        dtoResponseValidationOp.setType(validationOp.getType());
        dtoResponseValidationOp.setDescription(validationOp.getDescription());
        dtoResponseValidationOp.setCardinality(validationOp.getCardinality());
        dtoResponseValidationOp.setCompType(validationOp.getCompType());
        dtoResponseValidationOp.setMeasureTaskId(validationOp.getMeasureTaskId());
        dtoResponseValidationOp.setRefMeasureTaskId(validationOp.getRefMeasureTaskId());
        dtoResponseValidationOp.setReferenceParams(validationOp.getReferenceParams());
        dtoResponseValidationOp.setCountermeasures(validationOp.getCountermeasures());

        dtoResponseValidationOp.setThisOp(validationOp.getThisOp());
        dtoResponseValidationOp.setRefOp(validationOp.getRefOp());
        dtoResponseValidationOp.setUserDefined(validationOp.getUserDefined());
        dtoResponseValidationOp.setContromeasureDone(validationOp.isContromeasureDone());

        ResponseEntity<DTOResponseValidationOp> responseEntity =
                new ResponseEntity<DTOResponseValidationOp>(
                        dtoResponseValidationOp, HttpStatus.OK);
        return responseEntity;
    }

    //codice di Gabriele da rivedere
    @Override
    public ResponseEntity<?> getMeasureTask(
            String businessWorkflowProcessDefinitionId) throws IOException, ActivitiGetException, JsonRequestException {
        List<WorkflowData> workflowDataList = workflowDataRepository.findByBusinessWorkflowProcessDefinitionId(businessWorkflowProcessDefinitionId);
        if (workflowDataList.isEmpty()) {
            log.warn("no workflowdata found for businessWorkflowProcessDefinition: " + businessWorkflowProcessDefinitionId);
            //return httpstatus.notfound()
        }
        if (workflowDataList.size() > 1) {
            log.warn("2");
            //return httpstatus.notfound()
        }

        //TODO: indexOutOfBound
        WorkflowData workflowData = workflowDataList.get(0);
        log.warn("3");

        List<MeasureTask> measureTasksList = workflowData.getMeasureTasksList();
        log.warn("4");
        log.warn("measureTask", measureTasksList.get(0).getTaskId());

        //dentro ogni measureTask c'è un taskId ed una metrica
        List<FlowElement> flowElementList = activitiInterationImplementation.getFlowElementsList(
                hostSettings.getActivitiUsername(), hostSettings.getActivitiPassword(), businessWorkflowProcessDefinitionId);

        List<MetricTask> metricTasks = new ArrayList<MetricTask>();
        for (int i = 0; i < measureTasksList.size(); i++) {
            for (int j = 0; j < flowElementList.size(); j++) {
                if (measureTasksList.get(i).getTaskId().equals(flowElementList.get(j).getId())) {
                    metricTasks.add(new MetricTask(
                            measureTasksList.get(i).getMetric().getName(),
                            flowElementList.get(j).getName().toString(),
                            //"",
                            measureTasksList.get(i).getMetric().getDescription(),
                            measureTasksList.get(i).getTaskId()));
                }

            }
        }
        DTOResponseMetricTask dtoResponseMetricTask = new DTOResponseMetricTask();
        dtoResponseMetricTask.setMetricTask(metricTasks);

        ResponseEntity<DTOResponseMetricTask> responseEntity =
                new ResponseEntity<DTOResponseMetricTask>(dtoResponseMetricTask, HttpStatus.OK);
        return responseEntity;
    }

}