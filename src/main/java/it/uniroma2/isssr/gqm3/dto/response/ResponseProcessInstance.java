
package it.uniroma2.isssr.gqm3.dto.response;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "url",
    "businessKey",
    "suspended",
    "processDefinitionUrl",
    "activityId",
    "tenantId"
})
public class ResponseProcessInstance{

    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("businessKey")
    private String businessKey;
    @JsonProperty("suspended")
    private Boolean suspended;
    @JsonProperty("processDefinitionUrl")
    private String processDefinitionUrl;
    @JsonProperty("activityId")
    private String activityId;
    @JsonProperty("tenantId")
    private Object tenantId;
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
    public String getBusinessKey() {
        return businessKey;
    }

    /**
     * 
     * @param businessKey
     *     The businessKey
     */
    @JsonProperty("businessKey")
    public void setBusinessKey(String businessKey) {
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
     *     The tenantId
     */
    @JsonProperty("tenantId")
    public Object getTenantId() {
        return tenantId;
    }

    /**
     * 
     * @param tenantId
     *     The tenantId
     */
    @JsonProperty("tenantId")
    public void setTenantId(Object tenantId) {
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
