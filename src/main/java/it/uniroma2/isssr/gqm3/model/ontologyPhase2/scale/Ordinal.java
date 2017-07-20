package it.uniroma2.isssr.gqm3.model.ontologyPhase2.scale;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ovidiudanielbarba on 19/07/2017.
 */
public class Ordinal extends Nominal {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sortedBy;

    public String getSortedBy() {
        return sortedBy;
    }

    public void setSortedBy(String sortedBy) {
        this.sortedBy = sortedBy;
    }
}
