package it.uniroma2.isssr.gqm3.model.rest;

public class DTOStrategyFrom2 extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String id;
	
	String title;
	
	String description;
	
	String organizationalUnitId;
	
	String organizationalUnitName;
	
	int revisited;
	
	int version;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getOrganizationalUnitId() {
		return organizationalUnitId;
	}
	
	public void setOrganizationalUnitId(String organizationalUnitId) {
		this.organizationalUnitId = organizationalUnitId;
	}
	
	public String getOrganizationalUnitName() {
		return organizationalUnitName;
	}
	
	public void setOrganizationalUnitName(String organizationalUnitName) {
		this.organizationalUnitName = organizationalUnitName;
	}

	public int getRevisited() {
		return revisited;
	}
	
	public void setRevisited(int revisited) {
		this.revisited = revisited;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}
