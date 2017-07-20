package it.uniroma2.isssr.gqm3.model.ontologyPhase2.measurementModel;



/**
 * Created by MacH2o on 12/07/17.
 */

public class DirectPredictive extends MeasurementModel {
    private String baseMeas;
    private String expectedBehavior;

    public String getBaseMeas() {
        return baseMeas;
    }

    public void setBaseMeas(String baseMeas) {
        this.baseMeas = baseMeas;
    }

    public String getExpectedBehavior() {
        return expectedBehavior;
    }

    public void setExpectedBehavior(String expectedBehavior) {
        this.expectedBehavior = expectedBehavior;
    }
}
