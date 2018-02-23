package it.uniroma2.isssr.gqm3.model;

import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormVariableProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * <p>Title: VariableActiviti</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * Classe entity pensata per mappare nel MongoDB locale il valore di una variabile
 * del form completato, per poi successivamente recuperare tale valore, e
 * eliminarlo dal database
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */
@Document
public class VariableActiviti {
	
	@Id
	private String id;
	
	private String taskId;
	private String taskDefinitionKey;
	private List<ActivitiFormVariableProperty> properties;
	
	
	public VariableActiviti() {
		
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}


	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}


	public List<ActivitiFormVariableProperty> getProperties() {
		return properties;
	}


	public void setProperties(List<ActivitiFormVariableProperty> properties) {
		this.properties = properties;
	}
	
}
