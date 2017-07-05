/*
 * @autor Daniele Capri
 */

package it.uniroma2.isssr.gqm3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>Title: ActivitiUserList</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * <p>Class description:
 * 
 * La Classe Attribute rappresenta un attributo che 
 * è possibile aggiungere ad uno strategic plan.
 * 
 * 
 * @author Daniele Capri
 * @version 1.0
 *
 */

// TODO: Auto-generated Javadoc

@Document
public class Attribute {
	/** The id. */
	@Id
	private String id;

	/** The name. */
	private String name;

	/** The type. */
	private String type;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new attribute.
	 */
	public Attribute() {
	}

	/**
	 * Instantiates a new attribute.
	 *
	 * @param name il nome dell'attributo
	 * @param type il tipo
	 * @param value il valore
	 */
	public Attribute(String name, String type, String value) {
		super();
		this.name = name;
		this.type = type;
		this.value = value;
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

}
