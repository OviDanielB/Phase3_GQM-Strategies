package it.uniroma2.isssr.gqm3.model.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.StrategyWorkflowRelation;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.List;

/**
 * <p>Title: DTOResponseMetaWorkflow</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * Introdotta in luogo della DTOResponseStrategicPlan
 * Alla fine questa classe fornisce sempre proprietà legate allo StrategicPlan, 
 * ma quella aveva un bug e restituiva anche il campo version = 0.0.. Va risolto
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

public class DTOResponseMetaWorkflow extends DTO{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Lista delle strategie, aggiunta perché 
	 *  mi serve che nella risposta relativa
	 *  allo strategicPlan mi venga restituito un
	 *  Json con la lista delle strategie (ho aggiunto anche le altre due liste)
	 */
	@JsonInclude(Include.NON_NULL)
	private List<Strategy> strategies;
	
	@JsonInclude(Include.NON_NULL)
	private List<WorkflowData> metaWorkflowList;
	
	@JsonInclude(Include.NON_NULL)
	private List<StrategyWorkflowRelation> strategyWorkflowRelationList;
	

	/**
	 * Instantiates a new DTO response meta Workflow.
	 *
	 * @param error
	 *            the error
	 * @param message
	 *            the message
	 */
	public DTOResponseMetaWorkflow(String error, String message) {
		this.error = error;
		this.message = message;
	}
	
	public DTOResponseMetaWorkflow() {
		
	}
	
	/**
	 * Metodi set e get introdotti da me per settare il valore delle liste che devo
	 * esporre alle chiamate rest
	 */
	public List<WorkflowData> getMetaWorkflowList() {
		return metaWorkflowList;
	}

	public void setMetaWorkflowList(List<WorkflowData> metaWorkflowList2) {
		this.metaWorkflowList = metaWorkflowList2;
	}

	public List<StrategyWorkflowRelation> getStrategyWorkflowRelationList() {
		return strategyWorkflowRelationList;
	}

	public void setStrategyWorkflowRelationList(List<StrategyWorkflowRelation> strategyWorkflowRelationList2) {
		this.strategyWorkflowRelationList = strategyWorkflowRelationList2;
	}
	
	public List<Strategy> getStrategies() {
		return strategies;
	}

	public void setStrategies(List<Strategy> strategies) {
		this.strategies = strategies;
	}
	
}


