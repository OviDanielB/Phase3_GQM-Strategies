package it.uniroma2.isssr.gqm3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.FlowElement;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.GroupActiviti;
import it.uniroma2.isssr.gqm3.model.Metric;
import it.uniroma2.isssr.gqm3.model.WorkflowData;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "flowElements",
    "metrics",
    "groups"
})

public class ResponseMeasurementPlan {
	
	@JsonProperty("flowElements")
    private List<FlowElement> flowElements = new ArrayList<FlowElement>();
    @JsonProperty("metrics")
    private List<Metric> metrics = new ArrayList<Metric>();
    @JsonProperty("groups")
    private List<GroupActiviti> groups = new ArrayList<GroupActiviti>();
    @JsonProperty("workflowData")
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private WorkflowData workflowData;
    
    
    /**
     * 
     * @return
     *     The flowElements
     */
	public List<FlowElement> getFlowElements() {
		return flowElements;
	}
	
	/**
     * 
     * @param flowElements
     *     The flowElements
     */
	public void setFlowElements(List<FlowElement> flowElements) {
		this.flowElements = flowElements;
	}
	
	/**
     * 
     * @return
     *     The metrics list
     */
	public List<Metric> getMetrics() {
		return metrics;
	}
	
	/**
     * 
     * @param metrics
     *     The metrics list
     */
	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}

	/**
     * 
     * @return
     *     The groups list
     */
	public List<GroupActiviti> getGroups() {
		return groups;
	}
	
	/**
     * 
     * @param groupsList
     *     The groups list
     */

	public void setGroups(List<GroupActiviti> groupsList) {
		this.groups = groupsList;
	}


    /**
     * 
     * @return
     *     The workflowData
     */
	public WorkflowData getWorkflowData() {
		return workflowData;
	}

	/**
     * 
     * @param workflowData
     *     The workflowData
     */

	public void setWorkflowData(WorkflowData workflowData) {
		this.workflowData = workflowData;
	}

}
