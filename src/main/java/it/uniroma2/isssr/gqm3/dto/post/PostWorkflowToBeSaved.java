
package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "modelId", "name", "scope", "xml" })
public class PostWorkflowToBeSaved {

	@JsonProperty("modelId")
	private String modelId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("xml")
	private String xml;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getModelId() {
		return modelId;
	}

	public String getName() {
		return name;
	}

	public String getScope() {
		return scope;
	}

	public String getXml() {
		return xml;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setXml(String xml) {
		this.xml = xml;
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