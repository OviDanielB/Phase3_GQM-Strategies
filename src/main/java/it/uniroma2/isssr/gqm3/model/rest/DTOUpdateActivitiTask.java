package it.uniroma2.isssr.gqm3.model.rest;

/**
 * <p>Title: DTOUpdateActivitiTask</p>
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
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

public class DTOUpdateActivitiTask  extends DTO {
	private static final long serialVersionUID = 1L;
	
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}    
}



