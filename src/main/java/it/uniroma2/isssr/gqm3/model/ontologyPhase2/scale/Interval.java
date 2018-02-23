package it.uniroma2.isssr.gqm3.model.ontologyPhase2.scale;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ovidiudanielbarba on 19/07/2017.
 */
public class Interval extends Ordinal {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String range;


    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
