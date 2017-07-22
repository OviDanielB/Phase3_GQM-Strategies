package it.uniroma2.isssr.gqm3.activiti;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestConflictException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestException;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.ProcessDefinition;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.ProcessInstance;
import it.uniroma2.isssr.gqm3.model.FlowElement;
import it.uniroma2.isssr.gqm3.Exception.ActivitiGetException;
import it.uniroma2.isssr.gqm3.Exception.ActivitiPostException;
import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormProperty;
import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormVariableProperty;
import it.uniroma2.isssr.gqm3.model.activiti.process.ActivitiProcessDef;
import it.uniroma2.isssr.gqm3.model.activiti.process.ActivitiProcessDefinition;
import it.uniroma2.isssr.gqm3.model.activiti.process.ActivitiProcessInvolvedPeople;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTask;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.model.activiti.user.ActivitiUser;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * <p>Title: Activiti2Phase3</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * <p>Class description:
 * Interfaccia che espone dei metodi per i servizi richiesti ad 
 * Activiti-Rest, attore della nostra applicazione. Questi sono
 * i metodi per operare le richieste ad Activiti-Rest</p> 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

public interface Activiti2Phase3 {
	/**
	 * Metodo che restituisce la lista degli utenti in Activiti
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ActivitiGetException
	 */
	public List<ActivitiUser> getUsers() throws JsonParseException, JsonMappingException, IOException, ActivitiGetException;

	/**
	 * Metodo che restituisce i tasks salvati in Activiti 
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ActivitiGetException
	 */
	public List<ActivitiTask> getTasks() throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

	List<ProcessDefinition> getProcessesDefinitions()
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

	ActivitiTask getTaskByExecutionId(String executionId)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

    /**
	 * 
	 * Metodo che restituisce i tasks assegnati all'utente di Activiti di nome username
	 * 
	 * @param username
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ActivitiGetException
	 */
	public List<ActivitiTask> getUserTasks(String username)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

	/**
	 * Metodo che restituisce un png dello stato di avanzamento del processo in Activiti
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public byte[] getProcessInstanceState(String id) throws IOException;
	

	public List<ActivitiProcessDef> getProcess(String username, String password)
			throws JsonParseException, JsonMappingException, IOException;

	public ActivitiProcessDefinition getProcessDefinition(String username, String password, String id)
			throws JsonParseException, JsonMappingException, IOException;

	public ActivitiProcessInvolvedPeople getInvolvedPeople(String username, String password, String id)
			throws JsonParseException, JsonMappingException, IOException;

	ProcessDefinition getProcessByProcessDefinitionId(String username, String password, String processDefinitionId)
			throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

	/**
	 * Metodo che restituisce una lista di 
	 * From properties a partire dall'id di runtimae di un task
	 * 
	 * @param taskId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws JSONException
	 * @throws ActivitiGetException
	 */
	public List<ActivitiFormProperty> getFormPropertiesTaskById(String taskId) throws JsonParseException, JsonMappingException, IOException, JSONException, ActivitiGetException, JsonRequestException;

	public List<ActivitiTaskVariable> getTaskVariablesFromRuntimeTaskId(String taskId, String scope) throws JsonParseException, JsonMappingException, IOException, JSONException, ActivitiGetException, JsonRequestException;

	public void createTaskVariables(String taskId, List<ActivitiTaskVariable> activitiTaskVariables) throws JsonRequestConflictException, JsonRequestException;

	/**
	 * Servizio Rest per richiedere le informazioni riguardanti uno user Task a partire 
	 * da un TaskId di runtime 
	 *
	 * @param taskId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ActivitiGetException
	 */
	public ActivitiTask getUserTaskByTaskId(String taskId) throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

	public List<FlowElement> getFlowElementsList(String username, String password, String businessWorkflowProcessDefinitionId)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException;

	public void submitFormDataAndCompleteTask(String taskId,
                                              List<ActivitiFormVariableProperty> activitiFormVariableProperties) throws ActivitiPostException, JsonRequestConflictException, JsonRequestException;


	public ActivitiTask getTaskByProcessDefinitionId(String processDefinitionId, String goToTask) throws ActivitiGetException, IOException, JsonRequestException, JsonRequestConflictException;

    public ProcessInstance getProcessInstanceFromKey(String processDefinitionKey) throws JsonRequestException, IOException;
}

