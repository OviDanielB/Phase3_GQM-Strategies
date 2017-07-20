package it.uniroma2.isssr.gqm3.model.ontologyPhase2;



/**
 * Created by MacH2o on 10/07/17.
 */

public class Measurement {
    private String name;
    private String description;
    private MeasurementResult result;

    public Measurement(String name, String description, MeasurementResult result) {
        this.name = name;
        this.description = description;
        this.result = result;
    }

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

    public MeasurementResult getResult() {
        return result;
    }

    public void setResult(MeasurementResult result) {
        this.result = result;
    }
}
