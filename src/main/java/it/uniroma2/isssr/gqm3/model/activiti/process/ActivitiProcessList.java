package it.uniroma2.isssr.gqm3.model.activiti.process;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity che non è altro che una lista di ActivitiProcess
 * E' necessaria per trattare i risultati del Json
 * restituito da Activiti Rest
 * 
 * @author luca
 *
 */

public class ActivitiProcessList {

	
	private List<ActivitiProcessDef> activitiProcessList = new ArrayList<ActivitiProcessDef>();

	public List<ActivitiProcessDef> getActivitiProcessList() {
		return activitiProcessList;
	}

	public void setActivitiProcessList(List<ActivitiProcessDef> activitiProcessList) {
		this.activitiProcessList = activitiProcessList;
	}
	
	public void addActivitiProcess(ActivitiProcessDef activitiProcessDef){
		activitiProcessList.add(activitiProcessDef);
	}
	
	
}



