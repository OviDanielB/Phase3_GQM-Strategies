package it.uniroma2.isssr.gqm3.model.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.Attribute;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.ArrayList;

/**
 * <p>Title: DTOResponseAttribute</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * DTO relativa utilizzata per ritornare il risultato di una richiesta REST. Qui
 * dentro saranno salvati i campi della risposta. 
 * 
 * 
 * @author Luca Della Gatta
 * @version 1.0
 *
 */

public class DTOResponseAttribute extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The list of strategic plan. */
	@JsonInclude(Include.NON_NULL)
	private ArrayList<Attribute> attribute;
	
	/** The id. */
	@JsonInclude(Include.NON_NULL)
	private String id;

	/** The name. */
	@JsonInclude(Include.NON_NULL)
	private String name;
	
	/** The type. */
	@JsonInclude(Include.NON_NULL)
	private String type;

	/** The value. */
	@JsonInclude(Include.NON_NULL)
	private String value;

	/**
	 * Instantiates a new DTO response strategic plan.
	 *
	 * @param error
	 *            the error
	 * @param message
	 *            the message
	 */
	public DTOResponseAttribute(String error, String message) {
		this.error = error;
		this.message = message;
	}

	/**
	 * Instantiates a new DTO response strategic plan.
	 */
	public DTOResponseAttribute() {

	}

	/**
	 * Gets the Attributes.
	 *
	 * @return the attributes
	 */
	public ArrayList<Attribute> getAttribute() {
		return attribute;
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attribute the new attribute
	 */
	public void setAttribute(ArrayList<Attribute> attribute) {
		this.attribute = attribute;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
