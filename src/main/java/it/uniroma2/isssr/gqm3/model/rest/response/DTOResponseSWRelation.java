package it.uniroma2.isssr.gqm3.model.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Class DTOResponseSWRelation.
 */
public class DTOResponseSWRelation  extends DTO{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The strategies. */
		@JsonInclude(Include.NON_NULL)
		private List<HashMap<String, Object>> strategyToMetaworkflow;
		
		/** The strategies. */
		@JsonInclude(Include.NON_NULL)
		private Strategy strategy;
		
		/** The strategies. */
		@JsonInclude(Include.NON_NULL)
		private WorkflowData workflow;

		/**
		 * Instantiates a new DTO response sw relation.
		 */
		public DTOResponseSWRelation() {
			super();
			strategyToMetaworkflow=new ArrayList<HashMap<String, Object>>();
			
		}

		/**
		 * Gets the strategy to metaworkflow.
		 *
		 * @return the strategy to metaworkflow
		 */
		public List<HashMap<String, Object>> getStrategyToMetaworkflow() {
			return strategyToMetaworkflow;
		}

		/**
		 * Sets the strategy to metaworkflow.
		 *
		 * @param strategyToMetaworkflow the strategy to metaworkflow
		 */
		public void setStrategyToMetaworkflow(List<HashMap<String, Object>> strategyToMetaworkflow) {
			this.strategyToMetaworkflow = strategyToMetaworkflow;
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

		/**
		 * Push.
		 *
		 * @param strategy the strategy
		 * @param metaWorkflow the meta workflow
		 */
		public void push(Strategy strategy, WorkflowData metaWorkflow){
			HashMap<String, Object> tempMap= new HashMap<String, Object>();
			tempMap.put("strategy", strategy);
			tempMap.put("metaworkflow", metaWorkflow);
			strategyToMetaworkflow.add(tempMap);
		}
		
		
		
		
}


	
