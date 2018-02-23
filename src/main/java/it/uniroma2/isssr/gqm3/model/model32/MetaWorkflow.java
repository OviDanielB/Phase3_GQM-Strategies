package it.uniroma2.isssr.gqm3.model.model32;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>Title: MetaWorkflow</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * <p>Class description:
 * 
 * The Class MetaWorkflow represent 
 * a metaworkflow that is linked uniquely to a business workflow.
 * 
 * 
 * @author Daniele Capri
 * @version 1.0
 *
 */
@Document
public class MetaWorkflow {

	/** The DB's id. */
	@Id
	private String id;
	
	/** The id of the meta worflow. */
	private String idMeta;
	
	/** The id of the business workflow. */
	private String idBusiness;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new metaworkflow.
	 */
	public MetaWorkflow() {
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
	 * Gets the id meta.
	 *
	 * @return the id meta
	 */
	public String getIdMeta() {
		return idMeta;
	}



	/**
	 * Sets the id meta.
	 *
	 * @param idMeta the new id meta
	 */
	public void setIdMeta(String idMeta) {
		this.idMeta = idMeta;
	}



	/**
	 * Gets the id business.
	 *
	 * @return the id business
	 */
	public String getIdBusiness() {
		return idBusiness;
	}



	/**
	 * Sets the id business.
	 *
	 * @param idBusiness the new id business
	 */
	public void setIdBusiness(String idBusiness) {
		this.idBusiness = idBusiness;
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

	
	

}
