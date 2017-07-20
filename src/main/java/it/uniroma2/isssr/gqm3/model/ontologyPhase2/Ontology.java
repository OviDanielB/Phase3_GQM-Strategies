package it.uniroma2.isssr.gqm3.model.ontologyPhase2;


import com.fasterxml.jackson.annotation.JsonInclude;
import it.uniroma2.isssr.gqm3.model.ontologyPhase2.attribute.*;
import it.uniroma2.isssr.gqm3.model.ontologyPhase2.measurementModel.MeasurementModel;
import it.uniroma2.isssr.gqm3.model.ontologyPhase2.scale.Scale;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacH2o on 10/07/17.
 */

@Document
public class Ontology<T, X> extends Element {


    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer busVersion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String version;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String secretToken;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String creationDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastVersionDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tags;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String releaseNote;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String entityType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private X measurementModel;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Internal> internals;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<External>  externals;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T scale;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Unit unit;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MeasurementGoal goal;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Measurement> measurements;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String creatorId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String metricatorId;

    public void setCoCoMoParams() {
        this.unit = new Unit();
        this.unit.setName("KLOC");
        this.internals = new ArrayList<Internal>();
        Internal internal = new Internal();
        internal.setName("Software Project Type");
        internal.setDescription("Organic");
        this.internals.add(internal);

        String[] cocomoAttributes = {"Required software reliability", "Size of application database",
                "Complexity of the product", "Hardware attributes", "Run-time performance constraints",
                "Memory constraints", "Volatility of the virtual machine environment", "Required turnabout time",
                "Personnel attributes", "Analyst capability", "Software engineering capability",
                "Applications experience", "Virtual machine experience", "Programming language experience",
                "Project attributes", "Use of software tools", "Application of software engineering methods",
                "Required development schedule"};
        for (String attribute : cocomoAttributes) {
            internal = new Internal();
            internal.setName(attribute);
            internal.setDescription("Nominal");
            internal.setType(null);
            this.internals.add(internal);
        }
    }

    public String getMetricatorId() {
        return metricatorId;
    }

    public void setMetricatorId(String metricator) {
        this.metricatorId = metricator;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public String getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Integer getBusVersion() {
        return busVersion;
    }

    public void setBusVersion(Integer busVersion) {
        this.busVersion = busVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastVersionDate() {
        return lastVersionDate;
    }

    public void setLastVersionDate(String lastVersionDate) {
        this.lastVersionDate = lastVersionDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public X getMeasurementModel() {
        return measurementModel;
    }

    public void setMeasurementModel(X measurementModel) {
        this.measurementModel = measurementModel;
    }

    public ArrayList<Internal> getInternals() {
        return internals;
    }

    public void setInternals(ArrayList<Internal> internals) {
        this.internals = internals;
    }

    public ArrayList<External> getExternals() {
        return externals;
    }

    public void setExternals(ArrayList<External> externals) {
        this.externals = externals;
    }

    public T getScale() {
        return scale;
    }

    public void setScale(T scale) {
        this.scale = scale;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public MeasurementGoal getGoal() {
        return goal;
    }

    public void setGoal(MeasurementGoal goal) {
        this.goal = goal;
    }

    public ArrayList<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(ArrayList<Measurement> measurements) {
        this.measurements = measurements;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "Ontology{" +
                "id=" + id +
                ", measurementModel=" + measurementModel +
                ", internals=" + internals +
                ", externals=" + externals +
                ", scale=" + scale +
                ", unit=" + unit +
                ", goal=" + goal +
                ", measurements=" + measurements +
                ", creatorId='" + creatorId + '\'' +
                '}';
    }
}
