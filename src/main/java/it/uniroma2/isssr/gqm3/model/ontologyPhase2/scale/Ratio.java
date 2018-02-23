package it.uniroma2.isssr.gqm3.model.ontologyPhase2.scale;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ovidiudanielbarba on 19/07/2017.
 */

public class Ratio extends Interval {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String numerator;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String denominator;

    public String getNumerator() {
        return numerator;
    }

    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }

    public String getDenominator() {
        return denominator;
    }

    public void setDenominator(String denominator) {
        this.denominator = denominator;
    }
}
