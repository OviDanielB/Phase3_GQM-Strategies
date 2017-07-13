package it.uniroma2.isssr.gqm3.service;

import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.Attribute;
import it.uniroma2.isssr.gqm3.model.StrategicPlan;
import it.uniroma2.isssr.gqm3.model.StrategyWorkflowRelation;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseAttribute;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseMetaWorkflow;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseSWRelation;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategicPlan;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: StrategicPlanService</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * 
 * <p>Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * Interfaccia per la dichiarazione di metodi invocati nella richiesta degli strategic plans 
 * del servizio REST che restituiscono la relativa risposta.
 * Qui si espongono i metodi per interagire con gli Strategic Plan</p> 
 * 
 * @author Daniele Capri
 * @version 1.0
 *
 */
public interface StrategicPlanService {
	
	
	/**
	 * Gets the strategic plan.
	 *
	 * @param strategicPlanId the strategic plan id
	 * @return the strategic plan
	 */
	public ResponseEntity<DTOResponseStrategicPlan> getStrategicPlan(String strategicPlanId);
	
	
	/**
	 * Gets the strategic plans.
	 *
	 * @return the strategic plans
	 */
	public List<StrategicPlan> getStrategicPlansList();
	
	
	/**
	 * Gets the strategic plans.
	 *
	 * @return the strategic plans
	 */
	public ResponseEntity<DTOResponseStrategicPlan> getStrategicPlans();

	/**
	 * Creates the attribute.
	 *
	 * @param name the name
	 * @param type the type
	 * @param value the value
	 * @return the response entity
	 */
	public ResponseEntity<DTOResponseAttribute> createAttribute(String name, String type, String value);

	/**
	 * Creates the strategic plan.
	 *
	 * @param strategyId the List of strategy id
	 * @param name the name
	 * @param description the description
	 * @param version the version
	 * @param release the release
	 * @return the response entity
	 */
	public ResponseEntity<DTOResponseStrategicPlan> createStrategicPlan(ArrayList<String> strategyId, String name, String description,
                                                                        float version, String release);
	
	/**
	 * Update strategic plan.
	 *
	 * @param id the id
	 * @param name the name
	 * @param description the description
	 * @param attributes the attributes
	 * @param version the version
	 * @param release the release
	 * @return the response entity
	 */
	public ResponseEntity<DTOResponseStrategicPlan> updateStrategicPlan(String id, String name, String description,
                                                                        ArrayList<Attribute> attributes, float version, String release);
	
	
	/**
	 * Delete strategic plan.
	 *
	 * @param strategyId the strategy id
	 * @param name the name
	 * @param description the description
	 * @param attributes the attributes
	 * @param version the version
	 * @param release the release
	 * @return the response entity
	 */
	/*ResponseEntity<DTOResponseStrategicPlan> deleteStrategicPlan(ArrayList<String> strategyId, String name, String description,
			ArrayList<Attribute> attributes, float version, String release);
*/
	/**
	 * Gets the meta workflows.
	 *
	 * @param id the id
	 * @return the meta workflows
	 */

	/**
	 * Gets the strategy to workflow.
	 *
	 * @param id the id
	 * @return the strategy to workflow
	 */
	public ResponseEntity<DTOResponseStrategicPlan> getStrategyToWorkflow(String id);

	/**
	 * Sets the meta workflow.
	 *
	 * @param strategicPlanId the strategic plan id
	 * @param strategyId the strategy id
	 * @param name the name
	 * @return the response entity
	 */
	public ResponseEntity<DTOResponseSWRelation> setMetaWorkflow(String strategicPlanId, String strategyId, String name) throws ProcessDefinitionNotFoundException, IllegalCharacterRequestException, BusinessWorkflowNotCreatedException, JsonRequestException, ActivitiEntityAlreadyExistsException, MetaWorkflowNotStartedException, JsonRequestConflictException, MetaWorkflowNotDeployedException, ModelXmlNotFoundException;


	public ResponseEntity<DTOResponseSWRelation> getMetaWorkflows(String id);


	public ResponseEntity<DTOResponseMetaWorkflow> getStrategiesOfStrategicPlan(String id);
	
	public ResponseEntity<DTOResponseMetaWorkflow> getStrategyWorkflowRelationOfStrategicPlan(String id);
	

	public ResponseEntity<DTOResponseMetaWorkflow> getMetaworkflowOfStrategicPlan(String id);
	
	public ResponseEntity<DTOResponseMetaWorkflow> getWorkflowBusinessIdOfStrategicPlan(String id);

	public ResponseEntity<DTOResponseStrategicPlan> updateStrategicPlan(ArrayList<String> strategyId, String name,
                                                                        String description, ArrayList<Attribute> attributes, float version, String release);

	public ResponseEntity<DTOResponseStrategicPlan> deleteStrategicPlan(String id);
	
	public ResponseEntity<DTOResponseSWRelation> getStrategyWorkflowData(String strategicPlanId, String strategyId);

	public ResponseEntity<DTOResponseMetaWorkflow> getStrategyWithWorkflow(String strategicPlanId);
	
	public ResponseEntity<DTOResponseMetaWorkflow> getStrategiesWithOrganizationalUnitOfStrategicPlan(String strategicPlanId);

	public ResponseEntity<DTOResponseStrategicPlan> updateStrategiesOfStrategicPlan(String id,
                                                                                    ArrayList<StrategyWorkflowRelation> strategyToWorkflowId);

}
