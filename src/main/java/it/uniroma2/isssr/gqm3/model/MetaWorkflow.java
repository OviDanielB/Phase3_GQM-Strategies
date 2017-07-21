package it.uniroma2.isssr.gqm3.model;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.Variable;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ActivitiEntityList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.DeploymentList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ProcessDefinitionList;
import it.uniroma2.isssr.gqm3.dto.post.PostProcessInstance;
import it.uniroma2.isssr.gqm3.dto.put.PutMessage;
import it.uniroma2.isssr.gqm3.tools.XmlTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetaWorkflow extends Workflow {

	private String businessWorkflowName;

	private static final String ACTION_CATCH_MESSAGE = "messageEventReceived";

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public MetaWorkflow(HostSettings hostSettings, String name, String businessWorkflowName) {
		super(hostSettings, name);
		this.businessWorkflowName = businessWorkflowName;
	}

	public MetaWorkflow(HostSettings hostSettings, String metaWorkflowProcessInstanceId) {
		super(hostSettings);
		super.setProcessInstanceId(metaWorkflowProcessInstanceId);
	}

	@Override
	protected PostProcessInstance buildStartRequestBody(String isStrategyUpdated) {
		PostProcessInstance processInstanceBody = new PostProcessInstance();
		processInstanceBody.setProcessDefinitionId(super.getProcessDefinitionId());
		processInstanceBody.setVariables(initializeMetaWorkflowVariables(isStrategyUpdated));
		return processInstanceBody;
	}

	@Override
	protected String buildWorkflowXmlString() throws ModelXmlNotFoundException, IOException {

		String metaworkflowString;
		try {
			log.info(hostSettings.getMetaworkflowPath(), hostSettings.getMetaworkflowProcessIdentifierName(),
					hostSettings.getMetaworkflowName());
			String n = super.getName();
			metaworkflowString = XmlTools.workflowModelXmlToString(hostSettings.getMetaworkflowPath(),
					hostSettings.getMetaworkflowProcessIdentifierName(), hostSettings.getMetaworkflowName(), n, n);
		} catch (FileNotFoundException e) {
			throw new ModelXmlNotFoundException();
		}
		return metaworkflowString;
	}

	/**
	 * Initialize variables of MetaWorkflow Process-instance.
	 * 
	 * @return List of variables initialized.
	 */
	private ArrayList<Variable> initializeMetaWorkflowVariables(String isStrategyUpdated) {

		ArrayList<Variable> variables = new ArrayList<>();

		ArrayList<String> variablesName = new ArrayList<>(Arrays.asList("businessWorkflowModelId",
				"businessWorkflowProcessDefinitionId", "businessWorkflowProcessInstanceId", "issueMessage",
				"strategicPlannerNote", "strategicPlannerReport", "workflowDeveloperNote1", "workflowDeveloperNote2",
				"workflowDeveloperReport", "measurementPlannerNote", "measurementPlannerReport",
				"validationPlannerNote", "validationPlannerReport", "gqmExpertNote", "gqmExpertReport"));
		ArrayList<String> addressVariablesName = new ArrayList<>(Arrays.asList("webappUrl", "deployEndpoint",
				"startEndpoint", "exportEndpoint", "measurePlanningEndpoint", "validationPlanningEndpoint",
				"workflowDataPlansUri", "activitiExplorerUri", "activitiModelerUri"));
		ArrayList<String> addressVariablesValue = new ArrayList<>(Arrays.asList(hostSettings.getWebappFrontendUrl(),
				hostSettings.getDeployEndpoint(), hostSettings.getStartEndpoint(), hostSettings.getExportEndpoint(),
				hostSettings.getMeasurePlanningEndpoint(), hostSettings.getValidationPlanningEndpoint(),
				hostSettings.getStrategyPlansUri(), hostSettings.getActivitiExplorerConnectionUrl(),
				hostSettings.getActivitiModelerUri()));

		Variable workflowNameVariable = new Variable();
		workflowNameVariable.setName("workflowName");
		workflowNameVariable.setValue(getBusinessWorkflowName());
		variables.add(workflowNameVariable);

		for (String variableName : variablesName) {

			Variable variable = new Variable();
			variable.setName(variableName);
			variable.setValue("");
			variables.add(variable);
		}
		for (int i = 0; i < addressVariablesName.size(); i++) {

			Variable variable = new Variable();
			variable.setName(addressVariablesName.get(i));
			variable.setValue(addressVariablesValue.get(i));
			variables.add(variable);
		}

		Variable variable = new Variable();
		variable.setName("updatedStrategy");
		Boolean val = true;
		if (Integer.valueOf(isStrategyUpdated) == HostSettings.state.NEW.getValue()) {
			val = false;
		}
		variable.setValue(val);
		variables.add(variable);

		return variables;
	}
	
	
	
	/**
	 * Update some of MetaWorkflow Process Instance variables with activiti
	 * rest.
	 *
	 * @param issueMessage
	 *            Message reported by an user regarding an issue in the business
	 *            workflow.
	 * 
	 * @throws JsonRequestException
	 *             If there is an error during rest request.
	 */
	private void updateVariablesAfterFeedback(String issueMessage) throws JsonRequestException {
		
		ArrayList<Variable> variables = resetMetaWorkflowVariables();

		Variable issueMessageVariable = new Variable();

		issueMessageVariable.setName("issueMessage");
		issueMessageVariable.setValue(issueMessage);
		variables.add(issueMessageVariable);

		String restAddress = hostSettings.getActivitiRestEndpointProcessInstances() + "/"
				+ getProcessInstanceId() + "/variables";

		jsonRequestActiviti.put(restAddress, variables, String.class);

	}
	
	
	
	private ArrayList<Variable> resetMetaWorkflowVariables() throws JsonRequestException {

		ArrayList<Variable> variables = new ArrayList<>();

		ArrayList<String> variablesName = new ArrayList<>(Arrays.asList("businessWorkflowProcessInstanceId",
				"strategicPlannerNote", "strategicPlannerReport", "workflowDeveloperNote1", "workflowDeveloperNote2",
				"workflowDeveloperReport", "measurementPlannerNote", "measurementPlannerReport",
				"validationPlannerNote", "validationPlannerReport", "gqmExpertNote", "gqmExpertReport"));

		for (String variableName : variablesName) {

			Variable variable = new Variable();
			variable.setName(variableName);
			variable.setValue("");
			variables.add(variable);
		}
		return variables;
	}	
	
	
	public void sendMessage(String messageType, String messageBody) throws JsonRequestException, IssueMessageCatcherNotFoundException, JsonRequestConflictException{
		
		if( messageType == WorkflowMessage.MESSAGE_EVENT_SUBSCRIPTION_NAME_ISSUE_MESSAGE){
			
			updateVariablesAfterFeedback( messageBody);
		}
		String messageCatcherId = this.getMessageCatcherId(messageType);

		PutMessage putMessage = new PutMessage();
		putMessage.setAction(ACTION_CATCH_MESSAGE);
		putMessage.setMessageName(messageType);

		jsonRequestActiviti.put(hostSettings.getActivitiRestEndpointExecutions() + "/" + messageCatcherId, putMessage,
				String.class);
	}
	
	
	
	
	
	

	public void checkAlreadyExist(String name)
			throws ActivitiEntityAlreadyExistsException, JsonRequestException {

		String metaWorkflowName = name + Workflow.MODEL_XML_EXT;

		List<String> restAddresses = Arrays.asList(hostSettings.getActivitiRestEndpointDeployments(),
				hostSettings.getActivitiRestEndpointProcessDefinitions());

		List<Class<? extends ActivitiEntityList>> entitylists = Arrays.asList(DeploymentList.class,
				ProcessDefinitionList.class);

		for (int i = 0; i < restAddresses.size(); i++) {

			String restAddress = restAddresses.get(i);

			Class<? extends ActivitiEntityList> T = entitylists.get(i);
			super.checkAlreadyExists(metaWorkflowName, restAddress, T );
		}
	}

	public String getBusinessWorkflowName() {
		return businessWorkflowName;
	}

	public void setBusinessWorkflowName(String businessWorkflowName) {
		this.businessWorkflowName = businessWorkflowName;
	}

}
