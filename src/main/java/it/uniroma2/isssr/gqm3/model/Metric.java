package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "busVersion", "ordered", "set", "max", "entityType", "secretToken", "creatorId", "description",
		"creationDate", "version", "releaseNote", "hasMax", "tags", "unit", "min", "scaleType", "hasUserDefinedList",
		"userDefinedList", "name", "lastVersionDate", "id", "state", "hasMin", "metricatorId" })

/**
 * This class represents the metric object that Phase 2.2 b has been defined
 *
 */
public class Metric {

	@Id
	private String _id;
	@JsonProperty("busVersion")
	private String busVersion;
	@JsonProperty("ordered")
	private Boolean ordered;
	@JsonProperty("set")
	private String set;
	@JsonProperty("max")
	private Double max;
	@JsonProperty("entityType")
	private String entityType;
	@JsonProperty("secretToken")
	private String secretToken;
	@JsonProperty("creatorId")
	private String creatorId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("creationDate")
	private String creationDate;
	@JsonProperty("version")
	private String version;
	@JsonProperty("releaseNote")
	private String releaseNote;
	@JsonProperty("hasMax")
	private Boolean hasMax;
	@JsonProperty("tags")
	private List<String> tags = new ArrayList<String>();
	@JsonProperty("unit")
	private String unit;
	@JsonProperty("min")
	private Double min;
	@JsonProperty("scaleType")
	private String scaleType;
	@JsonProperty("hasUserDefinedList")
	private Boolean hasUserDefinedList;
	@JsonProperty("userDefinedList")
	private List<String> userDefinedList = new ArrayList<String>();
	@JsonProperty("name")
	private String name;
	@JsonProperty("lastVersionDate")
	private String lastVersionDate;
	@JsonProperty("id")
	private String id;
	@JsonProperty("state")
	private String state;
	@JsonProperty("hasMin")
	private Boolean hasMin;
	@JsonProperty("metricatorId")
	private String metricatorId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * 
	 * @return The busVersion
	 */
	@JsonProperty("busVersion")
	public String getBusVersion() {
		return busVersion;
	}

	/**
	 * 
	 * @param busVersion
	 *            The busVersion
	 */
	@JsonProperty("busVersion")
	public void setBusVersion(String busVersion) {
		this.busVersion = busVersion;
	}

	/**
	 * 
	 * @return The ordered
	 */
	@JsonProperty("ordered")
	public Boolean getOrdered() {
		return ordered;
	}

	/**
	 * 
	 * @param ordered
	 *            The ordered
	 */
	@JsonProperty("ordered")
	public void setOrdered(Boolean ordered) {
		this.ordered = ordered;
	}

	/**
	 * 
	 * @return The set
	 */
	@JsonProperty("set")
	public String getSet() {
		return set;
	}

	/**
	 * 
	 * @param set
	 *            The set
	 */
	@JsonProperty("set")
	public void setSet(String set) {
		this.set = set;
	}

	/**
	 * 
	 * @return The max
	 */
	@JsonProperty("max")
	public Double getMax() {
		return max;
	}

	/**
	 * 
	 * @param max
	 *            The max
	 */
	@JsonProperty("max")
	public void setMax(Double max) {
		this.max = max;
	}

	/**
	 * 
	 * @return The entityType
	 */
	@JsonProperty("entityType")
	public String getEntityType() {
		return entityType;
	}

	/**
	 * 
	 * @param entityType
	 *            The entityType
	 */
	@JsonProperty("entityType")
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * 
	 * @return The secretToken
	 */
	@JsonProperty("secretToken")
	public String getSecretToken() {
		return secretToken;
	}

	/**
	 * 
	 * @param secretToken
	 *            The secretToken
	 */
	@JsonProperty("secretToken")
	public void setSecretToken(String secretToken) {
		this.secretToken = secretToken;
	}

