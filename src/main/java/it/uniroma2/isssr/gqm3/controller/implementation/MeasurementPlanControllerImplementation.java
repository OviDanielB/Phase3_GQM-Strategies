package it.uniroma2.isssr.gqm3.controller.implementation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestException;
import it.uniroma2.isssr.gqm3.Exception.ProcessDefinitionImageNotFoundException;
import it.uniroma2.isssr.gqm3.controller.MeasurementPlanController;
import it.uniroma2.isssr.gqm3.dto.ErrorResponse;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.FlowElement;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.GroupActiviti;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.ProcessDefinitionModel;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.GroupActivitiList;
import it.uniroma2.isssr.gqm3.dto.response.ResponseMeasurementPlan;
import it.uniroma2.isssr.gqm3.model.MeasureTask;
import it.uniroma2.isssr.gqm3.model.Metric;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.repository.MeasureTaskRepository;
import it.uniroma2.isssr.gqm3.repository.MetricRepository;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;
import it.uniroma2.isssr.gqm3.tools.Costants;
import it.uniroma2.isssr.gqm3.tools.JsonRequestActiviti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Measurement Plan", description = "Measurement Plan API")
public class MeasurementPlanControllerImplementation implements MeasurementPlanController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(MeasurementPlanControllerImplementation.class);

    @Autowired
    private HostSettings hostSettings;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private MeasureTaskRepository measureTaskRepository;

    @Autowired
    private WorkflowDataRepository workflowDataRepository;

    @RequestMapping(value = "/measurement-plan", method = RequestMethod.GET)
    @ApiOperation(value = "Get a measurement plan", notes = "This endpoint returns a measurement plan that needs to fill" +
            " or returns a measurement plan that has already filled.")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<?> getMeasurementPlan(@RequestParam(value = "modelId") String modelId)
            throws JsonRequestException {

        List<WorkflowData> listStrategy = workflowDataRepository.findByBusinessWorkflowModelId(modelId);
        WorkflowData workflowData = listStrategy.get(0);

        JsonRequestActiviti jsonRequest = new JsonRequestActiviti(hostSettings);

        // Retrieve process definition from Activiti
        ResponseEntity<ProcessDefinitionModel> processDefinitionModel = jsonRequest.get(
                hostSettings.getActivitiRestEndpointProcessDefinitions() + "/"
                        + workflowData.getBusinessWorkflowProcessDefinitionId()
                        + hostSettings.getActivitiRestEndpointProcessDefinitionsModelSuffix(),
                ProcessDefinitionModel.class);

        List<FlowElement> tasksList = new ArrayList<FlowElement>();

        // Add a task
        for (FlowElement flowElement : processDefinitionModel.getBody().getProcesses().get(0).getFlowElements())
            if (flowElement.getName() != null)
                tasksList.add(flowElement);

        // Retrieve groups from Activiti
        @SuppressWarnings("unchecked")
        List<GroupActiviti> groupsList = (List<GroupActiviti>) jsonRequest
                .getList(hostSettings.getActivitiRestEndpointIdentityGroups(), GroupActivitiList.class);

        // Remove validator from groups list
        for (int i = 0; i < groupsList.size(); i++) {
            if (groupsList.get(i).getName().equals(Costants.VALIDATOR)) {
                groupsList.remove(groupsList.get(i));
            }
        }

        // Retrieve metric list from mongoDB
        List<Metric> metricsList = metricRepository.findAll();

        ResponseMeasurementPlan measurementPlanResponse = new ResponseMeasurementPlan();
        measurementPlanResponse.setMetrics(metricsList);
        measurementPlanResponse.setGroups(groupsList);
        measurementPlanResponse.setFlowElements(tasksList);

        // Measurement plan has already filled then return it
        if (workflowData.getMeasureTasksList() != null)
            measurementPlanResponse.setWorkflowData(workflowData);

        return new ResponseEntity<ResponseMeasurementPlan>(measurementPlanResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/measurement-plan", method = RequestMethod.POST)
    @ApiOperation(value = "Save a measurement plan", notes = "This endpoint saves a measurement plan.")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<?> saveMeasurementPlan(@RequestBody WorkflowData workflowData) throws JsonRequestException {

        // Retrieve workflowData
        List<WorkflowData> listStrategy = workflowDataRepository
                .findByBusinessWorkflowModelId(workflowData.getBusinessWorkflowModelId());
        WorkflowData s = listStrategy.get(0);

        // Set task list
        List<MeasureTask> measureTasksList = measureTaskRepository.save(workflowData.getMeasureTasksList());
        s.setMeasureTasksList(measureTasksList);
        // Save workflowData
        workflowDataRepository.save(s);

        return ResponseEntity.status(HttpStatus.OK).body("The measurement plan has been successfully saved");

    }

    @RequestMapping(value = "/measurement-plan/image", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation(value = "Get a workflow image", notes = "This endpoint returns an image of a workflow that requires a measurement plan.")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public
    @ResponseBody
    byte[] getMeasurementPlanImage(@RequestParam(value = "modelId") String modelId)
            throws JsonRequestException, ProcessDefinitionImageNotFoundException {

        // Retrieve workflowData
        List<WorkflowData> listStrategy = workflowDataRepository.findByBusinessWorkflowModelId(modelId);
        WorkflowData workflowData = listStrategy.get(0);

        // Return a link to show workflow image
        String link = hostSettings.getActivitiRestEndpointProcessDefinitions() + "/"
                + workflowData.getBusinessWorkflowProcessDefinitionId()
                + hostSettings.getActivitiRestEndpointProcessDefinitionsImageSuffix();

        JsonRequestActiviti jsonRequest = new JsonRequestActiviti(hostSettings);
        ResponseEntity<byte[]> imageResponseEntity = jsonRequest.get(link, byte[].class);
        if (imageResponseEntity.getBody() == null)
            throw new ProcessDefinitionImageNotFoundException(workflowData.getBusinessWorkflowProcessDefinitionId());
        byte[] image = imageResponseEntity.getBody();
        return image;

    }
}
