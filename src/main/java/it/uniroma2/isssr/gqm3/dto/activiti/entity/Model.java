
package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "name", "key", "category", "version", "metaInfo", "deploymentId", "id", "url", "createTime",
		"lastUpdateTime", "deploymentUrl", "tenantId" })
public class Model implements ActivitiEntity {

	@JsonProperty("name")
	private String name;
	@JsonProperty("key")
	private String key;
	@JsonProperty("category")
	private String category;
	@JsonProperty("version")
	private Integer version;
	@JsonProperty("metaInfo")
	private String metaInfo;
	@JsonProperty("deploymentId")
	private String deploymentId;
	@JsonProperty("id")
	private String id;
	@JsonProperty("url")
	private String url;
	@JsonProperty("createTime")
	private String createTime;
	@JsonProperty("lastUpdateTime")
	private String lastUpdateTime;
	@JsonProperty("deploymentUrl")
	private String deploymentUrl;
	@JsonProperty("tenantId")
	private Object tenantId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/*
	 * private static final String DEFAULT_MODEL_SOURCE = "{\"id\":\"canvas\","
	 * + "\"resourceId\":\"canvas\"," +
	 * "\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}";
	 */
	private static final String DEFAULT_MODEL_SOURCE = "{ \"resourceId\":\"canvas\", \"properties\":{ \"process_id\":\"DEFAULT_NAME_PLACEHOLDER\", \"name\":\"DEFAULT_NAME_PLACEHOLDER\", \"documentation\":\"\", \"process_author\":\"\", \"process_version\":\"\", \"process_namespace\":\"http://www.activiti.org/processdef\", \"executionlisteners\":\"\", \"eventlisteners\":\"\", \"signaldefinitions\":\"\", \"messagedefinitions\":\"\" }, \"stencil\":{ \"id\":\"BPMNDiagram\" }, \"childShapes\":[  ], \"bounds\":{  }, \"stencilset\":{ \"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\", \"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\" } } ";

	public static String getDefaultModelSource(String name) {
		return DEFAULT_MODEL_SOURCE.replaceAll("DEFAULT_NAME_PLACEHOLDER", name);
	}

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The key
	 */
	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 *            The key
	 */
	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 
	 * @return The category
	 */
	@JsonProperty("category")
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category
	 *            The category
	 */
	@JsonProperty("category")
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 
	 * @return The version
	 */
	@JsonProperty("version")
	public Integer getVersion() {
		return version;
	}

	/**
	 * 
	 * @param version
	 *            The version
	 */
	@JsonProperty("version")
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * 
	 * @return The metaInfo
	 */
	@JsonProperty("metaInfo")
	public String getMetaInfo() {
		return metaInfo;
	}

	/**
	 * 
	 * @param metaInfo
	 *            The metaInfo
	 */
	@JsonProperty("metaInfo")
	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}

	/**
	 * 
	 * @return The deploymentId
	 */
	@JsonProperty("deploymentId")
	public String getDeploymentId() {
		return deploymentId;
	}

	/**
	 * 
	 * @param deploymentId
	 *            The deploymentId
	 */
	@JsonProperty("deploymentId")
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The url
	 */
	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url
	 *            The url
	 */
	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * @return The createTime
	 */
	@JsonProperty("createTime")
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime
	 *            The createTime
	 */
	@JsonProperty("createTime")
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return The lastUpdateTime
	 */
	@JsonProperty("lastUpdateTime")
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * 
	 * @param lastUpdateTime
	 *            The lastUpdateTime
	 */
	@JsonProperty("lastUpdateTime")
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * 
	 * @return The deploymentUrl
	 */
	@JsonProperty("deploymentUrl")
	public String getDeploymentUrl() {
		return deploymentUrl;
	}

	/**
	 * 
	 * @param deploymentUrl
	 *            The deploymentUrl
	 */
	@JsonProperty("deploymentUrl")
	public void setDeploymentUrl(String deploymentUrl) {
		this.deploymentUrl = deploymentUrl;
	}

	/**
	 * 
	 * @return The tenantId
	 */
	@JsonProperty("tenantId")
	public Object getTenantId() {
		return tenantId;
	}

	/**
	 * 
	 * @param tenantId
	 *            The tenantId
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
