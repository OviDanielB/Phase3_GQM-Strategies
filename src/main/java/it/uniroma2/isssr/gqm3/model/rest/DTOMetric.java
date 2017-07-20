package it.uniroma2.isssr.gqm3.model.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DTOMetric {

    @JsonProperty("ordered")
    private Boolean ordered;
    @JsonProperty("description")
    private String description;
    @JsonProperty("version")
    private String version;
    @JsonProperty("releaseNote")
    private String releaseNote;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("scaleType")
    private String scaleType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("set")
    private String set;
    @JsonProperty("hasMax")
    private Boolean hasMax;
    @JsonProperty("hasMin")
    private Boolean hasMin;
    @JsonProperty("min")
    private Double min;
    @JsonProperty("max")
    private Double max;




    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getScaleType() {
        return scaleType;
    }

    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public Boolean getHasMax() {
        return hasMax;
    }

    public void setHasMax(Boolean hasMax) {
        this.hasMax = hasMax;
    }

    public Boolean getHasMin() {
        return hasMin;
    }

    public void setHasMin(Boolean hasMin) {
        this.hasMin = hasMin;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
