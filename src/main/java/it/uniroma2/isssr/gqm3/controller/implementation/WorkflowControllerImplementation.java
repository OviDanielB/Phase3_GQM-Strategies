package it.uniroma2.isssr.gqm3.controller.implementation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.controller.WorkflowController;
import it.uniroma2.isssr.gqm3.dto.ErrorResponse;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.Deployment;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.Model;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.ProcessDefinition;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.ProcessInstance;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.DeploymentList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ModelList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ProcessDefinitionList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ProcessInstanceList;
import it.uniroma2.isssr.gqm3.dto.post.PostCreateWorkflow;
import it.uniroma2.isssr.gqm3.dto.post.PostDeploy;
import it.uniroma2.isssr.gqm3.dto.post.PostStartProcessInstance;
import it.uniroma2.isssr.gqm3.model.BusinessWorkflow;
import it.uniroma2.isssr.gqm3.model.MetaWorkflow;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;
import it.uniroma2.isssr.gqm3.tools.JsonRequestActiviti;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "Workflow Controller", description = "Workflow Controller API")
public class WorkflowControllerImplementation implements WorkflowController {

	// TODO lettere accentate
	private static final char[] ILLEGAL_CHARACTERS = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>',
			'|', '\"', ':', '.' };

	@Autowired
	private HostSettings hostSettings;

	@Autowired
	private WorkflowDataRepository workflowDataRepository;

	@RequestMapping(value = "/workflows/create", method = RequestMethod.POST)
	@ApiOperation(value = "Create Workflow", notes = "This endpoint creates and starts a meta-workflow instance and creates an empty model of the related business workflow")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class) })
	public ResponseEntity<String> createWorkflow(HttpServletResponse httpServletResponse,
                                                 @RequestBody PostCreateWorkflow createWorkflowBody) throws IllegalCharacterRequestException,
			ActivitiEntityAlreadyExistsException, JsonRequestException, BusinessWorkflowNotCreatedException,
			MetaWorkflowNotDeployedException, ModelXmlNotFoundException, MetaWorkflowNotStartedException, IOException,
			ProcessDefinitionNotFoundException, JsonRequestConflictException {

		String workflowName = createWorkflowBody.getName();

		for (char character : ILLEGAL_CHARACTERS) {
			if (workflowName.indexOf(character) >= 0) {
				throw new IllegalCharacterRequestException(workflowName, character);
			}
		}
		if (!Character.isLetter(workflowName.charAt(0))) {
			throw new IllegalCharacterRequestException(workflowName, workflowName.charAt(0));
		}

		String metaWorkflowName = hostSettings.getMetaworkflowPrefix() + workflowName
				+ hostSettings.getMetaworkflowSuffix();

		MetaWorkflow metaWorkflow = new MetaWorkflow(hostSettings, metaWorkflowName, workflowName);
		metaWorkflow.checkAlreadyExist(metaWorkflowName);
		BusinessWorkflow businessWorkflow = new BusinessWorkflow(hostSettings, workflowName);
		businessWorkflow.checkAlreadyExist(workflowName);
		metaWorkflow.deploy();
		metaWorkflow.start();

		businessWorkflow.setMetaWorkflowProcessInstanceId(metaWorkflow.getProcessInstanceId());
		businessWorkflow.createModel();
		metaWorkflow.updateVariable("businessWorkflowModelId", businessWorkflow.getModelId());

		WorkflowData workflowData = new WorkflowData();
		workflowData.setBusinessWorkflowName(businessWorkflow.getName());
		workflowData.setBusinessWorkflowModelId(businessWorkflow.getModelId());
		workflowData.setMetaWorkflowName(metaWorkflow.getName());
		workflowData.setMetaWorkflowProcessInstanceId(metaWorkflow.getProcessInstanceId());
		workflowDataRepository.save(workflowData);

		JSONObject response = new JSONObject();
		response.put("metaWorkflowProcessInstanceId", metaWorkflow.getProcessInstanceId());
		response.put("businessWorkflowModelId", businessWorkflow.getModelId());

		return new ResponseEntity<String>(response.toString(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/workflows", method = RequestMethod.GET)
	@ApiOperation(value = "Get Workflows", notes = "This endpoint retrieves all names and IDs of the models [DEPRECATED]")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class) })
	public ResponseEntity<String> getWorkflows() throws JsonRequestException {

		List<String> workflowNames = new ArrayList<String>();
		List<String> workflowIDs = new ArrayList<String>();

		JsonRequestActiviti jsonRequest = new JsonRequestActiviti(hostSettings);

		@SuppressWarnings("unchecked")
		List<Model> modelList = (List<Model>) jsonRequest.getList(hostSettings.getActivitiRestEndpointModels(),
				ModelList.class);

		for (Model model : modelList) {
			workflowNames.add(model.getName());
			workflowIDs.add(model.getId());
		}

		JSONObject response = new JSONObject();
		response.put("workflowNames", workflowNames);
		response.put("workflowIDs", workflowIDs);

		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/workflows/deployments", method = RequestMethod.POST)
	@ApiOperation(value = "Deploy Workflow Model", notes = "This endpoint deploy a business workflow")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class) })
	public ResponseEntity<String> deployWorkflowModel(@RequestBody PostDeploy deployBody) throws JsonRequestException,
			MetaWorkflowNotDeployedException, ModelXmlNotFoundException, WorkflowDataException, IOException,
			ProcessDefinitionNotFoundException, ActivitiEntityAlreadyExistsException {

		
		
		String modelId = deployBody.getModelId();
		List<WorkflowData> workflowDatas = workflowDataRepository.findByBusinessWorkflowModelId(modelId);
		if (workflowDatas.size() != 1) {
			throw new WorkflowDataException();
		}
		WorkflowData workflowData = workflowDatas.get(0);
		String metaWorkflowProcessInstanceId = workflowData.getMetaWorkflowProcessInstanceId();
		String businessWorkflowName = workflowData.getBusinessWorkflowName();
		String businessWorkflowProcessDefinitionId = workflowData.getBusinessWorkflowProcessDefinitionId();
		String businessWorkflowProcessInstanceId = workflowData.getBusinessWorkflowProcessInstanceId();

		if(businessWorkflowProcessInstanceId != null 
				&& !businessWorkflowProcessInstanceId.isEmpty() ){
			
			throw new ActivitiEntityAlreadyExistsException(businessWorkflowName, ProcessInstance.class);
		}
		
		BusinessWorkflow businessWorkflow = new BusinessWorkflow(hostSettings);
		businessWorkflow.setModelId(modelId);
		businessWorkflow.setName(businessWorkflowName);

		if(businessWorkflowProcessDefinitionId != null 
				&& !businessWorkflowProcessDefinitionId.isEmpty() ){
			
			businessWorkflow.setProcessDefinitionId(businessWorkflowProcessDefinitionId);
			businessWorkflow.deleteDeployment();
		}

		businessWorkflow.deploy();

		MetaWorkflow metaWorkflow = new MetaWorkflow(hostSettings, metaWorkflowProcessInstanceId);

		businessWorkflowProcessDefinitionId = businessWorkflow.getProcessDefinitionId();
		metaWorkflow.updateVariable("businessWorkflowProcessDefinitionId", businessWorkflowProcessDefinitionId);
		workflowData.setBusinessWorkflowProcessDefinitionId(businessWorkflowProcessDefinitionId);
		workflowDataRepository.save(workflowData);

		JSONObject response = new JSONObject();
		response.put("businessWorkflowProcessDefinitionId", businessWorkflowProcessDefinitionId);

		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}

	
	
	
	
	
	
	@RequestMapping(value = "/workflows/processinstances", method = RequestMethod.POST)
	@ApiOperation(value = "Start Workflow Process Instance", notes = "This endpoint starts a business workflow and updates the meta-workflow")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class) })
	public ResponseEntity<String> startWorkflowProcessInstance(
			@RequestBody PostStartProcessInstance startProcessInstanceBody)
			throws JsonRequestException, MetaWorkflowNotDeployedException, ModelXmlNotFoundException,
			MetaWorkflowNotStartedException, WorkflowDataException, JsonRequestConflictException {
		
		
		String processDefinitionId = startProcessInstanceBody.getProcessDefinitionId();
		List<WorkflowData> workflowDatas = workflowDataRepository
				.findByBusinessWorkflowProcessDefinitionId(processDefinitionId);
		if (workflowDatas.size() != 1) {
			throw new WorkflowDataException();
		}
		WorkflowData workflowData = workflowDatas.get(0);
		String businessWorkflowName = workflowData.getBusinessWorkflowName();

		BusinessWorkflow businessWorkflow = new BusinessWorkflow(hostSettings);
		businessWorkflow.setProcessDefinitionId(processDefinitionId);
		businessWorkflow.setName(businessWorkflowName);

		businessWorkflow.start();
		String metaWorkflowProcessInstanceId = workflowData.getMetaWorkflowProcessInstanceId();
		String businessWorkflowProcessInstanceId = businessWorkflow.getProcessInstanceId();
		MetaWorkflow metaWorkflow = new MetaWorkflow(hostSettings, metaWorkflowProcessInstanceId);
		metaWorkflow.updateVariable("businessWorkflowProcessInstanceId", businessWorkflowProcessInstanceId);
		workflowData.setBusinessWorkflowProcessInstanceId(businessWorkflowProcessInstanceId);
		workflowDataRepository.save(workflowData);

		JSONObject response = new JSONObject();
		response.put("businessWorkflowProcessInstanceId", businessWorkflow.getProcessInstanceId());

		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(value = "/workflows/{modelId}/process-definition-id", method = RequestMethod.GET)
	@ApiOperation(value = "Get Workflow Process Definition Id", notes = "This endpoint check if the specificated workflow has been deployed yet and returns the associated process definition ID")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class) })
	public ResponseEntity<String> getWorkflowProcessDefinitionId(@PathVariable(value = "modelId") String modelId)
			throws JsonRequestException, ActivitiEntityAlreadyExistsException, WorkflowDataException,
			ProcessDefinitionNotFoundException {
		List<WorkflowData> workflowDatas = workflowDataRepository.findByBusinessWorkflowModelId(modelId);
		if (workflowDatas.size() != 1) {
			throw new WorkflowDataException();
		}
		WorkflowData workflowData = workflowDatas.get(0);
		String businessWorkflowName = workflowData.getBusinessWorkflowName();

		JsonRequestActiviti jsonRequestActiviti = new JsonRequestActiviti(hostSettings);

		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put("name", businessWorkflowName);
		queryParams.put("sort", "deployTime");

		List<Deployment> deployments = (List<Deployment>) jsonRequestActiviti
				.getList(hostSettings.getActivitiRestEndpointDeployments(), DeploymentList.class, queryParams);

		if (deployments.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} else {
			String deploymentId = deployments.get(0).getId();
			queryParams = new LinkedHashMap<String, String>();
			queryParams.put("deploymentId", deploymentId);

			List<ProcessDefinition> processDefinitionList = (List<ProcessDefinition>) jsonRequestActiviti.getList(
					hostSettings.getActivitiRestEndpointProcessDefinitions(), ProcessDefinitionList.class, queryParams);

			if (processDefinitionList.isEmpty()) {
				throw new ProcessDefinitionNotFoundException(deploymentId);
			} else {
				String businessWorkflowProcessDefinitionId = processDefinitionList.get(0).getId();
				JSONObject response = new JSONObject();
				response.put("businessWorkflowProcessDefinitionId", businessWorkflowProcessDefinitionId);

				return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(value = "/workflows/{businessWorkflowProcessDefinitionId}/process-instance", method = RequestMethod.GET)
	@ApiOperation(value = "Get Workflow Process Instance ID", notes = "This endpoint checks if a workflow has been started yet")
	@ApiResponses(value = {
			@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class) })
	public ResponseEntity<String> getWorkflowProcessInstanceId(
			@PathVariable(value = "businessWorkflowProcessDefinitionId") String businessWorkflowProcessDefinitionId)
			throws JsonRequestException, ActivitiEntityAlreadyExistsException, WorkflowDataException,
			ProcessDefinitionNotFoundException {
		JsonRequestActiviti jsonRequestActiviti = new JsonRequestActiviti(hostSettings);

		Map<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put("processDefinitionId", businessWorkflowProcessDefinitionId);

		List<ProcessInstance> processInstances = (List<ProcessInstance>) jsonRequestActiviti.getList(
				hostSettings.getActivitiRestEndpointProcessInstances(), ProcessInstanceList.class, queryParams);

		if (processInstances.isEmpty()) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} else {
			String businessWorkflowProcessInstanceId = processInstances.get(0).getId();

			JSONObject response = new JSONObject();
			response.put("businessWorkflowProcessInstanceId", businessWorkflowProcessInstanceId);

			return new ResponseEntity<String>(response.toString(), HttpStatus.OK);

		}
	}

}
