/*
 * @author Daniele Capri
 */
package it.uniroma2.isssr.gqm3.model;


import it.uniroma2.isssr.gqm3.repository.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * <p>Title: ActivitiUserList</p>
 *
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>Class description:
 *
 * Entity della nostra applicazione, annotata con @Document per notificare a
 * Spring che è un oggetto persistente in Mongo DB.
 *
 *
 * @author Daniele Capri
 * @version 1.0
 *
 */
@Document
public class StrategicPlan {

	/** The id. */
	@Id
	private String id;

	/** The strategy and workflow ids. This attribute represent the link (one to one) between a
	 * strategy and a meta workflow. This relationship is unique because a strategy links to a unique
	 * organizative unit and so to a unique strategic plan too*/
	private ArrayList<StrategyWorkflowRelation> strategyWorkflowIds;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The organizational unit. */
	private String organizationalunit;

	/** The attributes represent . */
	private ArrayList<Attribute> attributes;

	/** The version. */
	private Float version;

	/** The release. */
	private String release;

	/**
	 * Instantiates a new strategic plan.
	 */
	public StrategicPlan() {
	}



	/**
	 * Instantiates a new strategic plan.
	 *
	 * @param strategyIds the strategy ids
	 * @param name            the name
	 * @param description            the description
	 * @param organizationalUnit the organizational unit
	 * @param version            the version
	 * @param release            the release
	 */

//	public StrategicPlan(ArrayList<String> strategyIds, String name, String description, float version, String release) {
//		ArrayList<StrategyWorkflowRelation> newStrategyIds=new ArrayList<StrategyWorkflowRelation>();
//		for (String string : strategyIds) {
//			StrategyWorkflowRelation strategy =new StrategyWorkflowRelation(string);
//			newStrategyIds.add(strategy);
//		}
//		this.strategyWorkflowIds=newStrategyIds;
//		this.name = name;
//		this.description = description;
//		this.version = version;
//		this.release = release;
//	
//		attributes = new ArrayList<Attribute>();
//		
//
//	}


	public StrategicPlan(ArrayList<StrategyWorkflowRelation> strategyIds, String name, String description, String organizationalUnit, float version, String release) {


		this.strategyWorkflowIds=strategyIds;

		this.name = name;
		this.description = description;
		this.organizationalunit=organizationalUnit;
		this.version = version;
		this.release = release;
		attributes = new ArrayList<Attribute>();


	}

	/**
	 * Instantiates a new strategic plan.
	 *
	 * @param strategyIds the strategy ids
	 * @param name the name
	 * @param description the description
	 * @param organizationalUnit the organizational unit
	 * @param attributes the attributes
	 * @param version the version
	 * @param release the release
	 */

	public StrategicPlan(ArrayList<Strategy> strategyIds, String name, String description, String organizationalUnit, ArrayList<Attribute> attributes, float version, String release) {

		ArrayList<StrategyWorkflowRelation> newStrategyIds=new ArrayList<StrategyWorkflowRelation>();
		for (Strategy str : strategyIds) {
			StrategyWorkflowRelation strategy =new StrategyWorkflowRelation(str);
			newStrategyIds.add(strategy);
		}
		this.strategyWorkflowIds=newStrategyIds;
		this.name = name;
		this.description = description;
		this.organizationalunit=organizationalUnit;
		this.attributes = attributes;
		this.version = version;
		this.release = release;
		attributes = new ArrayList<Attribute>();

	}



