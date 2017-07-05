package it.uniroma2.isssr.gqm3.service.implementation;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestConflictException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestException;
import it.uniroma2.isssr.gqm3.activiti.ActivitiInterationImplementation;
import it.uniroma2.isssr.gqm3.service.ActivitiTaskService;
import it.uniroma2.isssr.gqm3.Exception.ActivitiGetException;
import it.uniroma2.isssr.gqm3.Exception.ActivitiPutException;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTask;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.model.rest.DTOActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.model.rest.response.activiti.DTOResponseActivitiTask;
import it.uniroma2.isssr.gqm3.model.rest.response.activiti.DTOResponseActivitiTaskVariables;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ActivitiTaskServiceImplementation</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * Qui si offre l'implementazione dei metodi dell'interfaccia 
 * TaskService. Poiché Activiti Rest è un attore della 
 * nostra applicazione, i servizi Rest offerti dalla 
 * nostra applicazione invocano dei metodi messi a disposione
 * dalla classe ActivitiInterationImplementation la cui dipendenza
 * è iniettatta all'interno del nostro Service.
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

@Service("ActivitiTaskService")
public class ActivitiTaskServiceImplementation implements ActivitiTaskService{
	@Autowired
	ActivitiInterationImplementation actitiviInterationImplementation;
	@Override
	public ResponseEntity<?> getTasks() throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
		// TODO Auto-generated method stub
			List<ActivitiTask> activitiTaskList = actitiviInterationImplementation.getTasks();
			List<DTOResponseActivitiTask> dtoResponseActivitiTaskList=
					new ArrayList<DTOResponseActivitiTask>();
			for(ActivitiTask activitiTask : activitiTaskList){
				DTOResponseActivitiTask dtoResponseActivitiTask  = 
						new DTOResponseActivitiTask ();
				dtoResponseActivitiTask.setId( activitiTask.getId());
				dtoResponseActivitiTask.setUrl( activitiTask.getUrl());
				dtoResponseActivitiTask.setOwner( activitiTask.getOwner());
				dtoResponseActivitiTask.setAssignee( activitiTask.getAssignee());
				dtoResponseActivitiTask.setDelegationState(activitiTask.getDelegationState());
				dtoResponseActivitiTask.setName( activitiTask.getName());
				dtoResponseActivitiTask.setDescription( activitiTask.getDescription());
				dtoResponseActivitiTask.setCreateTime( activitiTask.getCreateTime());
				dtoResponseActivitiTask.setDueDate( activitiTask.getDueDate());
				dtoResponseActivitiTask.setPriority( activitiTask.getPriority());
				dtoResponseActivitiTask.setSuspended( activitiTask.getSuspended());
				dtoResponseActivitiTask.setTaskDefinitionKey( activitiTask.getTaskDefinitionKey());
				dtoResponseActivitiTask.setTenantId( activitiTask.getTenantId());
				dtoResponseActivitiTask.setCategory( activitiTask.getCategory());
				dtoResponseActivitiTask.setFormKey( activitiTask.getFormKey());
				dtoResponseActivitiTask.setParentTaskId( activitiTask.getParentTaskId());
				dtoResponseActivitiTask.setParentTaskUrl( activitiTask.getParentTaskUrl());
				dtoResponseActivitiTask.setExecutionId( activitiTask.getExecutionId());
				dtoResponseActivitiTask.setExecutionUrl( activitiTask.getExecutionUrl());
				dtoResponseActivitiTask.setProcessInstanceId( activitiTask.getProcessInstanceId());
				dtoResponseActivitiTask.setProcessInstanceUrl( activitiTask.getProcessInstanceUrl());
				dtoResponseActivitiTask.setProcessDefinitionId( activitiTask.getProcessDefinitionId());
				dtoResponseActivitiTask.setProcessDefinitionUrl( activitiTask.getProcessDefinitionUrl());
				dtoResponseActivitiTaskList.add(dtoResponseActivitiTask);
			}
					
