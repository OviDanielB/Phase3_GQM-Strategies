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
 * 
 * <p>Class description:
 * 
 * Classe entity della nostra applicazione, annotata con @Document per dire a Spring
 * che questo è un Documento di MongoDB, da salvare nel DB.
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */
@Document
public class Strategy {
	
	/** The id. */
	@Id
	private String id;
	
	private String idF2;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	private String organizationalunit;
	
	private String organizationalunitId;
	
	int status;
	
	/** The version. */
	private int version;
	
	/** The release. */
	private int release;
	
	/**
	 * Instantiates a new strategy.
	 */
	public Strategy(){}
	
	/**
	 * Instantiates a new strategy.
	 *
	 * @parqam name the name
	 * @param description the description
	 * @param version the version
	 * @param release the release
	 */
	public Strategy(String name, String description, String organizationalUnit, int version, int release){
		this.name = name;
		this.description = description;
		this.organizationalunit=organizationalUnit;
		this.version = version;
		this.release = release;
		
	}
	
	
	
	public Strategy(String name, String description, String organizationalUnit, String organizationalUnitId, int status,
			int version, int release) {
		super();
		this.name = name;
		this.description = description;
		this.organizationalunit = organizationalUnit;
		this.organizationalunitId = organizationalUnitId;
		this.status = status;
		this.version = version;
		this.release = release;
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

	public String getOrganizationalunit() {
		return organizationalunit;
	}

	public void setOrganizationalunit(String organizationalUnit) {
		this.organizationalunit = organizationalUnit;
	}

	public String getOrganizationalunitId() {
		return organizationalunitId;
	}

	public void setOrganizationalunitId(String organizationalUnitId) {
		this.organizationalunitId = organizationalUnitId;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Gets the release.
	 *
	 * @return the release
	 */
	public int getRelease() {
		return release;
	}

	/**
	 * Sets the release.
	 *
	 * @param release the new release
	 */
	public void setRelease(int release) {
		this.release = release;
	}

	public String getIdF2() {
		return idF2;
	}

	public void setIdF2(String idF2) {
		this.idF2 = idF2;
	};
	
	
	
	

}