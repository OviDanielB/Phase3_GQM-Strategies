package it.uniroma2.isssr.gqm3.model;


import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.ActivitiEntity;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.Execution;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.ProcessDefinition;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.Variable;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ActivitiEntityList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.ProcessDefinitionList;
import it.uniroma2.isssr.gqm3.dto.post.PostProcessInstance;
import it.uniroma2.isssr.gqm3.dto.post.PostQueryMessageIssueCatcher;
import it.uniroma2.isssr.gqm3.dto.put.PutVariables;
import it.uniroma2.isssr.gqm3.dto.response.ResponseDeployment;
import it.uniroma2.isssr.gqm3.dto.response.ResponseProcessInstance;
import it.uniroma2.isssr.gqm3.dto.response.ResponseQueryMessageIssueCatcher;
import it.uniroma2.isssr.gqm3.tools.JsonRequestActiviti;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a Workflow object with name, model id (picture id),
 * process definition (Workflow deployed) and process instance (Workflow in running)
 */
public abstract class Workflow {

    private String name;
    private String modelId;
    private String processDefinitionId;
    private String processInstanceId;


    public static final String MODEL_XML_EXT = ".bpmn20.xml";

    protected HostSettings hostSettings;

    protected JsonRequestActiviti jsonRequestActiviti;

    public Workflow(HostSettings hostSettings, String name) {
        super();
        this.name = name;
        this.hostSettings = hostSettings;
        this.jsonRequestActiviti = new JsonRequestActiviti(hostSettings);
    }


    public Workflow(HostSettings hostSettings) {
        this.hostSettings = hostSettings;
        this.jsonRequestActiviti = new JsonRequestActiviti(hostSettings);
    }

    public void start(String isStrategyUpdated) throws MetaWorkflowNotStartedException, JsonRequestException, JsonRequestConflictException {

        PostProcessInstance processInstanceBody = buildStartRequestBody(isStrategyUpdated);

        ResponseEntity<ResponseProcessInstance> postProcessInstanceResponse = this.jsonRequestActiviti.post(
                hostSettings.getActivitiRestEndpointProcessInstances(), processInstanceBody,
                ResponseProcessInstance.class);

        if (postProcessInstanceResponse.getBody() != null) {
            setProcessInstanceId(postProcessInstanceResponse.getBody().getId());
        }
        if (getProcessInstanceId() == null || getProcessInstanceId().isEmpty()) {
            throw new MetaWorkflowNotStartedException(getProcessDefinitionId());
        } else {
            return;
        }
    }


    abstract protected PostProcessInstance buildStartRequestBody(String isStrategyUpdated);


    public void deploy() throws JsonRequestException, MetaWorkflowNotDeployedException, ModelXmlNotFoundException,
            IOException, ProcessDefinitionNotFoundException {

        String metaworkflowString = buildWorkflowXmlString();

        ResponseEntity<ResponseDeployment> postDeploymentResponse = jsonRequestActiviti.postMetaWorkflowMultiPart(
                hostSettings.getActivitiRestEndpointDeployments(), ResponseDeployment.class, getName(),
                metaworkflowString);

        String deploymentId = postDeploymentResponse.getBody().getId();

        Map<String, String> queryParams = new LinkedHashMap<String, String>();
        queryParams.put("deploymentId", deploymentId);

        @SuppressWarnings("unchecked")
        List<ProcessDefinition> processDefinitionList = (List<ProcessDefinition>) jsonRequestActiviti
                .getList(
                        hostSettings.getActivitiRestEndpointProcessDefinitions(),
                        ProcessDefinitionList.class, queryParams);

        if (processDefinitionList.isEmpty()) {
            throw new ProcessDefinitionNotFoundException(deploymentId);
        } else {
            setProcessDefinitionId(processDefinitionList.get(0).getId());
        }
        return;
    }


