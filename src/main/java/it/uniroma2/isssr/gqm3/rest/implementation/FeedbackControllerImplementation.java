package it.uniroma2.isssr.gqm3.rest.implementation;

import io.swagger.annotations.Api;
import it.uniroma2.isssr.gqm3.model.*;
import it.uniroma2.isssr.gqm3.repository.MeasureTaskRepository;
import it.uniroma2.isssr.gqm3.service.implementation.BusService2Phase4Implementation;
import it.uniroma2.isssr.integrazione.BusException;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.rest.FeedbackController;
import it.uniroma2.isssr.gqm3.dto.EndingMessage;
import it.uniroma2.isssr.gqm3.dto.ErrorResponse;
import it.uniroma2.isssr.gqm3.dto.IssueMessage;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@Api(value = "Feedback Controller", description = "Feedback Controller API")
@CrossOrigin(origins = "*")
public class FeedbackControllerImplementation implements FeedbackController {

    @Autowired
    private HostSettings hostSettings;

    @Autowired
    private WorkflowDataRepository workflowDataRepository;

    @Autowired
    private MeasureTaskRepository measureTaskRepository;

    @Autowired
//    private BusService2Phase4Implementation busService2Phase4Implementation;
    private BusInterfaceControllerImplementation busInterfaceControllerImplementation;

    private void receiveMessage(WorkflowMessage workflowMessage)
            throws IllegalReceiveMessageRequestBodyException,
            JsonRequestException, IssueMessageCatcherNotFoundException,
            WorkflowDataException, JsonRequestConflictException {

        String taskId = workflowMessage.getTaskId();

        List<MeasureTask> measureTasks = measureTaskRepository.findByTaskId(taskId);
        MeasureTask measureTask = measureTasks.get(0);
        List<WorkflowData> workflowDataList = workflowDataRepository.findAll();
        WorkflowData workflowData = new WorkflowData();
        for (WorkflowData wd : workflowDataList) {
            if (wd.getMeasureTasksList().indexOf(measureTask) != 0) {
                workflowData = wd;
            }
        }

        workflowMessage.setBusinessWorkflowProcessInstanceId(workflowData.getBusinessWorkflowProcessInstanceId());

        String metaWorkflowProcessInstanceId = workflowData.getMetaWorkflowProcessInstanceId();

        MetaWorkflow metaWorkflow = new MetaWorkflow(hostSettings,
                metaWorkflowProcessInstanceId);
        metaWorkflow.sendMessage(workflowMessage.getActivitiMessageType(),
                workflowMessage.getMessageBody());

    }

    public void receiveFeedbackMessage(IssueMessage issueMessage)
            throws IllegalReceiveMessageRequestBodyException,
            JsonRequestException, IssueMessageCatcherNotFoundException,
            WorkflowDataException, JsonRequestConflictException, IOException, BusRequestException, BusException, ModelXmlNotFoundException, IllegalSaveWorkflowRequestBodyException {

        WorkflowMessage workflowMessage = new WorkflowMessage(issueMessage);

        receiveMessage(workflowMessage);

        //TODO end a business workflow if
//        BusinessWorkflow businessWorkflow = new BusinessWorkflow(hostSettings);
//        businessWorkflow.setProcessInstanceId(issueMessage
//                .getBusinessWorkflowProcessInstanceId());
//        businessWorkflow.deleteProcessInstance();

        List<WorkflowData> workflowDatas = workflowDataRepository.findByBusinessWorkflowProcessInstanceId(workflowMessage
                .getBusinessWorkflowProcessInstanceId());
        if (workflowDatas.size() != 1) {
            throw new WorkflowDataException();
        }

        WorkflowData workflowData = workflowDatas.get(0);
        workflowData.setBusinessWorkflowProcessInstanceId(null);

        /* save on local mongodb*/
        workflowDataRepository.save(workflowData);

        /* save on bus*/
        busInterfaceControllerImplementation.saveWorkflowData(workflowData);
    }

    @RequestMapping(value = "/BusinessIssueMessages", method = RequestMethod.POST)
    @ApiOperation(value = "Receive Issue Messages", notes = "This endpoint receives an issue message")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<String> receiveIssueMessage(@RequestBody IssueMessage issueMessage, HttpServletResponse response)
            throws WorkflowDataException, IssueMessageCatcherNotFoundException,
            IllegalReceiveMessageRequestBodyException, JsonRequestException,
            JsonRequestConflictException, IOException, BusRequestException, BusException, ModelXmlNotFoundException, IllegalSaveWorkflowRequestBodyException {

        receiveFeedbackMessage(issueMessage);

        JSONObject json = new JSONObject();
        json.put("result", "ok");
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/BusinessEndingMessages", method = RequestMethod.POST)
    @ApiOperation(value = "Receive Ending Messages", notes = "This endpoint receives an ending message and terminates the specificated workflow")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<String> receiveEndingMessage(@RequestBody EndingMessage endingMessage, HttpServletResponse response)
            throws WorkflowDataException,
            IssueMessageCatcherNotFoundException, JsonRequestException,
            IllegalReceiveMessageRequestBodyException,
            JsonRequestConflictException, IOException, BusRequestException, BusException, ModelXmlNotFoundException, IllegalSaveWorkflowRequestBodyException {

        WorkflowMessage workflowMessage = new WorkflowMessage(endingMessage);

        receiveMessage(workflowMessage);

        String businessWorkflowProcessInstanceId = workflowMessage.getBusinessWorkflowProcessInstanceId();

        BusinessWorkflow businessWorkflow = new BusinessWorkflow(hostSettings);
        businessWorkflow.setProcessInstanceId(businessWorkflowProcessInstanceId);
        businessWorkflow.deleteProcessInstance();
        businessWorkflow.deleteDeployment();

        List<WorkflowData> workflowDatas = workflowDataRepository.findByBusinessWorkflowProcessInstanceId(businessWorkflowProcessInstanceId);
        if (workflowDatas.size() != 1) {
            throw new WorkflowDataException();
        }

        WorkflowData workflowData = workflowDatas.get(0);
        workflowData.setMetaWorkflowProcessInstanceId(null);
        workflowData.setBusinessWorkflowProcessInstanceId(null);
        workflowData.setBusinessWorkflowProcessDefinitionId(null);

        /* save on local mongodb */
        workflowDataRepository.save(workflowData);

        /* update workflows on bus */
        busInterfaceControllerImplementation.saveWorkflowData(workflowData);

        JSONObject json = new JSONObject();
        json.put("result", "ok");
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

}
