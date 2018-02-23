package it.uniroma2.isssr.gqm3.model.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.List;


/**
 * <p>Title: DTOResponseStrategy</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 

 * DTO relativa ad una risposta, riferita ad una richiesta di 
 * un servizio REST. Qui dentro saranno salvati i campi della 
 * risposta dell'interrogazione del DB, oltre ovviamente al
 * messaggio di errore e al messaggio vero e proprio.
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

public class DTOResponseStrategy extends DTO{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The strategies. */
	@JsonInclude(Include.NON_NULL)
	private List<Strategy> strategies;
	
	/** The strategyid. */
	@JsonInclude(Include.NON_NULL)
	private String strategyid;
	
	/** The strategy name. */
	@JsonInclude(Include.NON_NULL)
	private String strategyName;
	
	/** The strategy description. */
	@JsonInclude(Include.NON_NULL)
	private String strategyDescription;
	
	/**
	 * Instantiates a new DTO response.
	 *
	 * @param error the error
	 * @param message the message
	 */
	public DTOResponseStrategy(String error, String message){
		this.error = error;
		this.message = message;
	}
	
	/**
	 * Instantiates a new DTO response.
	 */
	public DTOResponseStrategy(){
			
		}

	/**
	 * Gets the strategies.
	 *
	 * @return the strategies
	 */
	public List<Strategy> getStrategies() {
		return strategies;
	}

	/**
	 * Sets the strategies.
	 *
	 * @param strategies the new strategies
	 */
	public void setStrategies(List<Strategy> strategies) {
		this.strategies = strategies;
	}

	/**
	 * Gets the strategyid.
	 *
	 * @return the strategyid
	 */
	public String getStrategyid() {
		return strategyid;
	}

	/**
	 * Sets the strategyid.
	 *
	 * @param strategyid the new strategyid
	 */
	public void setStrategyid(String strategyid) {
		this.strategyid = strategyid;
	}

	/**
	 * Gets the strategy name.
	 *
	 * @return the strategy name
	 */
	public String getStrategyName() {
		return strategyName;
	}

	/**
	 * Sets the strategy name.
	 *
	 * @param strategyName the new strategy name
	 */
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	/**
	 * Gets the strategy description.
	 *
	 * @return the strategy description
	 */
	public String getStrategyDescription() {
		return strategyDescription;
	}

	/**
	 * Sets the strategy description.
	 *
	 * @param strategyDescription the new strategy description
	 */
	public void setStrategyDescription(String strategyDescription) {
		this.strategyDescription = strategyDescription;
	}
}

