
package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "name",
    "key",
    "tenantId",
    "metaInfo"
})
public class PostModel{

    @JsonProperty("name")
    private String name;
    @JsonProperty("key")
    private String key;
    @JsonProperty("tenantId")
    private String tenantId;
    @JsonProperty("metaInfo")
    private String metaInfo;
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
     *     The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
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
     *     The metaInfo
     */
    @JsonProperty("metaInfo")
    public String getMetaInfo() {
        return metaInfo;
    }

    /**
     * 
     * @param metaInfo
     *     The metaInfo
     */
    @JsonProperty("metaInfo")
    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    public void buildMetaInfo(String name){
    	
    	setMetaInfo(
    			"{\"name\":\"" + name + "\","
    					+ "\"revision\":1,"
    					+ "\"description\":\"Workflow " + name + "\"}");
    }

}
