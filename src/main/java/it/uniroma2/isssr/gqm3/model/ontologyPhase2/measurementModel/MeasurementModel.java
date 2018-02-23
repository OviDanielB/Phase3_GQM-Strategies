package it.uniroma2.isssr.gqm3.model.ontologyPhase2.measurementModel;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by MacH2o on 10/07/17.
 */

public class MeasurementModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String metricType; // Generic or Base or CoCoMo
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modelType; // Descriptive, Prescriptive or Predictive
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type; //Direct, Indirect


    // TODO remove (contained in extended classes)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expected;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String baseMeas;


    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getBaseMeas() {
        return baseMeas;
    }

    public void setBaseMeas(String baseMeas) {
        this.baseMeas = baseMeas;
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

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MeasurementModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", metricType='" + metricType + '\'' +
                ", modelType='" + modelType + '\'' +
                ", type='" + type + '\'' +
                ", expected='" + expected + '\'' +
                ", baseMeas='" + baseMeas + '\'' +
                '}';
    }
}
