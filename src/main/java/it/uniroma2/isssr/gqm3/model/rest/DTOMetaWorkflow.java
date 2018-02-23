/*
 * @autor Daniele Capri
 */
package it.uniroma2.isssr.gqm3.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class DTOMetaWorkflow.
 */
public class DTOMetaWorkflow extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The strategy id. */
	@JsonInclude(Include.NON_NULL)
	private String strategyId;
	
	/** The strategic plan id. */
	@JsonInclude(Include.NON_NULL)
	private String strategcPlanId;
	
	/** The name of the new workflow. */
	@JsonInclude(Include.NON_NULL)
	private String name;

	/** Is the MetaWorkflow created from an updated strategy? */
	@JsonInclude(Include.NON_NULL)
	private String updatedStrategy;
	/**
	 * Gets the strategy id.
	 *
	 * @return the strategy id
	 */
	public String getStrategyId() {
		return strategyId;
	}

	/**
	 * Sets the strategy id.
	 *
	 * @param strategyId the new strategy id
	 */
	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	/**
	 * Gets the strategc plan id.
	 *
	 * @return the strategc plan id
	 */
	public String getStrategcPlanId() {
		return strategcPlanId;
	}

	/**
	 * Sets the strategc plan id.
	 *
	 * @param strategcPlanId the new strategc plan id
	 */
	public void setStrategcPlanId(String strategcPlanId) {
		this.strategcPlanId = strategcPlanId;
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


	public String getUpdatedStrategy() {
		return updatedStrategy;
	}

	public void setUpdatedStrategy(String updateStrategy) {
		this.updatedStrategy = updateStrategy;
	}
}
