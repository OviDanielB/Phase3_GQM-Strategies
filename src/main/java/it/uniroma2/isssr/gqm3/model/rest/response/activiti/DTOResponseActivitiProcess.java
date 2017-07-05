package it.uniroma2.isssr.gqm3.model.rest.response.activiti;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.activiti.process.ActivitiProcessDef;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.List;

/**
 * Oggetto che rappresenta la risposta di una chiamata Rest,
 * ovvero viene restituito nelle ResponseEntity
 * (ad esempio quando si fa una get alla nostra applicazione)
 * 
 * Estende DTO che a sua volta implementa Serializable. 
 * 
 * 
 * @author luca
 *
 */
public class DTOResponseActivitiProcess extends DTO {
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	private List<ActivitiProcessDef> activitiProcess;
	
	@JsonInclude(Include.NON_NULL)
	private String id;

	@JsonInclude(Include.NON_NULL)
	private String url;

	@JsonInclude(Include.NON_NULL)
	private String businessKey;

	@JsonInclude(Include.NON_NULL)
	private String suspended;

	@JsonInclude(Include.NON_NULL)
	private String ended;

	@JsonInclude(Include.NON_NULL)
	private String processDefinitionId;

	@JsonInclude(Include.NON_NULL)
	private String processDefinitionUrl;

	@JsonInclude(Include.NON_NULL)
	private String activityId;

	@JsonInclude(Include.NON_NULL)
	private List<String> variables;

	@JsonInclude(Include.NON_NULL)
	private String tenantId;

	@JsonInclude(Include.NON_NULL)
	private String completed;	
	
	@JsonInclude(Include.NON_NULL)
	private String name;
	
	@JsonInclude(Include.NON_NULL)
	private String description;
	
	@JsonInclude(Include.NON_NULL)
	private String version;
	
	@JsonInclude(Include.NON_NULL)
	private String activitiProcessAuthor;	
	
	
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	
	
	public DTOResponseActivitiProcess(String error, String message) {
		this.error = error;
		this.message = message;
	}
	
	public DTOResponseActivitiProcess() {

	}

	public List<ActivitiProcessDef> getActivitiProcess() {
		return activitiProcess;
	}

	public void setActivitiProcess(List<ActivitiProcessDef> process) {
		this.activitiProcess = process;
	}

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

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getSuspended() {
		return suspended;
	}

	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	public String getEnded() {
		return ended;
	}

	public void setEnded(String ended) {
		this.ended = ended;
	}

	public String getProcessDefinitionUrl() {
		return processDefinitionUrl;
	}

	public void setProcessDefinitionUrl(String processDefinitionUrl) {
		this.processDefinitionUrl = processDefinitionUrl;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getActivitiProcessAuthor() {
		return activitiProcessAuthor;
	}

	public void setActivitiProcessAuthor(String activitiProcessAuthor) {
		this.activitiProcessAuthor = activitiProcessAuthor;
	}

}

