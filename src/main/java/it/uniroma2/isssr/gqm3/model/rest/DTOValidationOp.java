package it.uniroma2.isssr.gqm3.model.rest;

import it.uniroma2.isssr.gqm3.model.validation.*;

import java.util.List;

public class DTOValidationOp extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private ValidationType type;
	private String description;
	private ValidationCardinality cardinality;
	private ComparisonType compType;
	private String measureTaskId;
	private String refMeasureTaskId;
	private String[] referenceParams;
	private List<Countermeasure> countermeasures;
	private DatasetOperation thisOp;
	private DatasetOperation refOp;
	private boolean userDefined;
	private boolean contromeasureDone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ValidationType getType() {
		return type;
	}
	public void setType(ValidationType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ValidationCardinality getCardinality() {
		return cardinality;
	}
	public void setCardinality(ValidationCardinality cardinality) {
		this.cardinality = cardinality;
	}
	public ComparisonType getCompType() {
		return compType;
	}
	public void setCompType(ComparisonType compType) {
		this.compType = compType;
	}
	/*
	public MeasureTask getMeasureTask() {
		return measureTask;
	}
	public void setMeasureTask(MeasureTask measureTask) {
		this.measureTask = measureTask;
	}
	public MeasureTask getRefMeasureTask() {
		return refMeasureTask;
	}
	public void setRefMeasureTask(MeasureTask refMeasureTask) {
		this.refMeasureTask = refMeasureTask;
	}*/
	
	
	public String[] getReferenceParams() {
		return referenceParams;
	}
	public String getMeasureTaskId() {
		return measureTaskId;
	}
	public void setMeasureTaskId(String measureTaskId) {
		this.measureTaskId = measureTaskId;
	}
	public String getRefMeasureTaskId() {
		return refMeasureTaskId;
	}
	public void setRefMeasureTaskId(String refMeasureTaskId) {
		this.refMeasureTaskId = refMeasureTaskId;
	}
	public void setReferenceParams(String[] referenceParams) {
		this.referenceParams = referenceParams;
	}
	public List<Countermeasure> getCountermeasures() {
		return countermeasures;
	}
	public void setCountermeasures(List<Countermeasure> countermeasures) {
		this.countermeasures = countermeasures;
	}
	
	public DatasetOperation getThisOp() {
		return thisOp;
	}
	public void setThisOp(DatasetOperation thisOp) {
		this.thisOp = thisOp;
	}
	public DatasetOperation getRefOp() {
		return refOp;
	}
	public void setRefOp(DatasetOperation refOp) {
		this.refOp = refOp;
	}
	public boolean isUserDefined() {
		return userDefined;
	}
	public void setUserDefined(boolean userDefined) {
		this.userDefined = userDefined;
	}
	public boolean isContromeasureDone() {
		return contromeasureDone;
	}
	public void setContromeasureDone(boolean contromeasureDone) {
		this.contromeasureDone = contromeasureDone;
	}
	
	
	
	
}