			ResponseEntity<List<DTOResponseActivitiTask>> responseEntity = new ResponseEntity<List<DTOResponseActivitiTask>>(dtoResponseActivitiTaskList, HttpStatus.OK);
			return responseEntity;
	}
	
	@Override
	public ResponseEntity<?> getUserTasks(String username) throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
		// TODO Auto-generated method stub
			List<ActivitiTask> tasks = actitiviInterationImplementation.getUserTasks(username);
			
			List<DTOResponseActivitiTask> dtoResponseActivitiTaskList=
					new ArrayList<DTOResponseActivitiTask>();
			for(ActivitiTask activitiTask : tasks){
				DTOResponseActivitiTask dtoResponseActivitiTask  = 
						new DTOResponseActivitiTask ();
				dtoResponseActivitiTask.setId( activitiTask.getId());
				dtoResponseActivitiTask.setUrl( activitiTask.getUrl());
				dtoResponseActivitiTask.setOwner( activitiTask.getOwner());
				dtoResponseActivitiTask.setAssignee( activitiTask.getAssignee());
				dtoResponseActivitiTask.setDelegationState(activitiTask.getDelegationState());
				dtoResponseActivitiTask.setName( activitiTask.getName());
				dtoResponseActivitiTask.setDescription( activitiTask.getDescription());
				dtoResponseActivitiTask.setCreateTime( activitiTask.getCreateTime());
				dtoResponseActivitiTask.setDueDate( activitiTask.getDueDate());
				dtoResponseActivitiTask.setPriority( activitiTask.getPriority());
				dtoResponseActivitiTask.setSuspended( activitiTask.getSuspended());
				dtoResponseActivitiTask.setTaskDefinitionKey( activitiTask.getTaskDefinitionKey());
				dtoResponseActivitiTask.setTenantId( activitiTask.getTenantId());
				dtoResponseActivitiTask.setCategory( activitiTask.getCategory());
				dtoResponseActivitiTask.setFormKey( activitiTask.getFormKey());
				dtoResponseActivitiTask.setParentTaskId( activitiTask.getParentTaskId());
				dtoResponseActivitiTask.setParentTaskUrl( activitiTask.getParentTaskUrl());
				dtoResponseActivitiTask.setExecutionId( activitiTask.getExecutionId());
				dtoResponseActivitiTask.setExecutionUrl( activitiTask.getExecutionUrl());
				dtoResponseActivitiTask.setProcessInstanceId( activitiTask.getProcessInstanceId());
				dtoResponseActivitiTask.setProcessInstanceUrl( activitiTask.getProcessInstanceUrl());
				dtoResponseActivitiTask.setProcessDefinitionId( activitiTask.getProcessDefinitionId());
				dtoResponseActivitiTask.setProcessDefinitionUrl( activitiTask.getProcessDefinitionUrl());
				dtoResponseActivitiTaskList.add(dtoResponseActivitiTask);
			}
					
			ResponseEntity<List<DTOResponseActivitiTask>> responseEntity = new ResponseEntity<List<DTOResponseActivitiTask>>(dtoResponseActivitiTaskList, HttpStatus.OK);
			return responseEntity;
	}

	
	@Override
	public ResponseEntity<?> getTaskVariablesFromRuntimeTaskId(
			String taskId, String scope) throws JsonParseException, JsonMappingException, IOException, JSONException, ActivitiGetException, JsonRequestException {
		// TODO Auto-generated method stub
			List<ActivitiTaskVariable> activitiTaskVariables = 
					actitiviInterationImplementation.getTaskVariablesFromRuntimeTaskId(
							taskId, scope);
			List<DTOResponseActivitiTaskVariables> dtoResponseActivitiTaskVariables =
					new ArrayList<DTOResponseActivitiTaskVariables>();
			for(ActivitiTaskVariable activitiTaskVariable:  activitiTaskVariables){
				DTOResponseActivitiTaskVariables dtoResponseActivitiTaskVariable =
						new DTOResponseActivitiTaskVariables();
				dtoResponseActivitiTaskVariable.setName(activitiTaskVariable.getName());
				dtoResponseActivitiTaskVariable.setScope(activitiTaskVariable.getScope());
				dtoResponseActivitiTaskVariable.setType(activitiTaskVariable.getType());
				dtoResponseActivitiTaskVariable.setValue(activitiTaskVariable.getValue());
				dtoResponseActivitiTaskVariables.add(dtoResponseActivitiTaskVariable);
			}
				ResponseEntity<List<DTOResponseActivitiTaskVariables>> responseEntity =
					new ResponseEntity<List<DTOResponseActivitiTaskVariables>>(dtoResponseActivitiTaskVariables, HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * Ritorno la lista delle variabili dopo aver completato il task.
	 * 
	 * 
	 */
	@Override
	public ResponseEntity<?> createTaskVariableFromRuntimeTaskId(
			String taskId, List<ActivitiTaskVariable> activitiTaskVariables) throws JsonRequestConflictException, JsonRequestException {
		// TODO Auto-generated method stub
		List<DTOResponseActivitiTaskVariables> dtoResponseActivitiTaskVariables = 
				new ArrayList<DTOResponseActivitiTaskVariables>();
		actitiviInterationImplementation.createTaskVariableFromRuntimeTaskId(taskId, activitiTaskVariables);
		for(ActivitiTaskVariable activitiTaskVariable: activitiTaskVariables){
			DTOResponseActivitiTaskVariables dtoResponseActivitiTaskVariable= new 
					DTOResponseActivitiTaskVariables();
			dtoResponseActivitiTaskVariable.setName(activitiTaskVariable.getName());
			dtoResponseActivitiTaskVariable.setType(activitiTaskVariable.getType());
			dtoResponseActivitiTaskVariable.setValue(activitiTaskVariable.getValue());
			dtoResponseActivitiTaskVariable.setScope(activitiTaskVariable.getScope());
			dtoResponseActivitiTaskVariables.add(dtoResponseActivitiTaskVariable);
		}
		ResponseEntity<List<DTOResponseActivitiTaskVariables>> responseEntity =
					new ResponseEntity<List<DTOResponseActivitiTaskVariables>>
						(dtoResponseActivitiTaskVariables, HttpStatus.CREATED);
		
		
		return responseEntity;
	}

	
	@Override
	public ResponseEntity<DTOResponseActivitiTask> getUserTasksByCandidateGroup(
			String candidateGroup) throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
		// TODO Auto-generated method stub
		List<ActivitiTask> tasks = actitiviInterationImplementation.getUserTasksByCandidateGroup(candidateGroup);
		DTOResponseActivitiTask dtoResponseActivitiTask = new DTOResponseActivitiTask();
		dtoResponseActivitiTask.setActivitiTask(tasks);
		ResponseEntity<DTOResponseActivitiTask> responseEntity = new ResponseEntity<DTOResponseActivitiTask>(dtoResponseActivitiTask, HttpStatus.OK);
		return responseEntity;
	}
	
	@Override
	public ResponseEntity<DTOResponseActivitiTask> getUserTaskByTaskId(
			String taskId) throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
		// TODO Auto-generated method stub
			ActivitiTask activitiTask = actitiviInterationImplementation.getUserTaskByTaskId(
					taskId);
			DTOResponseActivitiTask dtoResponseActivitiTask = new DTOResponseActivitiTask();
			if(activitiTask != null){
				dtoResponseActivitiTask.setId(activitiTask.getId());
				dtoResponseActivitiTask.setUrl(activitiTask.getUrl());
				dtoResponseActivitiTask.setOwner(activitiTask.getOwner());
				dtoResponseActivitiTask.setAssignee(activitiTask.getAssignee());
				dtoResponseActivitiTask.setDelegationState(activitiTask.getDelegationState());
				dtoResponseActivitiTask.setName(activitiTask.getName());
				dtoResponseActivitiTask.setDescription(activitiTask.getDescription());
				dtoResponseActivitiTask.setCreateTime(activitiTask.getCreateTime());
				dtoResponseActivitiTask.setDueDate(activitiTask.getDueDate());
				dtoResponseActivitiTask.setPriority(activitiTask.getPriority());
				dtoResponseActivitiTask.setSuspended(activitiTask.getSuspended());
				dtoResponseActivitiTask.setTaskDefinitionKey(activitiTask.getTaskDefinitionKey());
				dtoResponseActivitiTask.setTenantId(activitiTask.getTenantId());
				dtoResponseActivitiTask.setCategory(activitiTask.getCategory());
				dtoResponseActivitiTask.setFormKey(activitiTask.getFormKey());
				dtoResponseActivitiTask.setParentTaskId(activitiTask.getParentTaskId());
				dtoResponseActivitiTask.setParentTaskUrl(activitiTask.getParentTaskUrl());
				dtoResponseActivitiTask.setExecutionId(activitiTask.getExecutionId());
				dtoResponseActivitiTask.setExecutionUrl(activitiTask.getExecutionUrl());
				dtoResponseActivitiTask.setProcessInstanceId(activitiTask.getProcessInstanceId());
				dtoResponseActivitiTask.setProcessInstanceUrl(activitiTask.getProcessInstanceUrl());
				dtoResponseActivitiTask.setProcessDefinitionId(activitiTask.getProcessDefinitionId());
				dtoResponseActivitiTask.setProcessDefinitionUrl(activitiTask.getProcessDefinitionUrl());
				ResponseEntity<DTOResponseActivitiTask> responseEntity =
						new ResponseEntity<DTOResponseActivitiTask>(dtoResponseActivitiTask,
								HttpStatus.OK);
				return responseEntity;
			}
			else {
				ResponseEntity<DTOResponseActivitiTask> responseEntity =
						new ResponseEntity<DTOResponseActivitiTask>(dtoResponseActivitiTask,
								HttpStatus.NOT_FOUND);
				return responseEntity;
			}
			
	}

	@Override
	public ResponseEntity<DTOResponseActivitiTaskVariable> updateActivitiTaskVariable(String id, String name,
                                                                                      DTOActivitiTaskVariable dtoActivitiTaskVariable) throws JsonParseException, JsonMappingException, IOException, ActivitiPutException, JsonRequestException {
		// TODO Auto-generated method stub
		ActivitiTaskVariable activitiTaskVariable  =
				actitiviInterationImplementation.updateActivitiTaskVariable(id,
				name, dtoActivitiTaskVariable);
		DTOResponseActivitiTaskVariable dtoResponseActivitiTaskVariable =
				new DTOResponseActivitiTaskVariable();
		dtoResponseActivitiTaskVariable.setName(activitiTaskVariable.getName());
		dtoResponseActivitiTaskVariable.setType(activitiTaskVariable.getType());
		dtoResponseActivitiTaskVariable.setScope(activitiTaskVariable.getScope());
		dtoResponseActivitiTaskVariable.setValue(activitiTaskVariable.getValue());
		
		ResponseEntity<DTOResponseActivitiTaskVariable> responseEntity =
				new ResponseEntity<DTOResponseActivitiTaskVariable>(dtoResponseActivitiTaskVariable,
						HttpStatus.OK);
		return responseEntity;
		
	}
	
	
}