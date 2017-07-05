package it.uniroma2.isssr.gqm3.model.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.Attribute;
import it.uniroma2.isssr.gqm3.model.StrategicPlan;
import it.uniroma2.isssr.gqm3.model.StrategyWorkflowRelation;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: DTOResponseStrategicPlan</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * DTO relativa utilizzata per ritornare il risultato di una richiesta REST. Qui
 * dentro saranno salvati i campi della risposta.
 * 
 * 
 * @author Daniele Capri
 * @version 1.0
 *
 */

// TODO: Auto-generated Javadoc

public class DTOResponseStrategicPlan extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list of strategic plan. */
	@JsonInclude(Include.NON_NULL)
	private List<StrategicPlan> strategicPlans;
	
	/** The id. */
	@JsonInclude(Include.NON_NULL)
	private String id;
	
	/** The strategy_id. */
	@JsonInclude(Include.NON_NULL)
	private ArrayList<StrategyWorkflowRelation> strategyToWorkflowId;


	/** The name. */
	@JsonInclude(Include.NON_NULL)
	private String name;

	/** The description. */
	@JsonInclude(Include.NON_NULL)
	private String description;
	
	@JsonInclude(Include.NON_NULL)
	private String organizzationalunit;
	
	/** The version. */
	private float version;
	
	/** The release. */
	@JsonInclude(Include.NON_NULL)
	private String release;

	/** The attributes. */
	@JsonInclude(Include.NON_NULL)
	private ArrayList<Attribute> attributes;

	/**
	 * Instantiates a new DTO response strategic plan.
	 *
	 * @param error
	 *            the error
	 * @param message
	 *            the message
	 */
	public DTOResponseStrategicPlan(String error, String message) {
		this.error = error;
		this.message = message;
	}

	/**
	 * Instantiates a new DTO response strategic plan.
	 */
	public DTOResponseStrategicPlan() {

	}

	/**
	 * Gets the strategic plans.
	 *
	 * @return the strategic plans
	 */
	public List<StrategicPlan> getStrategicPlans() {
		return strategicPlans;
	}

	/**
	 * Sets the strategic plans.
	 *
	 * @param strategicPlans
	 *            the new strategic plans
	 */
	public void setStrategicPlans(List<StrategicPlan> strategicPlans) {
		this.strategicPlans = strategicPlans;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	

	/**
	 * Gets the strategy to workflow id.
	 *
	 * @return the strategy to workflow id
	 */

	public ArrayList<StrategyWorkflowRelation> getStrategyToWorkflowId() {
		return strategyToWorkflowId;

	}

	/**
	 * Sets the strategy to workflow id.
	 *
	 * @param strategyToWorkflowId the new strategy to workflow id
	 */

	public void setStrategyToWorkflowId(ArrayList<StrategyWorkflowRelation> strategyToWorkflowId) {
		this.strategyToWorkflowId = strategyToWorkflowId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the organizzational unit.
	 *
	 * @return the organizzational unit
	 */
	public String getOrganizzationalUnit() {
		return organizzationalunit;
	}

	/**
	 * Sets the organizzational unit.
	 *
	 * @param organizzationalUnit the new organizzational unit
	 */
	public void setOrganizzationalUnit(String organizzationalUnit) {
		this.organizzationalunit = organizzationalUnit;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public float getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(float version) {
		this.version = version;
	}

	/**
	 * Gets the release.
	 *
	 * @return the release
	 */
	public String getRelease() {
		return release;
	}

	/**
	 * Sets the release.
	 *
	 * @param release the new release
	 */
	public void setRelease(String release) {
		this.release = release;
	}

	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attributes the new attributes
	 */
	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
