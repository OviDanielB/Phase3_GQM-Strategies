package it.uniroma2.isssr.gqm3.controller;

import it.uniroma2.isssr.gqm3.Exception.JsonRequestException;
import it.uniroma2.isssr.gqm3.Exception.ProcessDefinitionImageNotFoundException;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface MeasurementPlanController {

    /**
     * @param modelId model Workflow id
     * @return a measurement plan if it's not completed
     * @throws JsonRequestException
     */
    @RequestMapping(value = "/measurement-plan", method = RequestMethod.GET)
    public ResponseEntity<?> getMeasurementPlan(@RequestParam(value = "modelId") String modelId)
            throws JsonRequestException;

    /**
     * Save/Update workflowDate with measurement plan executed
     *
     * @param workflowData
     * @return 200OK if measurement plan is saved successfully
     * @throws JsonRequestException
     */
    @RequestMapping(value = "/measurement-plan", method = RequestMethod.POST)
    public ResponseEntity<?> saveMeasurementPlan(@RequestBody WorkflowData workflowData) throws JsonRequestException;

    /**
     * @param modelId model Workflow id
     * @return Activiti url of Workflow Model image for the front-end
     * visualization
     * @throws ProcessDefinitionImageNotFoundException
     * @throws JsonRequestException
     */
    @RequestMapping(value = "/measurement-plan/image", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public
    @ResponseBody
    byte[] getMeasurementPlanImage(@RequestParam(value = "modelId") String modelId) throws JsonRequestException, ProcessDefinitionImageNotFoundException;
}
