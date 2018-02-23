
package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "url",
    "businessKey",
    "suspended",
    "ended",
    "processDefinitionId",
    "processDefinitionUrl",
    "processDefinitionKey",
    "activityId",
    "variables",
    "tenantId",
    "completed"
})
public class ProcessInstance implements ActivitiEntity{

    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("businessKey")
    private Object businessKey;
    @JsonProperty("suspended")
    private Boolean suspended;
    @JsonProperty("ended")
    private Boolean ended;
    @JsonProperty("processDefinitionId")
    private String processDefinitionId;
    @JsonProperty("processDefinitionUrl")
    private String processDefinitionUrl;
    @JsonProperty("processDefinitionKey")
    private String processDefinitionKey;
    @JsonProperty("activityId")
    private String activityId;
    @JsonProperty("variables")
    private List<Object> variables = new ArrayList<Object>();
    @JsonProperty("tenantId")
    private String tenantId;
    @JsonProperty("completed")
    private Boolean completed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The businessKey
     */
    @JsonProperty("businessKey")
    public Object getBusinessKey() {
        return businessKey;
    }

    /**
     * 
     * @param businessKey
     *     The businessKey
     */
    @JsonProperty("businessKey")
    public void setBusinessKey(Object businessKey) {
        this.businessKey = businessKey;
    }

    /**
     * 
     * @return
     *     The suspended
     */
    @JsonProperty("suspended")
    public Boolean getSuspended() {
        return suspended;
    }

    /**
     * 
     * @param suspended
     *     The suspended
     */
    @JsonProperty("suspended")
    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    /**
     * 
     * @return
     *     The ended
     */
    @JsonProperty("ended")
    public Boolean getEnded() {
        return ended;
    }

    /**
     * 
     * @param ended
     *     The ended
     */
    @JsonProperty("ended")
    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    /**
     * 
     * @return
     *     The processDefinitionId
     */
    @JsonProperty("processDefinitionId")
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    /**
     * 
     * @param processDefinitionId
     *     The processDefinitionId
     */
    @JsonProperty("processDefinitionId")
    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /**
     * 
     * @return
     *     The processDefinitionUrl
     */
    @JsonProperty("processDefinitionUrl")
    public String getProcessDefinitionUrl() {
        return processDefinitionUrl;
    }

    /**
     * 
     * @param processDefinitionUrl
     *     The processDefinitionUrl
     */
    @JsonProperty("processDefinitionUrl")
    public void setProcessDefinitionUrl(String processDefinitionUrl) {
        this.processDefinitionUrl = processDefinitionUrl;
    }

    /**
     * 
     * @return
     *     The activityId
     */
    @JsonProperty("activityId")
    public String getActivityId() {
        return activityId;
    }

    /**
     * 
     * @param activityId
     *     The activityId
     */
    @JsonProperty("activityId")
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * 
     * @return
     *     The variables
     */
    @JsonProperty("variables")
    public List<Object> getVariables() {
        return variables;
    }

    /**
     * 
     * @param variables
     *     The variables
     */
    @JsonProperty("variables")
    public void setVariables(List<Object> variables) {
        this.variables = variables;
    }

    /**
     * 
     * @return
     *     The tenantId
     */
    @JsonProperty("tenantId")
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 
     * @param tenantId
     *     The tenantId
     */
    @JsonProperty("tenantId")
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 
     * @return
     *     The completed
     */
    @JsonProperty("completed")
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * 
     * @param completed
     *     The completed
     */
    @JsonProperty("completed")
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
