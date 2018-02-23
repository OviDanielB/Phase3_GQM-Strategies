
package it.uniroma2.isssr.gqm3.dto.bus;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "busVersion", "instance", "payload", "typeObj", "err", "msg" })
public class BusReadResponse {

	@JsonProperty("busVersion")
	private String busVersion;
	@JsonProperty("instance")
	private String instance;
	@JsonProperty("payload")
	private JsonNode payload;
	@JsonProperty("typeObj")
	private String typeObj;
	//@JsonProperty("tags")
	//private List<Tag> tags = new ArrayList<Tag>();
	@JsonProperty("err")
	private String err;
	@JsonProperty("msg")
	private String msg;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
	 * @return The payload
	 */
	@JsonProperty("payload")
	public JsonNode getPayload() {
		return payload;
	}

	/**
	 * 
	 * @param payload
	 *            The payload
	 */
	@JsonProperty("payload")
	public void setPayload(JsonNode payload) {
		this.payload = payload;
	}

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

	@JsonProperty("err")
	public String getErr() {
		return err;
	}

	@JsonProperty("err")
	public void setErr(String err) {
		this.err = err;
	}

	@JsonProperty("msg")
	public String getMsg() {
		return msg;
	}

	@JsonProperty("msg")
	public void setMsg(String msg) {
		this.msg = msg;
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