    public void deleteProcessInstance() throws JsonRequestException {

        if (this.getProcessInstanceId() != null) {
            String restAddress = hostSettings.getActivitiRestEndpointProcessInstances() + "/" + this.getProcessInstanceId();

            jsonRequestActiviti.delete(restAddress);
        }
    }


    public void deleteDeployment() throws JsonRequestException {

        if (this.getProcessDefinitionId() != null) {
            String restAddressProcessDefinition = hostSettings.getActivitiRestEndpointProcessDefinitions() + "/" + this.getProcessDefinitionId();

            ResponseEntity<ProcessDefinition> postDeploymentResponse = jsonRequestActiviti
                    .get(restAddressProcessDefinition,
                            ProcessDefinition.class);

            ProcessDefinition processDefinition = postDeploymentResponse.getBody();

            if (processDefinition == null ||
                    processDefinition.getDeploymentId() == null ||
                    processDefinition.getDeploymentId().isEmpty()) {
                return;
            } else {

                String restAddressDeployment = hostSettings.getActivitiRestEndpointDeployments() + "/" + this.processDefinitionId;// processDefinition.getDeploymentId();

                deleteProcessInstance();

                jsonRequestActiviti.delete(restAddressDeployment);
            }
        }
    }


    abstract protected String buildWorkflowXmlString()
            throws JsonRequestException, ModelXmlNotFoundException, IOException;


    public void updateVariable(String key, String value) throws JsonRequestException {

        Variable modelIdVariable = new Variable();
        modelIdVariable.setName(key);
        modelIdVariable.setType("string");
        modelIdVariable.setValue(value);
        modelIdVariable.setScope("local");

        ArrayList<Variable> updateVariableBody = new ArrayList<Variable>();
        updateVariableBody.add(modelIdVariable);
        PutVariables putBody = new PutVariables();
        putBody.setVariables(updateVariableBody);

        String restAddress = hostSettings.getActivitiRestEndpointProcessInstances() + "/" + this.getProcessInstanceId()
                + "/variables";

        System.out.println(restAddress);

        jsonRequestActiviti.put(restAddress, updateVariableBody , String.class);

    }

    public void updateVariables(ArrayList<Variable> variables) throws JsonRequestException {

        String restAddress = hostSettings.getActivitiRestEndpointProcessInstances() + "/" + this.getProcessInstanceId()
                + "/variables";

        jsonRequestActiviti.put(restAddress, variables.toString(), String.class);

    }


    protected String getMessageCatcherId(String messageEventSubscriptionName) throws JsonRequestException, IssueMessageCatcherNotFoundException, JsonRequestConflictException {

        PostQueryMessageIssueCatcher postBody = new PostQueryMessageIssueCatcher();
        postBody.setMessageEventSubscriptionName(messageEventSubscriptionName);
        postBody.setProcessInstanceId(getProcessInstanceId());

        ResponseEntity<ResponseQueryMessageIssueCatcher> response = jsonRequestActiviti.post(
                hostSettings.getActivitiRestEndpointQueryExecutions(), postBody,
                ResponseQueryMessageIssueCatcher.class);

        String issueMessageCatcherId = null;
        List<Execution> executionList = response.getBody().getData();

        if (executionList != null && !executionList.isEmpty()) {
            issueMessageCatcherId = executionList.get(0).getId();
        } else {

            throw new IssueMessageCatcherNotFoundException(getProcessDefinitionId());
        }
        return issueMessageCatcherId;
    }


    public <T extends ActivitiEntityList> void checkAlreadyExists(String name, String restAddress, Class<T> T)
            throws ActivitiEntityAlreadyExistsException, JsonRequestException {

        System.out.println(restAddress);
        Map<String, String> queryParams = new LinkedHashMap<String, String>();
        queryParams.put("name", name);
        List<? extends ActivitiEntity> entityList = jsonRequestActiviti
                .getList(restAddress, T, queryParams);

        if (!entityList.isEmpty()) {
            throw new ActivitiEntityAlreadyExistsException(name, T);
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

}
