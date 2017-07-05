package it.uniroma2.isssr.gqm3.model.activiti.process;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author luca
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class ActivitiProcessDefinition {

    private String id;
    private String url;
    private String key;
    private String version;
    private String name;
    private String description;
    private String tenantId;
    private String deploymentId;
    private String deploymentUrl;
    private String resource;
    private String diagramResource;
    private String category;
    private String graphicalNotationDefined;
    private String suspended;
    private String startFormDefined;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTenantId() {
        return tenantId;
    }
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    public String getDeploymentId() {
        return deploymentId;
    }
    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
    public String getDeploymentUrl() {
        return deploymentUrl;
    }
    public void setDeploymentUrl(String deploymentUrl) {
        this.deploymentUrl = deploymentUrl;
    }
    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getDiagramResource() {
        return diagramResource;
    }
    public void setDiagramResource(String diagramResource) {
        this.diagramResource = diagramResource;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getGraphicalNotationDefined() {
        return graphicalNotationDefined;
    }
    public void setGraphicalNotationDefined(String graphicalNotationDefined) {
        this.graphicalNotationDefined = graphicalNotationDefined;
    }
    public String getSuspended() {
        return suspended;
    }
    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }
    public String getStartFormDefined() {
        return startFormDefined;
    }
    public void setStartFormDefined(String startFormDefined) {
        this.startFormDefined = startFormDefined;
    }

}

