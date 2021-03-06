package it.uniroma2.isssr.gqm3.model.ontologyPhase2.metric;


/**
 * Created by MacH2o on 11/07/17.
 */

public abstract class GenericEntity {
    protected String name;
    protected String description;
    protected String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
