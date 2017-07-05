package it.uniroma2.isssr.gqm3.model.activiti.form;

import java.util.ArrayList;
import java.util.List;

public class ActivitiFormPropertiesList {
	private List<ActivitiFormProperty> activitiFormProperties = new ArrayList<ActivitiFormProperty>();

	public List<ActivitiFormProperty> getActivitiFormProperties() {
		return activitiFormProperties;
	}

	public void setActivitiFormProperties(List<ActivitiFormProperty> activitiFormProperties) {
		this.activitiFormProperties = activitiFormProperties;
	}

	public void addActivitiFormProperties(ActivitiFormProperty activitiFormProperty){
		activitiFormProperties.add(activitiFormProperty);
	}
}
