package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents a Collected Data necessary for the GQM+Strategies Tool Phase 4 that is associated to a
 * particular workflowData. It's contains task id, the value collected and a boolean that indicates if this value is
 * validated or not.
 *
 */
@Document
public class CollectedData
{
	
	@Id
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String _id;
	@DBRef
	private WorkflowData workflowData;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String taskId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String value;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean validated;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String date;
	
	public String get_id()
	{
		return _id;
	}
	
	public void set_id(String _id)
	{
		this._id = _id;
	}
	
	public WorkflowData getWorkflowData()
	{
		return workflowData;
	}
	
	public void setWorkflowData(WorkflowData workflowData)
	{
		this.workflowData = workflowData;
	}
	
	public String getTaskId()
	{
		return taskId;
	}
	
	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public boolean isValidated()
	{
		return validated;
	}
	
	public void setValidated(boolean validated)
	{
		this.validated = validated;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setDate(String string)
	{
		this.date = string;
	}
	
}
