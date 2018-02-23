
package it.uniroma2.isssr.gqm3.dto.put;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "action",
    "messageName"
})
public class PutMessage {

    @JsonProperty("action")
    private String action;
    @JsonProperty("messageName")
    private String messageName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The action
     */
    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    /**
     * 
     * @param action
     *     The action
     */
    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 
     * @return
     *     The messageName
     */
    @JsonProperty("messageName")
    public String getMessageName() {
        return messageName;
    }

    /**
     * 
     * @param messageName
     *     The messageName
     */
    @JsonProperty("messageName")
    public void setMessageName(String messageName) {
        this.messageName = messageName;
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
