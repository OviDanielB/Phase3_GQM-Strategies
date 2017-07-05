package it.uniroma2.isssr.gqm3.model32;

public class MetricTask {
	
	String nameMetric;
	String idTask;
	String nameTask;
	String descriptionMetric;
	
	
	public MetricTask(String nameMetric, String nameTask, String descriptionMetric,String idTask) {
		super();
		this.idTask = idTask;
		this.nameMetric = nameMetric;
		this.nameTask = nameTask;
		this.descriptionMetric = descriptionMetric;
	}
	
	
	public String getIdTask() {
		return idTask;
	}


	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}


	public String getNameMetric() {
		return nameMetric;
	}
	public void setNameMetric(String nameMetric) {
		this.nameMetric = nameMetric;
	}
	public String getNameTask() {
		return nameTask;
	}
	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}
	public String getDescriptionMetric() {
		return descriptionMetric;
	}
	public void setDescriptionMetric(String descriptionMetric) {
		this.descriptionMetric = descriptionMetric;
	}
	
	

}
