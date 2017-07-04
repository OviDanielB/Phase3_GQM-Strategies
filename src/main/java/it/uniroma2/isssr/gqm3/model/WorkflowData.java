package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@CompoundIndexes({

		@CompoundIndex(name = "metaWorkflowProcessInstanceId_businessWorkflowProcessDefinitionId_businessWorkflowProcessInstanceId", def = "{'metaWorkflowProcessInstanceId':1, "
				+ "'businessWorkflowProcessDefinitionId':1, " + "'businessWorkflowProcessInstanceId':1}", unique = true)

})

/**
 * This class represents a Workflow in the whole his completeness, 
 * there are name, process instance and process definition of businessWorkflow and metaWorkflow.
 * Is present also a list of Measure Task associated.
 *
 */
public class WorkflowData {
	@Id
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String _id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String businessWorkflowName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String metaWorkflowName;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String metaWorkflowProcessInstanceId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String businessWorkflowModelId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String businessWorkflowProcessDefinitionId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String businessWorkflowProcessInstanceId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean ended = false;
	@DBRef
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<MeasureTask> measureTasksList;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getBusinessWorkflowName() {
		return businessWorkflowName;
	}

	public void setBusinessWorkflowName(String businessWorkflowName) {
		this.businessWorkflowName = businessWorkflowName;
	}

	public String getMetaWorkflowName() {
		return metaWorkflowName;
	}

	public void setMetaWorkflowName(String metaWorkflowName) {
		this.metaWorkflowName = metaWorkflowName;
	}

	/**
	 * @return the metaWorkflowProcessInstance
	 */
	public String getMetaWorkflowProcessInstanceId() {
		return metaWorkflowProcessInstanceId;
	}

	/**
	 * @param metaWorkflowProcessInstance
	 *            the metaWorkflowProcessInstance to set
	 */
	public void setMetaWorkflowProcessInstanceId(String metaWorkflowProcessInstance) {
		this.metaWorkflowProcessInstanceId = metaWorkflowProcessInstance;
	}

	/**
	 * @return the businessWorkflowModelId
	 */
	public String getBusinessWorkflowModelId() {
		return businessWorkflowModelId;
	}

	/**
	 * @param businessWorkflowModelId
	 *            the businessWorkflowModelId to set
	 */
	public void setBusinessWorkflowModelId(String businessWorkflowModelId) {
		this.businessWorkflowModelId = businessWorkflowModelId;
	}

	/**
	 * @return the businessWorkflowProcessDefinitionId
	 */
	public String getBusinessWorkflowProcessDefinitionId() {
		return businessWorkflowProcessDefinitionId;
	}

	/**
	 * @param businessWorkflowProcessDefinitionId
	 *            the businessWorkflowProcessDefinitionId to set
	 */
	public void setBusinessWorkflowProcessDefinitionId(String businessWorkflowProcessDefinitionId) {
		this.businessWorkflowProcessDefinitionId = businessWorkflowProcessDefinitionId;
	}

	/**
	 * @return the businessWorkflowProcessInstanceId
	 */
	public String getBusinessWorkflowProcessInstanceId() {
		return businessWorkflowProcessInstanceId;
	}

	/**
	 * @param businessWorkflowProcessInstanceId
	 *            the businessWorkflowProcessInstanceId to set
	 */
	public void setBusinessWorkflowProcessInstanceId(String businessWorkflowProcessInstanceId) {
		this.businessWorkflowProcessInstanceId = businessWorkflowProcessInstanceId;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public List<MeasureTask> getMeasureTasksList() {
		return measureTasksList;
	}

	public void setMeasureTasksList(List<MeasureTask> measureTasksList) {
		this.measureTasksList = measureTasksList;
	}

}