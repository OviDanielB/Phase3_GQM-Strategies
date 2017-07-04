
package it.uniroma2.isssr.gqm3.dto.activiti.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "processes",
    "signals",
    "pools",
    "imports",
    "interfaces",
    "globalArtifacts",
    "resources",
    "targetNamespace",
    "userTaskFormTypes",
    "metricTaskFormTypes",
    "startEventFormTypes"
})
public class ProcessDefinitionModel implements ActivitiEntity {

    @JsonProperty("processes")
    private List<Process> processes = new ArrayList<Process>();
    @JsonProperty("signals")
    private List<Object> signals = new ArrayList<Object>();
    @JsonProperty("pools")
    private List<Object> pools = new ArrayList<Object>();
    @JsonProperty("imports")
    private List<Object> imports = new ArrayList<Object>();
    @JsonProperty("interfaces")
    private List<Object> interfaces = new ArrayList<Object>();
    @JsonProperty("globalArtifacts")
    private List<Object> globalArtifacts = new ArrayList<Object>();
    @JsonProperty("resources")
    private List<Object> resources = new ArrayList<Object>();
    @JsonProperty("targetNamespace")
    private String targetNamespace;
    @JsonProperty("userTaskFormTypes")
    private Object userTaskFormTypes;
    @JsonProperty("metricTaskFormTypes")
    private Object metricTaskFormTypes;
    @JsonProperty("startEventFormTypes")
    private Object startEventFormTypes;

    /**
     * 
     * @return
     *     The processes
     */
    @JsonProperty("processes")
    public List<Process> getProcesses() {
        return processes;
    }

    /**
     * 
     * @param processes
     *     The processes
     */
    @JsonProperty("processes")
    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    /**
     * 
     * @return
     *     The signals
     */
    @JsonProperty("signals")
    public List<Object> getSignals() {
        return signals;
    }

    /**
     * 
     * @param signals
     *     The signals
     */
    @JsonProperty("signals")
    public void setSignals(List<Object> signals) {
        this.signals = signals;
    }

    /**
     * 
     * @return
     *     The pools
     */
    @JsonProperty("pools")
    public List<Object> getPools() {
        return pools;
    }

    /**
     * 
     * @param pools
     *     The pools
     */
    @JsonProperty("pools")
    public void setPools(List<Object> pools) {
        this.pools = pools;
    }

    /**
     * 
     * @return
     *     The imports
     */
    @JsonProperty("imports")
    public List<Object> getImports() {
        return imports;
    }

    /**
     * 
     * @param imports
     *     The imports
     */
    @JsonProperty("imports")
    public void setImports(List<Object> imports) {
        this.imports = imports;
    }

    /**
     * 
     * @return
     *     The interfaces
     */
    @JsonProperty("interfaces")
    public List<Object> getInterfaces() {
        return interfaces;
    }

    /**
     * 
     * @param interfaces
     *     The interfaces
     */
    @JsonProperty("interfaces")
    public void setInterfaces(List<Object> interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * 
     * @return
     *     The globalArtifacts
     */
    @JsonProperty("globalArtifacts")
    public List<Object> getGlobalArtifacts() {
        return globalArtifacts;
    }

    /**
     * 
     * @param globalArtifacts
     *     The globalArtifacts
     */
    @JsonProperty("globalArtifacts")
    public void setGlobalArtifacts(List<Object> globalArtifacts) {
        this.globalArtifacts = globalArtifacts;
    }

    /**
     * 
     * @return
     *     The resources
     */
    @JsonProperty("resources")
    public List<Object> getResources() {
        return resources;
    }

    /**
     * 
     * @param resources
     *     The resources
     */
    @JsonProperty("resources")
    public void setResources(List<Object> resources) {
        this.resources = resources;
    }

    /**
     * 
     * @return
     *     The targetNamespace
     */
    @JsonProperty("targetNamespace")
    public String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * 
     * @param targetNamespace
     *     The targetNamespace
     */
    @JsonProperty("targetNamespace")
    public void setTargetNamespace(String targetNamespace) {
        this.targetNamespace = targetNamespace;
    }

    /**
     * 
     * @return
     *     The userTaskFormTypes
     */
    @JsonProperty("userTaskFormTypes")
    public Object getUserTaskFormTypes() {
        return userTaskFormTypes;
    }

    /**
     * 
     * @param userTaskFormTypes
     *     The userTaskFormTypes
     */
    @JsonProperty("userTaskFormTypes")
    public void setUserTaskFormTypes(Object userTaskFormTypes) {
        this.userTaskFormTypes = userTaskFormTypes;
    }

    /**
     * 
     * @return
     *     The metricTaskFormTypes
     */
    @JsonProperty("metricTaskFormTypes")
    public Object getMetricTaskFormTypes() {
        return metricTaskFormTypes;
    }

    /**
     * 
     * @param metricTaskFormTypes
     *     The metricTaskFormTypes
     */
    @JsonProperty("metricTaskFormTypes")
    public void setMetricTaskFormTypes(Object metricTaskFormTypes) {
        this.metricTaskFormTypes = metricTaskFormTypes;
    }

    /**
     * 
     * @return
     *     The startEventFormTypes
     */
    @JsonProperty("startEventFormTypes")
    public Object getStartEventFormTypes() {
        return startEventFormTypes;
    }

    /**
     * 
     * @param startEventFormTypes
     *     The startEventFormTypes
     */
    @JsonProperty("startEventFormTypes")
    public void setStartEventFormTypes(Object startEventFormTypes) {
        this.startEventFormTypes = startEventFormTypes;
    }
}
