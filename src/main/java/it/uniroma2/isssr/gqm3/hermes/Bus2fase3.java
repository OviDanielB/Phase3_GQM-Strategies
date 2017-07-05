
package it.uniroma2.isssr.gqm3.hermes;

import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.rest.DTOStrategyFrom1;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: Bus2Fase32</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * In questa interfaccia vengono esposti i metodi per interagire
 * con il bus. Con questi metodi di operano le richieste e si 
 * espongono al bus i nostri servizi.</p>
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */
@Service("Bus32Sevice")
public interface Bus2fase3 {
	/**
	 * Gets the strategies.
	 *
	 * @return the strategies
	 */

	ResponseEntity<DTOResponseStrategy> getStrategies();
	
	ArrayList<DTOStrategyFrom1> getStrategiesF1();
	void saveOnBus(String taskId, String processInstanceId);

	/**
	 * Gets the strategies.
	 *
	 * @return the strategies
	 */
	public List<Strategy> getStrategiesList();
	
}
