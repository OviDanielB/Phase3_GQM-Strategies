package it.uniroma2.isssr.gqm3.model.activiti.task;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ActivitiTaskList</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * Entity che non è altro che una lista di ActivitiTask
 * E' necessaria per trattare i risultati del Json
 * restituito da Activiti Rest
 *
 *  
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */
public class ActivitiTaskList {
	private List<ActivitiTask> activitiTaskList = new ArrayList<ActivitiTask>();

	public List<ActivitiTask> getActivitiTaskList() {
		return activitiTaskList;
	}

	public void setActivitiTaskList(List<ActivitiTask> activitiTaskList) {
		this.activitiTaskList = activitiTaskList;
	}
	
	public void addActivitiTask(ActivitiTask task){
		activitiTaskList.add(task);
	}
	
}
