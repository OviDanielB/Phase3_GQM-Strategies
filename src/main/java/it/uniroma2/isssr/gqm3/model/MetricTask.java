package it.uniroma2.isssr.gqm3.model;

import it.uniroma2.isssr.gqm3.model.ontologyPhase2.Ontology;

public class MetricTask {
	
	String nameMetric;
	String idTask;
	String nameTask;
	String descriptionMetric;
	Boolean newVersion = false;
	Ontology ontology;
	
	
	public MetricTask(String nameMetric, String nameTask, String descriptionMetric,String idTask) {
		super();
		this.idTask = idTask;
		this.nameMetric = nameMetric;
		this.nameTask = nameTask;
		this.descriptionMetric = descriptionMetric;
	}

	public MetricTask(String idTask,Ontology ontology) {
		super();
		this.idTask = idTask;
		this.ontology = ontology;
		this.newVersion = true;
	}

	public Ontology getOntology() {
		return ontology;
	}

	public void setOntology(Ontology ontology) {
		this.ontology = ontology;
	}

	public Boolean getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(Boolean newVersion) {
		this.newVersion = newVersion;
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
