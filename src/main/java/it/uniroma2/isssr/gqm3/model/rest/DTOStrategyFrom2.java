package it.uniroma2.isssr.gqm3.model.rest;

public class DTOStrategyFrom2 extends DTO {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String description;

	private String organizationalUnitId;

	private String organizationalUnit;

	private int revisited;

	private int version;

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

	public String getOrganizationalUnit() {
		return organizationalUnit;
	}

	public void setOrganizationalUnit(String organizationalUnitName) {
		this.organizationalUnit = organizationalUnitName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
