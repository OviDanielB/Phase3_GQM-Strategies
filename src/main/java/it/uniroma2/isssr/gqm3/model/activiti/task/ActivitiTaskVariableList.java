package it.uniroma2.isssr.gqm3.model.activiti.task;

import java.util.ArrayList;
import java.util.List;

public class ActivitiTaskVariableList {
	private List<ActivitiTaskVariable> activitiTaskVariableList = new ArrayList<ActivitiTaskVariable>();

	public List<ActivitiTaskVariable> getActivitiTaskVariableList() {
		return activitiTaskVariableList;
	}

	public void setActivitiTaskVariableList(List<ActivitiTaskVariable> activitiTaskVariableList) {
		this.activitiTaskVariableList = activitiTaskVariableList;
	}
	
	public void addActivitiTaskVariable(ActivitiTaskVariable activitiTaskVariable){
		activitiTaskVariableList.add(activitiTaskVariable);
	}
}
