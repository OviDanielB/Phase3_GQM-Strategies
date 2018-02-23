package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.uniroma2.isssr.gqm3.model.ontologyPhase2.Ontology;
import it.uniroma2.isssr.gqm3.model.ontologyPhase2.attribute.*;
import it.uniroma2.isssr.gqm3.model.ontologyPhase2.attribute.Attribute;
import it.uniroma2.isssr.gqm3.model.validation.ValidationOp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * This class represents a Measure Task in which there are task id, who is responsible to measure,
 * if the measure is automatic or manual (means field), associated metric and a list of validations id.
 */

@Document
public class MeasureTask {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String _id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String taskId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Metric metric;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String means;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String responsible;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String source;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @DBRef
    private List<ValidationOp> validationIdList;

    /* newly added attributes */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attribute;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Ontology ontology;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String scope;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String time;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean newVersion;

    public Boolean getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(Boolean newVersion) {
        this.newVersion = newVersion;
    }

    public Ontology getOntology() {
        return ontology;
    }

    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<ValidationOp> getValidationIdList() {
        return validationIdList;
    }

    public void setValidationIdList(List<ValidationOp> validationIdList) {
        this.validationIdList = validationIdList;
    }

}