	/**
	 * Instantiates a new strategic plan.
	 *
	 * @param id the id
	 * @param strategyIds the strategy ids
	 * @param name the name
	 * @param description the description
	 * @param organizationalUnit the organizational unit
	 * @param attributes the attributes
	 * @param version the version
	 * @param release the release
	 */
	public StrategicPlan(String id, ArrayList<Strategy> strategyIds, String name, String description, String organizationalUnit, ArrayList<Attribute> attributes, float version, String release) {

		ArrayList<StrategyWorkflowRelation> newStrategyIds=new ArrayList<StrategyWorkflowRelation>();
		for (Strategy str : strategyIds) {
			StrategyWorkflowRelation strategy =new StrategyWorkflowRelation(str);
			newStrategyIds.add(strategy);
		}
		this.strategyWorkflowIds=newStrategyIds;
		this.name = name;
		this.description = description;
		this.organizationalunit=organizationalUnit;
		this.attributes = attributes;
		this.version = version;
		this.release = release;
		attributes = new ArrayList<Attribute>();
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
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the strategy id.
	 *
	 * @return the strategy id
	 */
	public ArrayList<String> strategyIds() {
		ArrayList<String> result= new ArrayList<String>();
		for (StrategyWorkflowRelation string : strategyWorkflowIds) {
			result.add(string.getStrategy().getId());
		}

		return result;
	}

	/**
	 * Sets the strategy id.
	 *
	 * @param strategyIds the new strategy ids
	 */
	public void setStrategyIds(ArrayList<Strategy> strategyIds) {
		ArrayList<StrategyWorkflowRelation> newStrategyIds=new ArrayList<StrategyWorkflowRelation>();
		for (Strategy string : strategyIds) {
			StrategyWorkflowRelation strategy =new StrategyWorkflowRelation(string);
			newStrategyIds.add(strategy);
		}
		this.strategyWorkflowIds=newStrategyIds;


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
	 * @param name
	 *            the new name
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
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * Gets the organizational unit.
	 *
	 * @return the organizational unit
	 */
	public String getOrganizationalUnit() {
		return organizationalunit;
	}

	/**
	 * Sets the organizational unit.
	 *
	 * @param organizzationalUnit the new organizational unit
	 */
	public void setOrganizationalUnit(String organizzationalUnit) {
		this.organizationalunit = organizzationalUnit;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Float getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(Float version) {
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
	 * @param release
	 *            the new release
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
	 * @param attributes
	 *            the new attributes
	 */
	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}


	/**
	 * Gets the strategy workflow ids.
	 *
	 * @return the strategy workflow ids
	 */
	public ArrayList<StrategyWorkflowRelation> getStrategyWorkflowIds() {
		return strategyWorkflowIds;

	}
	/**
	 * Sets the strategy workflow ids.
	 *
	 * @param strategyWorkflowIds the new strategy workflow ids
	 */
	public void setStrategyWorkflowIds(ArrayList<StrategyWorkflowRelation> strategyWorkflowIds) {
		if(!strategyWorkflowIds.isEmpty()){
		String tempOrgUnit = strategyWorkflowIds.get(0).getStrategy().getOrganizationalunit();
		for (StrategyWorkflowRelation swr : strategyWorkflowIds) {
			String temp2OrgUnit = swr.getStrategy().getOrganizationalunit();
			if(!temp2OrgUnit.equals(tempOrgUnit)){
				organizationalunit = null;
				return;
			}
		}
		this.strategyWorkflowIds = strategyWorkflowIds;}

	}

	 /**
	 * Gets the metaworkflow id starting from strategy.
	 *
	 * @param strategyId the strategy id
	 * @return the meta id
	 */
	public String getMetaId(Strategy strategyId){
		for (StrategyWorkflowRelation strings : strategyWorkflowIds) {
			if(strings.getStrategy().equals(strategyId)){
				return strings.getWorkflow().get_id();
			}
		}
		return null;
	}

	/**
	 * Sets the metaworkflow id starting from the associated strategy.
	 *
	 * @param strategyId the strategy id
	 * @param metaWorkflowId the meta workflow id
	 */
	public void setMetaId(Strategy strategyId, WorkflowData metaWorkflowId){
		for (StrategyWorkflowRelation strings : strategyWorkflowIds) {
			if(strings.getStrategy().getId().equals(strategyId.getId())){
				strings.setWorkflow(metaWorkflowId);
			}
		}

	}

	public WorkflowData getSWFromStrategy(Strategy strategy){
		for (StrategyWorkflowRelation swr : strategyWorkflowIds) {
			if(swr.getStrategy().getId().equals(strategy.getId())){
				return swr.getWorkflow();
			}
		}
		return null;
	}

}
