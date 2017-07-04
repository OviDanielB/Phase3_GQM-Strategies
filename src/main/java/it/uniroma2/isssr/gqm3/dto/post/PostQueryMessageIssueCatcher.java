
package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "messageEventSubscriptionName",
    "processInstanceId"
})
public class PostQueryMessageIssueCatcher {

    @JsonProperty("messageEventSubscriptionName")
    private String messageEventSubscriptionName;
    @JsonProperty("processInstanceId")
    private String processInstanceId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The messageEventSubscriptionName
     */
    @JsonProperty("messageEventSubscriptionName")
    public String getMessageEventSubscriptionName() {
        return messageEventSubscriptionName;
    }

    /**
     * 
     * @param messageEventSubscriptionName
     *     The messageEventSubscriptionName
     */
    @JsonProperty("messageEventSubscriptionName")
    public void setMessageEventSubscriptionName(String messageEventSubscriptionName) {
        this.messageEventSubscriptionName = messageEventSubscriptionName;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
