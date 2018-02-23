package it.uniroma2.isssr.gqm3.model.rest;

/**
 * @author emanuele
 */
public class OrgUnitDTO {

    private long id;
    private String name;
    private String description;

    public OrgUnitDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
