package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.Model;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ActivitiEntityList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.DeploymentList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ModelList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ProcessDefinitionList;
import it.uniroma2.isssr.gqm3.dto.post.PostModel;
import it.uniroma2.isssr.gqm3.dto.post.PostProcessInstance;
import it.uniroma2.isssr.gqm3.tools.JsonRequestActiviti;
import it.uniroma2.isssr.gqm3.tools.XmlTools;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BusinessWorkflow extends Workflow {

	private String metaWorkflowProcessInstanceId;

	public BusinessWorkflow(HostSettings hostSettings, String name) {
		super(hostSettings, name);
	}

	public BusinessWorkflow(HostSettings hostSettings) {
		super(hostSettings);
	}

	public void createModel()
			throws JsonRequestException, BusinessWorkflowNotCreatedException, JsonRequestConflictException {

		PostModel postModelBody = new PostModel();
		postModelBody.setName(super.getName());
		postModelBody.setKey("key " + super.getName());
		postModelBody.buildMetaInfo(super.getName());

		ResponseEntity<Model> businessWorkflowModelResponse = jsonRequestActiviti
				.post(hostSettings.getActivitiRestEndpointModels(), postModelBody, Model.class);

		if (businessWorkflowModelResponse.getBody() != null) {
			super.setModelId(businessWorkflowModelResponse.getBody().getId());
		}
		if (super.getModelId() == null || super.getModelId().isEmpty()) {
			throw new BusinessWorkflowNotCreatedException();
		} else {
			String metaWorkflowName = hostSettings.getMetaworkflowPrefix() + super.getName()
					+ hostSettings.getMetaworkflowSuffix();
			String restAddress = hostSettings.getActivitiRestEndpointModels() + "/" + super.getModelId() + "/source";

			jsonRequestActiviti.putMultiPart(restAddress, String.class, metaWorkflowName,
					Model.getDefaultModelSource(super.getName()));

			return;
		}
	}

	@Override
	protected PostProcessInstance buildStartRequestBody(String isStrategyUpdated) {
		PostProcessInstance processInstanceBody = new PostProcessInstance();
		processInstanceBody.setProcessDefinitionId(super.getProcessDefinitionId());
		processInstanceBody.setTenantId(getMetaWorkflowProcessInstanceId());
		return processInstanceBody;
	}

	@Override
	protected String buildWorkflowXmlString()
			throws JsonRequestException, ModelXmlNotFoundException, JsonProcessingException, IOException {

		JsonRequestActiviti jsonRequest = new JsonRequestActiviti(hostSettings);

		ResponseEntity<String> modelSourceResponse = jsonRequest
				.get(hostSettings.getActivitiRestEndpointModels() + "/" + super.getModelId() + "/source", String.class);

		String modelSource = modelSourceResponse.getBody();
		if (modelSource == null || modelSource.isEmpty()) {
			throw new ModelXmlNotFoundException();
		}
		String xmlString = XmlTools.modelSourceToXml(modelSource);

		if (xmlString == null || xmlString.isEmpty()) {
			throw new ModelXmlNotFoundException();
		}

		return xmlString;
	}

	public void checkAlreadyExist(String name)
			throws ActivitiEntityAlreadyExistsException, JsonRequestException {

		List<String> restAddresses = Arrays.asList(hostSettings.getActivitiRestEndpointModels(),
				hostSettings.getActivitiRestEndpointDeployments(),
				hostSettings.getActivitiRestEndpointProcessDefinitions());

		List<Class<? extends ActivitiEntityList>> entitylists = Arrays.asList(ModelList.class, DeploymentList.class,
				ProcessDefinitionList.class);

		for (int i = 0; i < restAddresses.size(); i++) {

			String restAddress = restAddresses.get(i);
			Class<? extends ActivitiEntityList> T = entitylists.get(i);
			super.checkAlreadyExists(name, restAddress, T );
		}
	}

	public String getMetaWorkflowProcessInstanceId() {
		return metaWorkflowProcessInstanceId;
	}

	public void setMetaWorkflowProcessInstanceId(String metaWorkflowProcessInstanceId) {
		this.metaWorkflowProcessInstanceId = metaWorkflowProcessInstanceId;
	}

}