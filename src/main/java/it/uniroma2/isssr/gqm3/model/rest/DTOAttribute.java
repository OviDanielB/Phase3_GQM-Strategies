
package it.uniroma2.isssr.gqm3.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <p>Title: DTOAttribute</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * 
 * <p>Class description:
 * 
 * DTO relativa alla classe Attribute. Quando ricevo il Json (ad es. nella POST)
 * in DTOAttribute salverò i relativi campi
 * 
 * @author Luca Della Gatta
 * @version 1.0
 *
 */
public class DTOAttribute extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	public String getId() {
		return id;
	}

	/**
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	public String getName() {
		return name;
	}

	/**
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	public String getType() {
		return type;
	}

	/**
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Descrizione del metodo
	 * 
	 * @param 
	 * 
	 * @return
	 * 
	 * @throws
	 */
	public void setValue(String value) {
		this.value = value;
	}

	

}
