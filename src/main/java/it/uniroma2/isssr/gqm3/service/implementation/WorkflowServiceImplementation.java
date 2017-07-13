package it.uniroma2.isssr.gqm3.service.implementation;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.BusinessWorkflow;
import it.uniroma2.isssr.gqm3.model.MetaWorkflow;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author emanuele
 */
@Service
public class WorkflowServiceImplementation {

    private static final char[] ILLEGAL_CHARACTERS = {'/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>',
            '|', '\"', ':', '.'};

    @Autowired
    private HostSettings hostSettings;

    @Autowired
    private WorkflowDataRepository workflowDataRepository;

    @Autowired
    private BusService2Phase4Implementation busService2Phase4Implementation;


    public ResponseEntity<String> createWorkflow(String workflowName) throws IllegalCharacterRequestException, JsonRequestException, ActivitiEntityAlreadyExistsException, ModelXmlNotFoundException, ProcessDefinitionNotFoundException, MetaWorkflowNotDeployedException, MetaWorkflowNotStartedException, JsonRequestConflictException, BusinessWorkflowNotCreatedException {

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
        try {
            metaWorkflow.deploy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        metaWorkflow.start();

        businessWorkflow.setMetaWorkflowProcessInstanceId(metaWorkflow.getProcessInstanceId());
        businessWorkflow.createModel();
        metaWorkflow.updateVariable("businessWorkflowModelId", businessWorkflow.getModelId());

        WorkflowData workflowData = new WorkflowData();
        workflowData.setBusinessWorkflowName(businessWorkflow.getName());
        workflowData.setBusinessWorkflowModelId(businessWorkflow.getModelId());
        workflowData.setMetaWorkflowName(metaWorkflow.getName());
        workflowData.setMetaWorkflowProcessInstanceId(metaWorkflow.getProcessInstanceId());
        workflowData.setMeasureTasksList(new ArrayList<>());

		/* save on local mongodb */
        workflowDataRepository.save(workflowData);

        /* save on bus */
        try {
            busService2Phase4Implementation.saveWorkflowData(workflowData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject response = new JSONObject();
        response.put("metaWorkflowProcessInstanceId", metaWorkflow.getProcessInstanceId());
        response.put("businessWorkflowModelId", businessWorkflow.getModelId());

        return new ResponseEntity<>(response.toString(), HttpStatus.CREATED);
    }
}
