package it.uniroma2.isssr.gqm3.rest.implementation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.BusRequestException;
import it.uniroma2.isssr.gqm3.Exception.JsonRequestException;
import it.uniroma2.isssr.gqm3.rest.MeasurementPlanController;
import it.uniroma2.isssr.gqm3.dto.ErrorResponse;
import it.uniroma2.isssr.gqm3.model.MeasureTask;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.repository.MeasureTaskRepository;
import it.uniroma2.isssr.gqm3.repository.MetricRepository;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;
import it.uniroma2.isssr.gqm3.service.implementation.BusService2Phase4Implementation;
import it.uniroma2.isssr.integrazione.BusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @Autowired
    private BusService2Phase4Implementation busService2Phase4Implementation;


    @RequestMapping(value = "/measurement-plan", method = RequestMethod.POST)
    @ApiOperation(value = "Save a measurement plan", notes = "This endpoint saves a measurement plan.")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<?> saveMeasurementPlan(@RequestBody WorkflowData workflowData) throws JsonRequestException, BusRequestException, BusException, IOException {


        // Retrieve workflowData
        List<WorkflowData> listStrategy = workflowDataRepository
                .findByBusinessWorkflowModelId(workflowData.getBusinessWorkflowModelId());
        WorkflowData s = listStrategy.get(0);


        // Set task list
        List<MeasureTask> measureTasksList = measureTaskRepository.save(workflowData.getMeasureTasksList());
        s.setMeasureTasksList(measureTasksList);
        // Save workflowData on local mongodb
        workflowDataRepository.save(s);

        // save on bus

        busService2Phase4Implementation.saveWorkflowData(workflowData);


        return ResponseEntity.status(HttpStatus.OK).body("The measurement plan has been successfully saved");

    }
}