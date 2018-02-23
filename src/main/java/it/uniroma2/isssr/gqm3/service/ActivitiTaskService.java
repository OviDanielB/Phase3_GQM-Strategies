package it.uniroma2.isssr.gqm3.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.ActivitiGetException;
import it.uniroma2.isssr.gqm3.Exception.ActivitiPutException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestConflictException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestException;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.model.rest.DTOActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.model.rest.response.activiti.DTOResponseActivitiTask;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * <p>Title: TaskService</p>
 * <p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>
 * <p>Class description:
 * <p>
 * Interfaccia per la dichiarazione di metodi che verranno invocati nella richiesta
 * del servizio REST e restituiranno la relativa risposta.
 * <p>
 * Questa interfaccia espone dei metodi per interagire con i task di Activiti</p>
 *
 * @author Fabio Alberto Coira
 * @version 1.0
 */
public interface ActivitiTaskService {

    /**
     * Metodo per richiedere la lista di tutti i tasks di Activiti
     *
     * @return ResponseEntity generica (la sua implementazione avrà per body la lista degli UserTasks di Activiti e HttpStatus.OK
     * in caso di successo)
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    public ResponseEntity<?> getTasks()
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

    /**
     * Metodo per richiedere la lista di tutti gli UserTasks assegnati allo user di Activiti
     * con username {username}
     *
     * @param username : nome dello user di cui si vuole l'elenco dei tasks che lo hanno come assignee
     * @return ResponseEntity generica (la sua implementazione avrà per body la lista degli UserTasks di Activiti
     * con assignee = username e HttpStatus.OK in caso di successo)
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    public ResponseEntity<?> getUserTasks(String username)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

    /**
     * Metodo per richiedere le informazioni ad uno User Task con taskId come id di runtime.
     *
     * @param taskId : id di runtime dello User Task
     * @return ResponseEntity che ha per body un oggetto DTOResponseActivitiTask e HttpStatus.OK
     * in caso di successo)
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    public ResponseEntity<DTOResponseActivitiTask> getUserTaskByTaskId(String taskId)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;


    /**
     * Metodo che consente di ottenere la lista in Activiti di tutte le variabili
     * di uno UserTask a partire dall'id di runtime {taskId}
     *
     * @param taskId : id di runtime dello User Task
     * @param scope  : scope delle variabili, può essere "scope":"local" o "scope": "global"
     * @return ResponseEntity generica
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws JSONException
     * @throws ActivitiGetException
     */
    public ResponseEntity<?> getTaskVariablesFromRuntimeTaskId(String taskId,
                                                               String scope) throws JsonParseException, JsonMappingException, IOException, JSONException, ActivitiGetException, JsonRequestException;

    /**
     * Metodo che consente di creare delle variabili e di associarle
     * ad un task di Activiti a partite da una lista di variabili e dall'id di runtime {taskId}
     * Si sottolinea come si sia osservato che activiti non consente di creare variabili
     * sia con scope "local" che "global"
     *
     * @param taskId                : id di runtime dello User Task
     * @param activitiTaskVariables : lista di ActivitiTaskVariable che
     *                              si vuole creare a associarle allo UserTask
     * @return ResponseEntity generica
     */
    public ResponseEntity<?> createTaskVariableFromRuntimeTaskId(String taskId,
                                                                 List<ActivitiTaskVariable> activitiTaskVariables) throws JsonRequestConflictException, JsonRequestException;

    /**
     * @param candidateGroup :
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    public ResponseEntity<DTOResponseActivitiTask> getUserTasksByCandidateGroup(
            String candidateGroup) throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

    /**
     * @param id
     * @param name
     * @param dtoActivitiTaskVariable
     * @return
     * @throws ActivitiPutException
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public ResponseEntity<DTOResponseActivitiTaskVariable> updateActivitiTaskVariable(
            String id, String name,
            DTOActivitiTaskVariable dtoActivitiTaskVariable) throws JsonParseException, JsonMappingException, IOException, ActivitiPutException, JsonRequestException;

}
