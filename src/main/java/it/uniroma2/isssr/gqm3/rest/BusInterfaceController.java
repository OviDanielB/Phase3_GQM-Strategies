package it.uniroma2.isssr.gqm3.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.dto.IssueMessage;
import it.uniroma2.isssr.gqm3.dto.bus.BusNotification;
import it.uniroma2.isssr.gqm3.dto.post.PostWorkflowToBeSaved;
import it.uniroma2.isssr.integrazione.BusException;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BusInterfaceController {

    /**
     * This is the endpoint to do the export of a workflow
     *
     * @param workflowToBeSaved Contains the parameters of the workflow wanted to be saved to
     *                          the bus (modelId and name)
     * @param response          The HttpServletResponse
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     * @throws ModelXmlNotFoundException
     * @throws JSONException
     * @throws BusException
     * @throws BusRequestException
     */
    @RequestMapping(value = "/bus/workflows", method = RequestMethod.POST)
    public ResponseEntity<?> saveWorkflow(@RequestBody PostWorkflowToBeSaved workflowToBeSaved,
                                          HttpServletResponse response) throws JsonProcessingException, IOException, ModelXmlNotFoundException,
            JSONException, BusException, BusRequestException;

    /**
     * This is the endpoint to get all workflows xml from the bus
     *
     * @return
     * @throws BusException
     * @throws IOException
     */
    @RequestMapping(value = "/bus/workflows", method = RequestMethod.GET)
    public ResponseEntity<?> getWorkflows() throws BusException, IOException;

    /**
     * This is the endpoint to do the system initialization
     *
     * @param response The HttpServletResponse
     * @return 200 OK if the initialization is done without any problems.
     * @throws IOException
     * @throws JsonRequestException
     * @throws JSONException
     * @throws BusRequestException
     * @throws ParseException
     * @throws BusException
     * @throws JsonRequestConflictException
     */
    @RequestMapping(value = "/bus/phaseInit", method = RequestMethod.POST)
    public ResponseEntity<?> phaseInit(HttpServletResponse response) throws IOException, JsonRequestException,
            JSONException, BusRequestException, ParseException, BusException, JsonRequestConflictException;

    /*This feature maybe do not work on integration bus */
    /**
     * This is the endpoint to manage all notifications received from bus
     *
     * @param busNotification The nofication received from bus
     * @param response        The HttpServletResponse
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws JSONException
     * @throws IOException
     * @throws BusRequestException
     * @throws BusException
     * @throws ParseException
     * @throws JsonRequestException
     * @throws IllegalReceiveMessageRequestBodyException
     * @throws IssueMessageCatcherNotFoundException
     * @throws WorkflowDataException
     * @throws JsonRequestConflictException
     */
    @RequestMapping(value = "/bus/notifications", method = RequestMethod.POST)
    public ResponseEntity<?> receiveNotification(@RequestBody BusNotification busNotification,
                                                 HttpServletResponse response)
            throws JsonParseException, JsonMappingException, JSONException, IOException, BusRequestException,
            BusException, ParseException, JsonRequestException, IllegalReceiveMessageRequestBodyException,
            IssueMessageCatcherNotFoundException, WorkflowDataException, JsonRequestConflictException, IllegalSaveWorkflowRequestBodyException, ModelXmlNotFoundException;

    /**
     * Not Implemented
     *
     * @param message  Not Implemented
     * @param response Not Implemented
     * @return Not Implemented
     */
    @RequestMapping(value = "/bus/messages", method = RequestMethod.POST)
    public ResponseEntity<?> receiveMessage(@RequestBody String message, HttpServletResponse response);

    /**
     * This is the endpoint to get all IssueMessages sent to the bus
     *
     * @return
     * @throws BusException
     * @throws IOException
     */
    @RequestMapping(value = "/bus/issueMessages", method = RequestMethod.GET)
    public ResponseEntity<?> getIssueMessages() throws BusException, IOException;

    @RequestMapping(value = "/bus/issueMessages", method = RequestMethod.POST)
    public ResponseEntity<?> insertIssueMessage(@RequestParam(value = "instance") String instance,
                                                @RequestBody IssueMessage issueMessage, HttpServletResponse response)
            throws JSONException, BusException, IOException;

}