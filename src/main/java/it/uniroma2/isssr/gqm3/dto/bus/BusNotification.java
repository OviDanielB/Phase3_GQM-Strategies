
package it.uniroma2.isssr.gqm3.dto.bus;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "tag",
    "operation",
    "phase",
    "data",
    "id",
    "version",
    "destinationAdress"
})
public class BusNotification {

    @JsonProperty("tag")
    private String tag;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("phase")
    private String phase;
    @JsonProperty("data")
    private String data;
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("version")
    private String version;
    @JsonProperty("destinationAdress")
    private String destinationAdress;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The tag
     */
    @JsonProperty("tag")
    public String getTag() {
        return tag;
    }

    /**
     * 
     * @param tag
     *     The tag
     */
    @JsonProperty("tag")
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 
     * @return
     *     The operation
     */
    @JsonProperty("operation")
    public String getOperation() {
        return operation;
    }

    /**
     * 
     * @param operation
     *     The operation
     */
    @JsonProperty("operation")
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * 
     * @return
     *     The phase
     */
    @JsonProperty("phase")
    public String getPhase() {
        return phase;
    }

    /**
     * 
     * @param phase
     *     The phase
     */
    @JsonProperty("phase")
    public void setPhase(String phase) {
        this.phase = phase;
    }

    /**
     * 
     * @return
     *     The data
     */
    @JsonProperty("data")
    public String getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The destinationAdress
     */
    @JsonProperty("destinationAdress")
    public String getDestinationAdress() {
        return destinationAdress;
    }

    /**
     * 
     * @param destinationAdress
     *     The destinationAdress
     */
    @JsonProperty("destinationAdress")
    public void setDestinationAdress(String destinationAdress) {
        this.destinationAdress = destinationAdress;
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
