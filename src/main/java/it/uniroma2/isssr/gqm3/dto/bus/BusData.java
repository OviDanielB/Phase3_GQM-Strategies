package it.uniroma2.isssr.gqm3.dto.bus;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "typeObj", "operation", "instance", "phase", "busVersion" })
public class BusData {

	@JsonProperty("typeObj")
	private String typeObj;
	@JsonProperty("operation")
	private String operation;
	@JsonProperty("instance")
	private String instance;
	@JsonProperty("phase")
	private String phase;
	@JsonProperty("busVersion")
	private String busVersion;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The typeObj
	 */
	@JsonProperty("typeObj")
	public String getTypeObj() {
		return typeObj;
	}

	/**
	 * 
	 * @param typeObj
	 *            The typeObj
	 */
	@JsonProperty("typeObj")
	public void setTypeObj(String typeObj) {
		this.typeObj = typeObj;
	}

	/**
	 * 
	 * @return The operation
	 */
	@JsonProperty("operation")
	public String getOperation() {
		return operation;
	}

	/**
	 * 
	 * @param operation
	 *            The operation
	 */
	@JsonProperty("operation")
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * 
	 * @return The instance
	 */
	@JsonProperty("instance")
	public String getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param instance
	 *            The instance
	 */
	@JsonProperty("instance")
	public void setInstance(String instance) {
		this.instance = instance;
	}

	/**
	 * 
	 * @return The phase
	 */
	@JsonProperty("phase")
	public String getPhase() {
		return phase;
	}

	/**
	 * 
	 * @param phase
	 *            The phase
	 */
	@JsonProperty("phase")
	public void setPhase(String phase) {
		this.phase = phase;
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

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}