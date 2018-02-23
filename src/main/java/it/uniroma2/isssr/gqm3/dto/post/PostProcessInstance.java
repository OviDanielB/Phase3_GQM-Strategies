
package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.*;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.Variable;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "processDefinitionId",
    "businessKey",
    "tenantId",
    "variables"
})
public class PostProcessInstance{


	@JsonProperty("processDefinitionId")
    private String processDefinitionId;
    @JsonProperty("businessKey")
    private String businessKey;
    @JsonProperty("tenantId")
    private String tenantId;
    @JsonProperty("variables")
    private List<Variable> variables = new ArrayList<Variable>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The processDefinitionId
     */
    @JsonProperty("processDefinitionId")
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    /**
     * 
     * @param processDefinitionId
     *     The processDefinitionId
     */
    @JsonProperty("processDefinitionId")
    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /**
     * 
     * @return
     *     The businessKey
     */
    @JsonProperty("businessKey")
    public String getBusinessKey() {
        return businessKey;
    }

    /**
     * 
     * @param businessKey
     *     The businessKey
     */
    @JsonProperty("businessKey")
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    @JsonProperty("tenantId")
    public String getTenantId() {
		return tenantId;
	}

    @JsonProperty("tenantId")
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
    
    
    /**
     * 
     * @return
     *     The variables
     */
    @JsonProperty("variables")
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * 
     * @param variables
     *     The variables
     */
    @JsonProperty("variables")
    public void setVariables(List<Variable> variables) {
        this.variables = variables;
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
