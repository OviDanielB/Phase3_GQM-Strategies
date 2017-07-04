
package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "modelId" })
public class PostDeploy {


	@JsonProperty("modelId")
	private String modelId;

	/**
	 * 
	 * @return The modelId
	 */
	@JsonProperty("modelId")
	public String getModelId() {
		return modelId;
	}

	/**
	 * 
	 * @param modelId
	 *            The modelId
	 */
	@JsonProperty("modelId")
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

}