package it.uniroma2.isssr.gqm3.model.ontologyPhase2;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ovidiudanielbarba on 19/07/2017.
 */
public class DTOOntologyResponsePhase2 {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

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
