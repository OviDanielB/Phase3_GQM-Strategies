
package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "url",
    "parentId",
    "parentUrl",
    "processInstanceId",
    "processInstanceUrl",
    "suspended",
    "activityId",
    "tenantId"
})
public class Execution implements ActivitiEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("parentId")
    private String parentId;
    @JsonProperty("parentUrl")
    private String parentUrl;
    @JsonProperty("processInstanceId")
    private String processInstanceId;
    @JsonProperty("processInstanceUrl")
    private String processInstanceUrl;
    @JsonProperty("suspended")
    private Boolean suspended;
    @JsonProperty("activityId")
    private String activityId;
    @JsonProperty("tenantId")
    private String tenantId;
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
     *     The parentId
     */
    @JsonProperty("parentId")
    public String getParentId() {
        return parentId;
    }

    /**
     * 
     * @param parentId
     *     The parentId
     */
    @JsonProperty("parentId")
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return
     *     The parentUrl
     */
    @JsonProperty("parentUrl")
    public String getParentUrl() {
        return parentUrl;
    }

    /**
     * 
     * @param parentUrl
     *     The parentUrl
     */
    @JsonProperty("parentUrl")
    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }

    /**
     * 
     * @return
     *     The processInstanceId
     */
    @JsonProperty("processInstanceId")
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    /**
     * 
     * @param processInstanceId
     *     The processInstanceId
     */
    @JsonProperty("processInstanceId")
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    /**
     * 
     * @return
     *     The processInstanceUrl
     */
    @JsonProperty("processInstanceUrl")
    public String getProcessInstanceUrl() {
        return processInstanceUrl;
    }

    /**
     * 
     * @param processInstanceUrl
     *     The processInstanceUrl
     */
    @JsonProperty("processInstanceUrl")
    public void setProcessInstanceUrl(String processInstanceUrl) {
        this.processInstanceUrl = processInstanceUrl;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
