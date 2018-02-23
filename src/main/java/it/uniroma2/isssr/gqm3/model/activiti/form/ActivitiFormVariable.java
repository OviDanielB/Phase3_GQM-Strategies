package it.uniroma2.isssr.gqm3.model.activiti.form;

import java.util.List;

public class ActivitiFormVariable {
	
		private String taskId;
		private List<ActivitiFormVariableProperty> properties;
		public String getTaskId() {
			return taskId;
		}
		public void setTaskId(String taskId) {
			this.taskId = taskId;
		}
		public List<ActivitiFormVariableProperty> getProperties() {
			return properties;
		}
		public void setProperties(List<ActivitiFormVariableProperty> properties) {
			this.properties = properties;
		}
		
}
