package it.uniroma2.isssr.gqm3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * <p>Title: StrategyWorkflowRelation</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * ...
 * 
 * 
 * @author Daniele Capri
 * @version 1.0
 *
 */

@Document
public class StrategyWorkflowRelation {
	
	@Id
	private String _id;
	
	/** The strategy id. */
	@DBRef
	private Strategy strategy;
	
	/** The metaworkflow id. */
	@DBRef
	private WorkflowData workflow;
	
	

	/**
	 * Instantiates a new strategy workflow relation.
	 */
	public StrategyWorkflowRelation() {

	}

	/**
	 * Instantiates a new strategy workflow relation.
	 *
	 * @param strategy the strategy
	 */
	public StrategyWorkflowRelation(Strategy strategy) {
		super();
		this.strategy = strategy;
		workflow = null;
	}

	/**
	 * Instantiates a new strategy workflow relation.
	 *
	 * @param strategy the strategy id
	 * @param workflow the metaworkflow id
	 */
	public StrategyWorkflowRelation( Strategy strategy, WorkflowData workflow) {
		super();
		
		this.strategy = strategy;
		this.workflow = workflow;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public WorkflowData getWorkflow() {
		return workflow;
	}

	public void setWorkflow(WorkflowData workflow) {
		this.workflow = workflow;
	}

	
	
}
