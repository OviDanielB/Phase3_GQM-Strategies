package it.uniroma2.isssr.gqm3.model.ontologyPhase2.measurementModel;



/**
 * Created by MacH2o on 12/07/17.
 */

public class IndirectPredictive extends MeasurementModel {
    private String derivedMeas;
    private String expectedBehavior;

    public String getDerivedMeas() {
        return derivedMeas;
    }

    public void setDerivedMeas(String derivedMeas) {
        this.derivedMeas = derivedMeas;
    }

    public String getExpectedBehavior() {
        return expectedBehavior;
    }

    public void setExpectedBehavior(String expectedBehavior) {
        this.expectedBehavior = expectedBehavior;
    }
}
