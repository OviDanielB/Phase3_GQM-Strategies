
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
    "name",
    "documentation",
    "executionListeners",
    "asynchronous",
    "notExclusive",
    "initiator",
    "formKey",
    "defaultFlow",
    "forCompensation",
    "loopCharacteristics",
    "ioSpecification",
    "dataInputAssociations",
    "dataOutputAssociations",
    "boundaryEvents",
    "failedJobRetryTimeCycleValue",
    "mapExceptions",
    "assignee",
    "owner",
    "priority",
    "dueDate",
    "category",
    "extensionId",
    "candidateUsers",
    "candidateGroups",
    "taskListeners",
    "skipExpression",
    "customProperties",
    "extended",
    "conditionExpression",
    "sourceRef",
    "targetRef"
})
public class FlowElement  implements ActivitiEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("xmlRowNumber")
    private Integer xmlRowNumber;
    @JsonProperty("xmlColumnNumber")
    private Integer xmlColumnNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("documentation")
    private Object documentation;
    @JsonProperty("executionListeners")
    private List<Object> executionListeners = new ArrayList<Object>();
    @JsonProperty("asynchronous")
    private Boolean asynchronous;
    @JsonProperty("notExclusive")
    private Boolean notExclusive;
    @JsonProperty("initiator")
    private String initiator;
    @JsonProperty("formKey")
    private Object formKey;
    @JsonProperty("defaultFlow")
    private Object defaultFlow;
    @JsonProperty("forCompensation")
    private Boolean forCompensation;
    @JsonProperty("loopCharacteristics")
    private Object loopCharacteristics;
    @JsonProperty("ioSpecification")
    private Object ioSpecification;
    @JsonProperty("dataInputAssociations")
    private List<Object> dataInputAssociations = new ArrayList<Object>();
    @JsonProperty("dataOutputAssociations")
    private List<Object> dataOutputAssociations = new ArrayList<Object>();
    @JsonProperty("boundaryEvents")
    private List<Object> boundaryEvents = new ArrayList<Object>();
    @JsonProperty("failedJobRetryTimeCycleValue")
    private Object failedJobRetryTimeCycleValue;
    @JsonProperty("mapExceptions")
    private List<Object> mapExceptions = new ArrayList<Object>();
    @JsonProperty("assignee")
    private Object assignee;
    @JsonProperty("owner")
    private Object owner;
    @JsonProperty("priority")
    private Object priority;
    @JsonProperty("dueDate")
    private Object dueDate;
    @JsonProperty("category")
    private Object category;
    @JsonProperty("extensionId")
    private Object extensionId;
    @JsonProperty("candidateUsers")
    private List<Object> candidateUsers = new ArrayList<Object>();
    @JsonProperty("candidateGroups")
    private List<String> candidateGroups = new ArrayList<String>();
    @JsonProperty("taskListeners")
    private List<Object> taskListeners = new ArrayList<Object>();
    @JsonProperty("skipExpression")
    private Object skipExpression;
    @JsonProperty("customProperties")
    private List<Object> customProperties = new ArrayList<Object>();
    @JsonProperty("extended")
    private Boolean extended;
    @JsonProperty("conditionExpression")
    private Object conditionExpression;
    @JsonProperty("sourceRef")
    private String sourceRef;
    @JsonProperty("targetRef")
    private String targetRef;

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
     *     The documentation
     */
    @JsonProperty("documentation")
    public Object getDocumentation() {
        return documentation;
    }

    /**
     * 
     * @param documentation
     *     The documentation
     */
    @JsonProperty("documentation")
    public void setDocumentation(Object documentation) {
        this.documentation = documentation;
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
     *     The asynchronous
     */
    @JsonProperty("asynchronous")
    public Boolean getAsynchronous() {
        return asynchronous;
    }

    /**
     * 
     * @param asynchronous
     *     The asynchronous
     */
    @JsonProperty("asynchronous")
    public void setAsynchronous(Boolean asynchronous) {
        this.asynchronous = asynchronous;
    }

    /**
     * 
     * @return
     *     The notExclusive
     */
    @JsonProperty("notExclusive")
    public Boolean getNotExclusive() {
        return notExclusive;
    }

    /**
     * 
     * @param notExclusive
     *     The notExclusive
     */
    @JsonProperty("notExclusive")
    public void setNotExclusive(Boolean notExclusive) {
        this.notExclusive = notExclusive;
    }

    /**
     * 
     * @return
     *     The initiator
     */
    @JsonProperty("initiator")
    public String getInitiator() {
        return initiator;
    }

    /**
     * 
     * @param initiator
     *     The initiator
     */
    @JsonProperty("initiator")
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    /**
     * 
     * @return
     *     The formKey
     */
    @JsonProperty("formKey")
    public Object getFormKey() {
        return formKey;
    }

    /**
     * 
     * @param formKey
     *     The formKey
     */
    @JsonProperty("formKey")
    public void setFormKey(Object formKey) {
        this.formKey = formKey;
    }

    /**
     * 
     * @return
     *     The defaultFlow
     */
    @JsonProperty("defaultFlow")
    public Object getDefaultFlow() {
        return defaultFlow;
    }

    /**
     * 
     * @param defaultFlow
     *     The defaultFlow
     */
    @JsonProperty("defaultFlow")
    public void setDefaultFlow(Object defaultFlow) {
        this.defaultFlow = defaultFlow;
    }

    /**
     * 
     * @return
     *     The forCompensation
     */
    @JsonProperty("forCompensation")
    public Boolean getForCompensation() {
        return forCompensation;
    }

    /**
     * 
     * @param forCompensation
     *     The forCompensation
     */
    @JsonProperty("forCompensation")
    public void setForCompensation(Boolean forCompensation) {
        this.forCompensation = forCompensation;
    }

    /**
     * 
     * @return
     *     The loopCharacteristics
     */
    @JsonProperty("loopCharacteristics")
    public Object getLoopCharacteristics() {
        return loopCharacteristics;
    }

    /**
     * 
     * @param loopCharacteristics
     *     The loopCharacteristics
     */
    @JsonProperty("loopCharacteristics")
    public void setLoopCharacteristics(Object loopCharacteristics) {
        this.loopCharacteristics = loopCharacteristics;
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
     *     The dataInputAssociations
     */
    @JsonProperty("dataInputAssociations")
    public List<Object> getDataInputAssociations() {
        return dataInputAssociations;
    }

    /**
     * 
     * @param dataInputAssociations
     *     The dataInputAssociations
     */
    @JsonProperty("dataInputAssociations")
    public void setDataInputAssociations(List<Object> dataInputAssociations) {
        this.dataInputAssociations = dataInputAssociations;
    }

    /**
     * 
     * @return
     *     The dataOutputAssociations
     */
    @JsonProperty("dataOutputAssociations")
    public List<Object> getDataOutputAssociations() {
        return dataOutputAssociations;
    }

    /**
     * 
     * @param dataOutputAssociations
     *     The dataOutputAssociations
     */
    @JsonProperty("dataOutputAssociations")
    public void setDataOutputAssociations(List<Object> dataOutputAssociations) {
        this.dataOutputAssociations = dataOutputAssociations;
    }

    /**
     * 
     * @return
     *     The boundaryEvents
     */
    @JsonProperty("boundaryEvents")
    public List<Object> getBoundaryEvents() {
        return boundaryEvents;
    }

    /**
     * 
     * @param boundaryEvents
     *     The boundaryEvents
     */
    @JsonProperty("boundaryEvents")
    public void setBoundaryEvents(List<Object> boundaryEvents) {
        this.boundaryEvents = boundaryEvents;
    }

    /**
     * 
     * @return
     *     The failedJobRetryTimeCycleValue
     */
    @JsonProperty("failedJobRetryTimeCycleValue")
    public Object getFailedJobRetryTimeCycleValue() {
        return failedJobRetryTimeCycleValue;
    }

    /**
     * 
     * @param failedJobRetryTimeCycleValue
     *     The failedJobRetryTimeCycleValue
     */
    @JsonProperty("failedJobRetryTimeCycleValue")
    public void setFailedJobRetryTimeCycleValue(Object failedJobRetryTimeCycleValue) {
        this.failedJobRetryTimeCycleValue = failedJobRetryTimeCycleValue;
    }

    /**
     * 
     * @return
     *     The mapExceptions
     */
    @JsonProperty("mapExceptions")
    public List<Object> getMapExceptions() {
        return mapExceptions;
    }

    /**
     * 
     * @param mapExceptions
     *     The mapExceptions
     */
    @JsonProperty("mapExceptions")
    public void setMapExceptions(List<Object> mapExceptions) {
        this.mapExceptions = mapExceptions;
    }

    /**
     * 
     * @return
     *     The assignee
     */
    @JsonProperty("assignee")
    public Object getAssignee() {
        return assignee;
    }

    /**
     * 
     * @param assignee
     *     The assignee
     */
    @JsonProperty("assignee")
    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    /**
     * 
     * @return
     *     The owner
     */
    @JsonProperty("owner")
    public Object getOwner() {
        return owner;
    }

    /**
     * 
     * @param owner
     *     The owner
     */
    @JsonProperty("owner")
    public void setOwner(Object owner) {
        this.owner = owner;
    }

    /**
     * 
     * @return
     *     The priority
     */
    @JsonProperty("priority")
    public Object getPriority() {
        return priority;
    }

    /**
     * 
     * @param priority
     *     The priority
     */
    @JsonProperty("priority")
    public void setPriority(Object priority) {
        this.priority = priority;
    }

    /**
     * 
     * @return
     *     The dueDate
     */
    @JsonProperty("dueDate")
    public Object getDueDate() {
        return dueDate;
    }

    /**
     * 
     * @param dueDate
     *     The dueDate
     */
    @JsonProperty("dueDate")
    public void setDueDate(Object dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * 
     * @return
     *     The category
     */
    @JsonProperty("category")
    public Object getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    @JsonProperty("category")
    public void setCategory(Object category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The extensionId
     */
    @JsonProperty("extensionId")
    public Object getExtensionId() {
        return extensionId;
    }

    /**
     * 
     * @param extensionId
     *     The extensionId
     */
    @JsonProperty("extensionId")
    public void setExtensionId(Object extensionId) {
        this.extensionId = extensionId;
    }

    /**
     * 
     * @return
     *     The candidateUsers
     */
    @JsonProperty("candidateUsers")
    public List<Object> getCandidateUsers() {
        return candidateUsers;
    }

    /**
     * 
     * @param candidateUsers
     *     The candidateUsers
     */
    @JsonProperty("candidateUsers")
    public void setCandidateUsers(List<Object> candidateUsers) {
        this.candidateUsers = candidateUsers;
    }

    /**
     * 
     * @return
     *     The candidateGroups
     */
    @JsonProperty("candidateGroups")
    public List<String> getCandidateGroups() {
        return candidateGroups;
    }

    /**
     * 
     * @param candidateGroups
     *     The candidateGroups
     */
    @JsonProperty("candidateGroups")
    public void setCandidateGroups(List<String> candidateGroups) {
        this.candidateGroups = candidateGroups;
    }

    /**
     * 
     * @return
     *     The taskListeners
     */
    @JsonProperty("taskListeners")
    public List<Object> getTaskListeners() {
        return taskListeners;
    }

    /**
     * 
     * @param taskListeners
     *     The taskListeners
     */
    @JsonProperty("taskListeners")
    public void setTaskListeners(List<Object> taskListeners) {
        this.taskListeners = taskListeners;
    }

    /**
     * 
     * @return
     *     The skipExpression
     */
    @JsonProperty("skipExpression")
    public Object getSkipExpression() {
        return skipExpression;
    }

    /**
     * 
     * @param skipExpression
     *     The skipExpression
     */
    @JsonProperty("skipExpression")
    public void setSkipExpression(Object skipExpression) {
        this.skipExpression = skipExpression;
    }

    /**
     * 
     * @return
     *     The customProperties
     */
    @JsonProperty("customProperties")
    public List<Object> getCustomProperties() {
        return customProperties;
    }

    /**
     * 
     * @param customProperties
     *     The customProperties
     */
    @JsonProperty("customProperties")
    public void setCustomProperties(List<Object> customProperties) {
        this.customProperties = customProperties;
    }

    /**
     * 
     * @return
     *     The extended
     */
    @JsonProperty("extended")
    public Boolean getExtended() {
        return extended;
    }

    /**
     * 
     * @param extended
     *     The extended
     */
    @JsonProperty("extended")
    public void setExtended(Boolean extended) {
        this.extended = extended;
    }

    /**
     * 
     * @return
     *     The conditionExpression
     */
    @JsonProperty("conditionExpression")
    public Object getConditionExpression() {
        return conditionExpression;
    }

    /**
     * 
     * @param conditionExpression
     *     The conditionExpression
     */
    @JsonProperty("conditionExpression")
    public void setConditionExpression(Object conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    /**
     * 
     * @return
     *     The sourceRef
     */
    @JsonProperty("sourceRef")
    public String getSourceRef() {
        return sourceRef;
    }

    /**
     * 
     * @param sourceRef
     *     The sourceRef
     */
    @JsonProperty("sourceRef")
    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    /**
     * 
     * @return
     *     The targetRef
     */
    @JsonProperty("targetRef")
    public String getTargetRef() {
        return targetRef;
    }

    /**
     * 
     * @param targetRef
     *     The targetRef
     */
    @JsonProperty("targetRef")
    public void setTargetRef(String targetRef) {
        this.targetRef = targetRef;
    }
}
