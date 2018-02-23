package it.uniroma2.isssr.gqm3.model.validation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * La classe ValidationOp rappresenta l'operazione di validazione che viene
 * effettuata su un dato task di misura.
 * 
 * @author federico, versione rivista da Fabio per evitare Infinite Recursion
 *         with Jackson JSON
 * 
 * @link http://www.baeldung.com/jackson-bidirectional-relationships-and-
 *       infinite-recursion
 * @link http://stackoverflow.com/questions/3325387/infinite-recursion-with-
 *       jackson-json-and-hibernate-jpa-issue
 *
 *       In questa versione non ho un doppio riferimento ricorsivo, ma tengo
 *       traccia solo dell'id (ho scelto il taskId perché è univoco, ma volendo
 *       si può salvare anche l'id nel db)
 */
@Document
public class ValidationOp {
	@Id
	private String id;

	private String name;

	/**
	 * Il tipo di validazione da effettuare.
	 */
	private ValidationType type;

	/**
	 * La descrizione dell'operazione di validazione.
	 */
	private String description;

	/**
	 * La cardinalità dell'operazione di validazione.
	 */
	private ValidationCardinality cardinality;

	/**
	 * Il tipo di confronto da effettuare.
	 */
	private ComparisonType compType;

	/**
	 * L'id del measure task a cui la ValidationOp è associata.
	 */
	// @DBRef

	// private MeasureTask measureTask;
	private String measureTaskId;
	/**
	 * L'id del measure task con cui si effettua il confronto.
	 */
	// @DBRef
	// private MeasureTask refMeasureTask;
	private String refMeasureTaskId;
	/**
	 * I parametri con cui si effettua il confronto. TODO: è provvisorio!
	 */
	private String[] referenceParams;

	/**
	 * La lista delle possibili operazioni da effettuare nel caso la validazione
	 * non venga passata.
	 */
	private List<Countermeasure> countermeasures;

	/**
	 * L'operazione da effettuare sul set di dati relativo alla misura corrente.
	 */
	private DatasetOperation thisOp;

	/**
	 * L'operazione da effettuare sul set di dati relativo alla misura di
	 * riferimento.
	 */
	private DatasetOperation refOp;

	private boolean userDefined;

	// se è stata scelta una operazione da fare sul data collected non deve
	// essere più mostrato il validationOp
	private boolean contromeasureDone;

	/*
	 * TODO: definisci i vari costruttori. Ha senso farli in base alla
	 * cardinalità?
	 */
	public ValidationOp() {

	}

	public ValidationOp(ValidationType type, String description, ValidationCardinality cardinality,
			String measureTaskId) {
		this.type = type;
		this.description = description;
		this.cardinality = cardinality;
		this.measureTaskId = measureTaskId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	 * public MeasureTask getThisMeasureTask() { return measureTask; }
	 * 
	 * public void setThisMeasureTask(MeasureTask measureTask) {
	 * this.measureTask = measureTask; }
	 * 
	 * public MeasureTask getRefMeasureTask() { return refMeasureTask; }
	 * 
	 * public void setRefMeasureTask(MeasureTask refMeasureTask) {
	 * this.refMeasureTask = refMeasureTask; }
	 */

	public String[] getReferenceParams() {
		return referenceParams;
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

	public Boolean getUserDefined() {
		return userDefined;
	}

	public void setUserDefined(boolean userDefined) {
		this.userDefined = userDefined;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isContromeasureDone() {
		return contromeasureDone;
	}

	public void setContromeasureDone(boolean contromeasureDone) {
		this.contromeasureDone = contromeasureDone;
	}
}
