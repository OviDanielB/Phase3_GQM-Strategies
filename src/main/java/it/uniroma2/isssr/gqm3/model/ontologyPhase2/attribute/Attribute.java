package it.uniroma2.isssr.gqm3.model.ontologyPhase2.attribute;

/**
 * Created by MacH2o on 10/07/17.
 */

public class Attribute<T> {
     String name;
     String description;
     String type; //Internal or External
     T entity;

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

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", entity=" + entity +
                '}';
    }
}
