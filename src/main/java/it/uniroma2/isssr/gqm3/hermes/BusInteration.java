
package it.uniroma2.isssr.gqm3.hermes;


import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/**
 * <p>Title: BusInteration</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * In questa interfaccia vengono esposti i metodi per interagire
 * con il bus. Con questi metodi si operano delle traduzioni
 * di ciò che esponiamo/richiediamo al bus rispetto a quello
 * che effettivamente dobbiamo esporre/ci viene offerto</p>
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */
@Service("BusInteration")
public interface BusInteration {
	//scrivo una operazione di prova da implementare
	//relativa ad una richiesta del bus..

	//Devo poter ottenere tutte le strategie foglia presenti
	
	/**
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	
	//nel DB gestito dagli intergratori

	ResponseEntity<DTOResponseStrategy> getValidationOps();

	public ResponseEntity<DTOResponseStrategy> getStrategies();

	String alertPhase2WrongStrategy(String strategyId);
	
	public ResponseEntity<DTOResponseStrategy> getStrategiesFree();
}
