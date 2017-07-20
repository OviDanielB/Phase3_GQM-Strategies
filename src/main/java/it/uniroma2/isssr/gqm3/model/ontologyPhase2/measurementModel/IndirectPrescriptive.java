package it.uniroma2.isssr.gqm3.model.ontologyPhase2.measurementModel;



/**
 * Created by MacH2o on 12/07/17.
 */

public class IndirectPrescriptive extends MeasurementModel {
    private String derivedMeas;
    private String expected;


    public String getDerivedMeas() {
        return derivedMeas;
    }

    public void setDerivedMeas(String derivedMeas) {
        this.derivedMeas = derivedMeas;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