	/**
	 * 
	 * @return The creatorId
	 */
	@JsonProperty("creatorId")
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * 
	 * @param creatorId
	 *            The creatorId
	 */
	@JsonProperty("creatorId")
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The creationDate
	 */
	@JsonProperty("creationDate")
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * 
	 * @param creationDate
	 *            The creationDate
	 */
	@JsonProperty("creationDate")
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * 
	 * @return The version
	 */
	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	/**
	 * 
	 * @param version
	 *            The version
	 */
	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 
	 * @return The releaseNote
	 */
	@JsonProperty("releaseNote")
	public String getReleaseNote() {
		return releaseNote;
	}

	/**
	 * 
	 * @param releaseNote
	 *            The releaseNote
	 */
	@JsonProperty("releaseNote")
	public void setReleaseNote(String releaseNote) {
		this.releaseNote = releaseNote;
	}

	/**
	 * 
	 * @return The hasMax
	 */
	@JsonProperty("hasMax")
	public Boolean getHasMax() {
		return hasMax;
	}

	/**
	 * 
	 * @param hasMax
	 *            The hasMax
	 */
	@JsonProperty("hasMax")
	public void setHasMax(Boolean hasMax) {
		this.hasMax = hasMax;
	}

	/**
	 * 
	 * @return The tags
	 */
	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}

	/**
	 * 
	 * @param tags
	 *            The tags
	 */
	@JsonProperty("tags")
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * 
	 * @return The unit
	 */
	@JsonProperty("unit")
	public String getUnit() {
		return unit;
	}

	/**
	 * 
	 * @param unit
	 *            The unit
	 */
	@JsonProperty("unit")
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * 
	 * @return The min
	 */
	@JsonProperty("min")
	public Double getMin() {
		return min;
	}

	/**
	 * 
	 * @param min
	 *            The min
	 */
	@JsonProperty("min")
	public void setMin(Double min) {
		this.min = min;
	}

	/**
	 * 
	 * @return The scaleType
	 */
	@JsonProperty("scaleType")
	public String getScaleType() {
		return scaleType;
	}

	/**
	 * 
	 * @param scaleType
	 *            The scaleType
	 */
	@JsonProperty("scaleType")
	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}

	/**
	 * 
	 * @return The hasUserDefinedList
	 */
	@JsonProperty("hasUserDefinedList")
	public Boolean getHasUserDefinedList() {
		return hasUserDefinedList;
	}

	/**
	 * 
	 * @param hasUserDefinedList
	 *            The hasUserDefinedList
	 */
	@JsonProperty("hasUserDefinedList")
	public void setHasUserDefinedList(Boolean hasUserDefinedList) {
		this.hasUserDefinedList = hasUserDefinedList;
	}

	/**
	 * 
	 * @return The userDefinedList
	 */
	@JsonProperty("userDefinedList")
	public List<String> getUserDefinedList() {
		return userDefinedList;
	}

	/**
	 * 
	 * @param userDefinedList
	 *            The userDefinedList
	 */
	@JsonProperty("userDefinedList")
	public void setUserDefinedList(List<String> userDefinedList) {
		this.userDefinedList = userDefinedList;
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
	 * @return The lastVersionDate
	 */
	@JsonProperty("lastVersionDate")
	public String getLastVersionDate() {
		return lastVersionDate;
	}

	/**
	 * 
	 * @param lastVersionDate
	 *            The lastVersionDate
	 */
	@JsonProperty("lastVersionDate")
	public void setLastVersionDate(String lastVersionDate) {
		this.lastVersionDate = lastVersionDate;
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
	 * @return The state
	 */
	@JsonProperty("state")
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 *            The state
	 */
	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @return The hasMin
	 */
	@JsonProperty("hasMin")
	public Boolean getHasMin() {
		return hasMin;
	}

	/**
	 * 
	 * @param hasMin
	 *            The hasMin
	 */
	@JsonProperty("hasMin")
	public void setHasMin(Boolean hasMin) {
		this.hasMin = hasMin;
	}

	/**
	 * 
	 * @return The metricatorId
	 */
	@JsonProperty("metricatorId")
	public String getMetricatorId() {
		return metricatorId;
	}

	/**
	 * 
	 * @param metricatorId
	 *            The metricatorId
	 */
	@JsonProperty("metricatorId")
	public void setMetricatorId(String metricatorId) {
		this.metricatorId = metricatorId;
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