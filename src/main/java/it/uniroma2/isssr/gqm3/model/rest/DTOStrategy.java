package it.uniroma2.isssr.gqm3.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <p>Title: DTOStrategy</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * DTO relativa alla classe Strategy. Quando ricevo il Json (ad es. nella POST)
 * in DTOStrategy salverò i relativi campi
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */


public class DTOStrategy extends DTO {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The name. */
	@JsonInclude(Include.NON_NULL)
	private String name;
	
	/** The description. */
	@JsonInclude(Include.NON_NULL)
	private String description;
	

	@JsonInclude(Include.NON_NULL)
	private String organizationalUnit;
	
	@JsonInclude(Include.NON_NULL)
	private String organizationalUnitId;

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

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	public String getOrganizationalUnit() {
		return organizationalUnit;
	}

	public void setOrganizationalUnit(String organizationalUnit) {
		this.organizationalUnit = organizationalUnit;
	}

	public String getOrganizationalUnitId() {
		return organizationalUnitId;
	}

	public void setOrganizationalUnitId(String organizationalUnitId) {
		this.organizationalUnitId = organizationalUnitId;
	}
	
	
	
}
