package it.uniroma2.isssr.gqm3.model.rest.response.activiti;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

/**
 * <p>Title: DTOResponseActivitiUser</p>
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

public class DTOResponseActivitiUser extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private String id;
	
	@JsonInclude(Include.NON_NULL)
	private String firstName;
	
	@JsonInclude(Include.NON_NULL)
	private String lastName;
	
	@JsonInclude(Include.NON_NULL)
	private String url;
	
	@JsonInclude(Include.NON_NULL)
	private String email;
	
	public DTOResponseActivitiUser(String error, String message) {
		this.error = error;
		this.message = message;
	}
	
	public DTOResponseActivitiUser() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
