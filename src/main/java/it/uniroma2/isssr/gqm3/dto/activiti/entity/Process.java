
package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "xmlRowNumber",
    "xmlColumnNumber",
    "extensionElements",
    "name",
    "executable",
    "documentation",
    "ioSpecification",
    "executionListeners",
    "lanes",
    "dataObjects",
    "candidateStarterUsers",
    "candidateStarterGroups",
    "eventListeners",
    "artifacts",
    "flowElements"
})
public class Process implements ActivitiEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("xmlRowNumber")
    private Integer xmlRowNumber;
    @JsonProperty("xmlColumnNumber")
    private Integer xmlColumnNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("executable")
    private Boolean executable;
    @JsonProperty("documentation")
    private String documentation;
    @JsonProperty("ioSpecification")
    private Object ioSpecification;
    @JsonProperty("executionListeners")
    private List<Object> executionListeners = new ArrayList<Object>();
    @JsonProperty("lanes")
    private List<Object> lanes = new ArrayList<Object>();
    @JsonProperty("dataObjects")
    private List<Object> dataObjects = new ArrayList<Object>();
    @JsonProperty("candidateStarterUsers")
    private List<Object> candidateStarterUsers = new ArrayList<Object>();
    @JsonProperty("candidateStarterGroups")
    private List<Object> candidateStarterGroups = new ArrayList<Object>();
    @JsonProperty("eventListeners")
    private List<Object> eventListeners = new ArrayList<Object>();
    @JsonProperty("artifacts")
    private List<Object> artifacts = new ArrayList<Object>();
    @JsonProperty("flowElements")
    private List<FlowElement> flowElements = new ArrayList<FlowElement>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The xmlRowNumber
     */
    @JsonProperty("xmlRowNumber")
    public Integer getXmlRowNumber() {
        return xmlRowNumber;
    }

    /**
     * 
     * @param xmlRowNumber
     *     The xmlRowNumber
     */
    @JsonProperty("xmlRowNumber")
    public void setXmlRowNumber(Integer xmlRowNumber) {
        this.xmlRowNumber = xmlRowNumber;
    }

    /**
     * 
     * @return
     *     The xmlColumnNumber
     */
    @JsonProperty("xmlColumnNumber")
    public Integer getXmlColumnNumber() {
        return xmlColumnNumber;
    }

    /**
     * 
     * @param xmlColumnNumber
     *     The xmlColumnNumber
     */
    @JsonProperty("xmlColumnNumber")
    public void setXmlColumnNumber(Integer xmlColumnNumber) {
        this.xmlColumnNumber = xmlColumnNumber;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The executable
     */
    @JsonProperty("executable")
    public Boolean getExecutable() {
        return executable;
    }

    /**
     * 
     * @param executable
     *     The executable
     */
    @JsonProperty("executable")
    public void setExecutable(Boolean executable) {
        this.executable = executable;
    }

    /**
     * 
     * @return
     *     The documentation
     */
    @JsonProperty("documentation")
    public String getDocumentation() {
        return documentation;
    }

    /**
     * 
     * @param documentation
     *     The documentation
     */
    @JsonProperty("documentation")
    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    /**
     * 
     * @return
     *     The ioSpecification
     */
    @JsonProperty("ioSpecification")
    public Object getIoSpecification() {
        return ioSpecification;
    }

    /**
     * 
     * @param ioSpecification
     *     The ioSpecification
     */
    @JsonProperty("ioSpecification")
    public void setIoSpecification(Object ioSpecification) {
        this.ioSpecification = ioSpecification;
    }

    /**
     * 
     * @return
     *     The executionListeners
     */
    @JsonProperty("executionListeners")
    public List<Object> getExecutionListeners() {
        return executionListeners;
    }

    /**
     * 
     * @param executionListeners
     *     The executionListeners
     */
    @JsonProperty("executionListeners")
    public void setExecutionListeners(List<Object> executionListeners) {
        this.executionListeners = executionListeners;
    }

    /**
     * 
     * @return
     *     The lanes
     */
    @JsonProperty("lanes")
    public List<Object> getLanes() {
        return lanes;
    }

    /**
     * 
     * @param lanes
     *     The lanes
     */
    @JsonProperty("lanes")
    public void setLanes(List<Object> lanes) {
        this.lanes = lanes;
    }

    /**
     * 
     * @return
     *     The dataObjects
     */
    @JsonProperty("dataObjects")
    public List<Object> getDataObjects() {
        return dataObjects;
    }

    /**
     * 
     * @param dataObjects
     *     The dataObjects
     */
    @JsonProperty("dataObjects")
    public void setDataObjects(List<Object> dataObjects) {
        this.dataObjects = dataObjects;
    }

    /**
     * 
     * @return
     *     The candidateStarterUsers
     */
    @JsonProperty("candidateStarterUsers")
    public List<Object> getCandidateStarterUsers() {
        return candidateStarterUsers;
    }

    /**
     * 
     * @param candidateStarterUsers
     *     The candidateStarterUsers
     */
    @JsonProperty("candidateStarterUsers")
    public void setCandidateStarterUsers(List<Object> candidateStarterUsers) {
        this.candidateStarterUsers = candidateStarterUsers;
    }

    /**
     * 
     * @return
     *     The candidateStarterGroups
     */
    @JsonProperty("candidateStarterGroups")
    public List<Object> getCandidateStarterGroups() {
        return candidateStarterGroups;
    }

    /**
     * 
     * @param candidateStarterGroups
     *     The candidateStarterGroups
     */
    @JsonProperty("candidateStarterGroups")
    public void setCandidateStarterGroups(List<Object> candidateStarterGroups) {
        this.candidateStarterGroups = candidateStarterGroups;
    }

    /**
     * 
     * @return
     *     The eventListeners
     */
    @JsonProperty("eventListeners")
    public List<Object> getEventListeners() {
        return eventListeners;
    }

    /**
     * 
     * @param eventListeners
     *     The eventListeners
     */
    @JsonProperty("eventListeners")
    public void setEventListeners(List<Object> eventListeners) {
        this.eventListeners = eventListeners;
    }

    /**
     * 
     * @return
     *     The artifacts
     */
    @JsonProperty("artifacts")
    public List<Object> getArtifacts() {
        return artifacts;
    }

    /**
     * 
     * @param artifacts
     *     The artifacts
     */
    @JsonProperty("artifacts")
    public void setArtifacts(List<Object> artifacts) {
        this.artifacts = artifacts;
    }

    /**
     * 
     * @return
     *     The flowElements
     */
    @JsonProperty("flowElements")
    public List<FlowElement> getFlowElements() {
        return flowElements;
    }

    /**
     * 
     * @param flowElements
     *     The flowElements
     */
    @JsonProperty("flowElements")
    public void setFlowElements(List<FlowElement> flowElements) {
        this.flowElements = flowElements;
    }

}
