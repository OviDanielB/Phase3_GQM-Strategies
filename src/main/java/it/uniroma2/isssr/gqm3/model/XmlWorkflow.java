
package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
	"name",
    "xml",
    "encoded"
})
public class XmlWorkflow {

	@JsonProperty("name")
    private String name;
	@JsonProperty("xml")
    private String xml;
    @JsonProperty("encoded")
    private String encoded;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The xml
     */
    @JsonProperty("xml")
    public String getXml() {
        return xml;
    }

    /**
     * 
     * @param xml
     *     The xml
     */
    @JsonProperty("xml")
    public void setXml(String xml) {
        this.xml = xml;
    }

    /**
     * 
     * @return
     *     The encoded
     */
    @JsonProperty("encoded")
    public String getEncoded() {
        return encoded;
    }

    /**
     * 
     * @param encoded
     *     The encoded
     */
    @JsonProperty("encoded")
    public void setEncoded(String encoded) {
        this.encoded = encoded;
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
