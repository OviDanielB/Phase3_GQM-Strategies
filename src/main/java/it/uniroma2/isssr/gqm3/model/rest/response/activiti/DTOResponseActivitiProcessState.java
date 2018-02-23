package it.uniroma2.isssr.gqm3.model.rest.response.activiti;

import it.uniroma2.isssr.gqm3.model.rest.DTO;

/**
 * <p>Title: DTOResponseActivitiProcess</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * Oggetto che rappresenta la risposta di una chiamata Rest,
 * ovvero viene restituito nelle ResponseEntity
 * (ad esempio quando si fa una get alla nostra applicazione)
 * 
 * Estende DTO che a sua volta implementa Serializable.
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

public class DTOResponseActivitiProcessState extends DTO{
	private static final long serialVersionUID = 1L;
	
	private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}