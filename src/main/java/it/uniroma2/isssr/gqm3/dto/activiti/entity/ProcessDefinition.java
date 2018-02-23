
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
    "version",
    "key",
    "category",
    "suspended",
    "name",
    "description",
    "deploymentId",
    "deploymentUrl",
    "graphicalNotationDefined",
    "resource",
    "diagramResource",
    "startFormDefined"
})
public class ProcessDefinition implements ActivitiEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("version")
    private Integer version;
    @JsonProperty("key")
    private String key;
    @JsonProperty("category")
    private String category;
    @JsonProperty("suspended")
    private Boolean suspended;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("deploymentId")
    private String deploymentId;
    @JsonProperty("deploymentUrl")
    private String deploymentUrl;
    @JsonProperty("graphicalNotationDefined")
    private Boolean graphicalNotationDefined;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("diagramResource")
    private String diagramResource;
    @JsonProperty("startFormDefined")
    private Boolean startFormDefined;
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
     *     The version
     */
    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(Integer version) {
        this.version = version;
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
     *     The category
     */
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
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
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The deploymentId
     */
    @JsonProperty("deploymentId")
    public String getDeploymentId() {
        return deploymentId;
    }

    /**
     * 
     * @param deploymentId
     *     The deploymentId
     */
    @JsonProperty("deploymentId")
    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    /**
     * 
     * @return
     *     The deploymentUrl
     */
    @JsonProperty("deploymentUrl")
    public String getDeploymentUrl() {
        return deploymentUrl;
    }

    /**
     * 
     * @param deploymentUrl
     *     The deploymentUrl
     */
    @JsonProperty("deploymentUrl")
    public void setDeploymentUrl(String deploymentUrl) {
        this.deploymentUrl = deploymentUrl;
    }

    /**
     * 
     * @return
     *     The graphicalNotationDefined
     */
    @JsonProperty("graphicalNotationDefined")
    public Boolean getGraphicalNotationDefined() {
        return graphicalNotationDefined;
    }

    /**
     * 
     * @param graphicalNotationDefined
     *     The graphicalNotationDefined
     */
    @JsonProperty("graphicalNotationDefined")
    public void setGraphicalNotationDefined(Boolean graphicalNotationDefined) {
        this.graphicalNotationDefined = graphicalNotationDefined;
    }

    /**
     * 
     * @return
     *     The resource
     */
    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    /**
     * 
     * @param resource
     *     The resource
     */
    @JsonProperty("resource")
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * 
     * @return
     *     The diagramResource
     */
    @JsonProperty("diagramResource")
    public String getDiagramResource() {
        return diagramResource;
    }

    /**
     * 
     * @param diagramResource
     *     The diagramResource
     */
    @JsonProperty("diagramResource")
    public void setDiagramResource(String diagramResource) {
        this.diagramResource = diagramResource;
    }

    /**
     * 
     * @return
     *     The startFormDefined
     */
    @JsonProperty("startFormDefined")
    public Boolean getStartFormDefined() {
        return startFormDefined;
    }

    /**
     * 
     * @param startFormDefined
     *     The startFormDefined
     */
    @JsonProperty("startFormDefined")
    public void setStartFormDefined(Boolean startFormDefined) {
        this.startFormDefined = startFormDefined;
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
