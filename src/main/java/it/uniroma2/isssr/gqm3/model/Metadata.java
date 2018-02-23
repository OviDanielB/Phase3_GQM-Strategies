package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Classe del model del progetto svolto dal gruppo gqm3141
 * @author gqm3141
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "version",
    "tags",
    "creationDate",
    "lastVersionDate",
    "state",
    "releaseNote",
    "elementType"
})
@Document
public class Metadata {
	
	@Id
    @JsonProperty("id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonProperty("version")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String version;
    @JsonProperty("tags")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("creationDate")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String creationDate;
    @JsonProperty("lastVersionDate")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastVersionDate;
    @JsonProperty("state")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String state;
    @JsonProperty("releaseNote")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String releaseNote;
    @JsonProperty("elementType")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String elementType;
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
     *     The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The creationDate
     */
    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate
     *     The creationDate
     */
    @JsonProperty("creationDate")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 
     * @return
     *     The lastVersionDate
     */
    @JsonProperty("lastVersionDate")
    public String getLastVersionDate() {
        return lastVersionDate;
    }

    /**
     * 
     * @param lastVersionDate
     *     The lastVersionDate
     */
    @JsonProperty("lastVersionDate")
    public void setLastVersionDate(String lastVersionDate) {
        this.lastVersionDate = lastVersionDate;
    }

    /**
     * 
     * @return
     *     The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The releaseNote
     */
    @JsonProperty("releaseNote")
    public String getReleaseNote() {
        return releaseNote;
    }

    /**
     * 
     * @param releaseNote
     *     The releaseNote
     */
    @JsonProperty("releaseNote")
    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    /**
     * 
     * @return
     *     The elementType
     */
    @JsonProperty("elementType")
    public String getElementType() {
        return elementType;
    }

    /**
     * 
     * @param elementType
     *     The elementType
     */
    @JsonProperty("elementType")
    public void setElementType(String elementType) {
        this.elementType = elementType;
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
