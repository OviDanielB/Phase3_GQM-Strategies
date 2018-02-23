package it.uniroma2.isssr.gqm3.rest;

import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.integrazione.BusException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

public interface MeasurementPlanController {


    /**
     * Save/Update workflowDate with measurement plan executed
     *
     * @param workflowData
     * @return 200OK if measurement plan is saved successfully
     * @throws JsonRequestException
     */
    @RequestMapping(value = "/measurement-plan", method = RequestMethod.POST)
    ResponseEntity<?> saveMeasurementPlan(@RequestBody WorkflowData workflowData) throws JsonRequestException, BusRequestException, BusException, IOException, IllegalSaveWorkflowRequestBodyException, ModelXmlNotFoundException;

}
