package it.uniroma2.isssr.gqm3.model.ontologyPhase2;

/**
 * Created by MacH2o on 10/07/17.
 */

public class Unit {
    private String name;
    private String description;

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

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
