package it.uniroma2.isssr.gqm3.model.ontologyPhase2.measurementModel;


/**
 * Created by MacH2o on 12/07/17.
 */

public class DirectPrescriptive extends MeasurementModel {
    private String baseMeas;
    private String expected;

    public String getBaseMeas() {
        return baseMeas;
    }

    public void setBaseMeas(String baseMeas) {
        this.baseMeas = baseMeas;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
