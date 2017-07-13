package it.uniroma2.isssr.gqm3.rest.implementation;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.rest.BusInterfaceController;
import it.uniroma2.isssr.gqm3.dto.ErrorResponse;
import it.uniroma2.isssr.gqm3.dto.IssueMessage;
import it.uniroma2.isssr.gqm3.dto.IssueMessageResource;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.GroupActiviti;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.UserActiviti;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.GroupActivitiList;
import it.uniroma2.isssr.gqm3.dto.activiti.entitylist.UserActivitiList;
import it.uniroma2.isssr.gqm3.dto.bus.BusData;
import it.uniroma2.isssr.gqm3.dto.bus.BusIssueMessage;
import it.uniroma2.isssr.gqm3.dto.bus.BusNotification;
import it.uniroma2.isssr.gqm3.dto.bus.BusReadResponse;
import it.uniroma2.isssr.gqm3.dto.post.PostAssignUserToGroup;
import it.uniroma2.isssr.gqm3.dto.post.PostWorkflowToBeSaved;
import it.uniroma2.isssr.gqm3.dto.response.ResponseGetIssueMessages;
import it.uniroma2.isssr.gqm3.model.*;
import it.uniroma2.isssr.gqm3.repository.MetricRepository;
import it.uniroma2.isssr.gqm3.repository.SystemStateRepository;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;
import it.uniroma2.isssr.gqm3.tools.JsonRequestActiviti;
import it.uniroma2.isssr.gqm3.tools.XmlTools;
import it.uniroma2.isssr.integrazione.BusException;
import it.uniroma2.isssr.integrazione.BusMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

@RestController
@Api(value = "busInterface", description = "Bus Interface API")
public class BusInterfaceControllerImplementation implements
        BusInterfaceController {

    private final Logger LOG = LoggerFactory
            .getLogger(BusInterfaceControllerImplementation.class);

    private final HostSettings hostSettings;

    private final MetricRepository metricRepository;

    private final SystemStateRepository systemStateRepository;

    private final WorkflowDataRepository workflowDataRepository;

    @Autowired
    private FeedbackControllerImplementation feedbackControllerImplementation;

    @Autowired
    public BusInterfaceControllerImplementation(HostSettings hostSettings, MetricRepository metricRepository, SystemStateRepository systemStateRepository, WorkflowDataRepository workflowDataRepository) {
        this.hostSettings = hostSettings;
        this.metricRepository = metricRepository;
        this.systemStateRepository = systemStateRepository;
        this.workflowDataRepository = workflowDataRepository;
    }

    @RequestMapping(value = "/bus/notifications", method = RequestMethod.POST)
    @ApiOperation(value = "Receive Notifications", notes = "This endpoint manages all notifications from bus. Supported notifications are: Creating of some new users, "
            + "Delete of any user, Insert of new metrics, Feedback messages from phases 5-6")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<BusNotification> receiveNotification(
            @RequestBody BusNotification busNotification,
            HttpServletResponse response) throws JsonParseException,
            JsonMappingException, JSONException, IOException,
            BusRequestException, BusException, ParseException,
            JsonRequestException, IllegalReceiveMessageRequestBodyException,
            IssueMessageCatcherNotFoundException, WorkflowDataException,
            JsonRequestConflictException {

        String data = busNotification.getData();

        if (data != null) {

            ObjectMapper objectMapper = new ObjectMapper();
            BusData busData = objectMapper.readValue(data, BusData.class);

            if (busData.getTypeObj() != null) {
                String typeObj = busData.getTypeObj();
                String operation = busData.getOperation();

                if (typeObj.equals(Metric.class.getSimpleName())) {
                    refreshMetrics();
                } else if (typeObj.equals(hostSettings
                        .getUserCreateTypeObject())
                        || typeObj.equals(hostSettings
                        .getUserDeleteTypeObject())) {
                    refreshUsers();
                } else if (typeObj.equals("base64-"
                        + IssueMessage.class.getSimpleName())
                        && operation.equals(BusMessage.OPERATION_CREATE)) {
                    // Reading IssueMessage from BUS
                    String instance = busData.getInstance();
                    String phase = busData.getPhase();
                    JSONObject readBody = new JSONObject();
                    readBody.put("objIdLocalToPhase", "");
                    readBody.put("typeObj",
                            "base64-" + IssueMessage.class.getSimpleName());
                    readBody.put("instance", instance);
                    readBody.put("busVersion", "");
                    readBody.put("tags", "[]");
                    BusMessage readIssueMessage = new BusMessage(
                            BusMessage.OPERATION_READ, phase,
                            readBody.toString());
                    String readResponseString = readIssueMessage
                            .send(hostSettings.getBusUri());
                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println(readResponseString);
                    List<BusReadResponse> readResponses = mapper.readValue(
                            readResponseString,
                            mapper.getTypeFactory().constructCollectionType(
                                    List.class, BusReadResponse.class));
                    BusReadResponse readResponse = readResponses.get(0);
                    if (readResponse == null) {
                        throw new BusException("Response is null!");
                    } else if (!readResponse.getErr().equals("0")) {
                        throw new BusException("Err: " + readResponse.getErr()
                                + " msg: " + readResponse.getMsg());
                    }
                    String busIssueMessageString = mapper.treeToValue(
                            readResponse.getPayload(), String.class);
                    BusIssueMessage busIssueMessage = mapper.readValue(
                            busIssueMessageString, BusIssueMessage.class);
                    if (busIssueMessage == null) {
                        throw new BusException("issueMessage is null!");
                    }
                    IssueMessage issueMessage = new IssueMessage();
                    issueMessage
                            .setBusinessWorkflowProcessInstanceId(busIssueMessage
                                    .getBusinessWorkflowInstanceId());
                    issueMessage.setIssueMessage(busIssueMessage
                            .getIssueMessage());
                    if (busIssueMessage.getIssueMessageResources() != null
                            && !busIssueMessage.getIssueMessageResources()
                            .isEmpty()) {
                        List<IssueMessageResource> issueMessageResources = mapper
                                .readValue(
                                        busIssueMessage
                                                .getIssueMessageResources(),
                                        mapper.getTypeFactory()
                                                .constructCollectionType(
                                                        List.class,
                                                        IssueMessageResource.class));
                        issueMessage
                                .setIssueMessageResources(issueMessageResources);

                    }

                    feedbackControllerImplementation.receiveFeedbackMessage(issueMessage);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(busNotification);
    }

    @RequestMapping(value = "/bus/issueMessages", method = RequestMethod.GET)
    @ApiOperation(value = "Get Issue Messages", notes = "This endpoint retrieves all issue messages from bus")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<ArrayList<ResponseGetIssueMessages>> getIssueMessages()
            throws BusException, IOException {
        JSONObject readBody = new JSONObject();
        readBody.put("objIdLocalToPhase", "");
        readBody.put("typeObj", "base64-" + IssueMessage.class.getSimpleName());
        readBody.put("instance", "");
        readBody.put("busVersion", "");
        readBody.put("tags", "[]");

        BusMessage busMessage = new BusMessage(BusMessage.OPERATION_READ,
                "phase5", readBody.toString());
        String busResponse;
        busResponse = busMessage.send(hostSettings.getBusUri());

        ObjectMapper mapper = new ObjectMapper();
        List<BusReadResponse> readResponseList = mapper.readValue(
                busResponse,
                mapper.getTypeFactory().constructCollectionType(List.class,
                        BusReadResponse.class));

        ArrayList<ResponseGetIssueMessages> responseArray = new ArrayList<>();
        for (BusReadResponse response : readResponseList) {
            String busIssueMessageString = mapper.treeToValue(
                    response.getPayload(), String.class);
            BusIssueMessage busIssueMessage = mapper.readValue(
                    busIssueMessageString, BusIssueMessage.class);

            ResponseGetIssueMessages element = new ResponseGetIssueMessages();
            element.setBusinessWorkflowInstanceId(busIssueMessage
                    .getBusinessWorkflowInstanceId());
            element.setIssueMessage(busIssueMessage.getIssueMessage());
            List<IssueMessageResource> listIssueMessageResources = new ArrayList<>();
            if (busIssueMessage.getIssueMessageResources() != null
                    && !busIssueMessage.getIssueMessageResources().isEmpty()) {
                List<IssueMessageResource> issueMessageResources = mapper
                        .readValue(
                                busIssueMessage.getIssueMessageResources(),
                                mapper.getTypeFactory()
                                        .constructCollectionType(List.class,
                                                IssueMessageResource.class));
                listIssueMessageResources.addAll(issueMessageResources);

            }
            String resourcesString = "";
            for (IssueMessageResource resource : listIssueMessageResources) {
                resourcesString += "Resource: " + resource.getName() + "<br>";
                resourcesString += "Link: " + resource.getUrl() + "<br>";
                resourcesString += "Description: " + resource.getDescription()
                        + "<br>";
            }
            element.setIssueMessageResources(resourcesString);

            String businessName = "";
            List<WorkflowData> workflowDatas = workflowDataRepository
                    .findByBusinessWorkflowProcessInstanceId(busIssueMessage
                            .getBusinessWorkflowInstanceId());
            if (workflowDatas.size() > 0) {
                WorkflowData workflowData = workflowDatas.get(0);
                businessName = workflowData.getBusinessWorkflowName();
            }
            element.setBusinessWorkflowName(businessName);

            responseArray.add(element);
        }
        return new ResponseEntity<ArrayList<ResponseGetIssueMessages>>(
                responseArray, HttpStatus.OK);

    }

    @RequestMapping(value = "/bus/issueMessages", method = RequestMethod.POST)
    @ApiOperation(value = "Insert Issue Message", notes = "This endpoint creates an issue message")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<String> insertIssueMessage(
            @RequestParam(value = "instance") String instance,
            @RequestBody IssueMessage issueMessage, HttpServletResponse response)
            throws JSONException, BusException, IOException {

        BusIssueMessage busIssueMessage = new BusIssueMessage();
        busIssueMessage.setIssueMessage(issueMessage.getIssueMessage());
        busIssueMessage.setBusinessWorkflowInstanceId(issueMessage
                .getBusinessWorkflowProcessInstanceId());
        busIssueMessage.setIssueMessageResources(new JSONArray(issueMessage
                .getIssueMessageResources()).toString());

        JSONObject jo = new JSONObject();
        jo.put("objIdLocalToPhase", instance);
        jo.put("typeObj", "base64-" + IssueMessage.class.getSimpleName());
        jo.put("instance", instance);
        jo.put("tags", "[]");
        jo.put("payload", new JSONObject(busIssueMessage).toString());

        System.out.println(jo.toString());

        BusMessage busMessage = new BusMessage(BusMessage.OPERATION_CREATE,
                "phase5", jo.toString());
        String busResponse = busMessage.send(hostSettings.getBusUri());
        return ResponseEntity.status(HttpStatus.OK).body(busResponse);
    }

    @RequestMapping(value = "/bus/messages", method = RequestMethod.POST)
    @ApiOperation(value = "Receive Message", notes = "This endpoint receive a message")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<String> receiveMessage(@RequestBody String message,
                                                 HttpServletResponse response) {
        // Not implemented
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @RequestMapping(value = "/bus/workflows", method = RequestMethod.POST)
    @ApiOperation(value = "Save Workflow", notes = "This endpoint saves a workflow definition")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<?> saveWorkflow(
            @RequestBody PostWorkflowToBeSaved workflowToBeSaved,
            HttpServletResponse response) throws JsonProcessingException,
            IOException, ModelXmlNotFoundException, JSONException,
            BusException, BusRequestException {
        try {

            checkRequestBody(workflowToBeSaved);

            JsonRequestActiviti jsonRequest = new JsonRequestActiviti(
                    hostSettings);

            ResponseEntity<String> modelSourceResponse = jsonRequest.get(
                    hostSettings.getActivitiRestEndpointModels() + "/"
                            + workflowToBeSaved.getModelId() + "/source",
                    String.class);

            String modelSource = modelSourceResponse.getBody();
            String xml = XmlTools.modelSourceToXml(modelSource);
            if (xml == null) {
                LOG.error("xml is null");
                throw new ModelXmlNotFoundException();
            }
            workflowToBeSaved.setXml(xml);

            if (hostSettings.isBus()) {

                JSONObject payload = new JSONObject();
                String encoded = Base64.getEncoder().encodeToString(
                        xml.getBytes("utf-8"));
                // payload.put("xml", encoded);
                payload.put("name", workflowToBeSaved.getName());
                payload.put("xml", encoded);
                payload.put("encoded", "base64");
                String pl = payload.toString();
                // String pl = payload.toString();
                JSONObject jo = new JSONObject();
                jo.put("objIdLocalToPhase", workflowToBeSaved.getModelId());
                jo.put("typeObj", "WorkflowXml");
                jo.put("instance", workflowToBeSaved.getName());
                jo.put("tags", "[]");
                jo.put("payload", pl);

                BusMessage busMessage = new BusMessage(
                        BusMessage.OPERATION_UPDATE,
                        hostSettings.getPhaseName(), jo.toString());
                String busResponse = busMessage.send(hostSettings.getBusUri());

                BusReadResponse busResponseParsed;

                ObjectMapper mapper = new ObjectMapper();
                busResponseParsed = mapper.readValue(busResponse,
                        BusReadResponse.class);
                if (!busResponseParsed.getErr().equals("0")) {
                    busMessage = new BusMessage(BusMessage.OPERATION_CREATE,
                            hostSettings.getPhaseName(), jo.toString());
                    busResponse = busMessage.send(hostSettings.getBusUri());
                    busResponseParsed = mapper.readValue(busResponse,
                            BusReadResponse.class);
                    if (!busResponseParsed.getErr().equals("0")) {
                        throw new BusRequestException(
                                busResponseParsed.getErr());
                    }
                }
                return new ResponseEntity<String>(busResponse, HttpStatus.OK);
            } else
                System.out.println(xml);

            BusReadResponse busResponseParsed = new BusReadResponse();
            busResponseParsed.setInstance(workflowToBeSaved.getName());
            return new ResponseEntity<BusReadResponse>(busResponseParsed,
                    HttpStatus.OK);

        } catch (JsonRequestException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                    e.getMessage());
        } catch (IllegalSaveWorkflowRequestBodyException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    e.getMessage());
        }
    }

    @RequestMapping(value = "/bus/workflows", method = RequestMethod.GET)
    @ApiOperation(value = "Get Workflows", notes = "This endpoint retrieves the workflows previously exported")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<ArrayList<XmlWorkflow>> getWorkflows()
            throws BusException, IOException {
        if (hostSettings.isBus()) {
            // String pl = payload.toString();
            JSONObject jo = new JSONObject();
            jo.put("objIdLocalToPhase", "");
            jo.put("typeObj", "WorkflowXml");
            jo.put("instance", "");
            jo.put("busVersion", "");
            jo.put("tags", "[]");

            BusMessage busMessage = new BusMessage(BusMessage.OPERATION_READ,
                    hostSettings.getPhaseName(), jo.toString());
            String busResponse;
            busResponse = busMessage.send(hostSettings.getBusUri());
            System.out.println(busResponse);

            ObjectMapper mapper = new ObjectMapper();
            List<BusReadResponse> readResponseList = mapper.readValue(
                    busResponse,
                    mapper.getTypeFactory().constructCollectionType(List.class,
                            BusReadResponse.class));

            ArrayList<XmlWorkflow> xmlList = new ArrayList<>();
            for (BusReadResponse response : readResponseList) {
                XmlWorkflow xmlWorkflow = mapper.treeToValue(
                        response.getPayload(), XmlWorkflow.class);

                xmlWorkflow.setXml(new String(Base64.getDecoder().decode(
                        xmlWorkflow.getXml()), "utf-8").replace("\n", "")
                        .replace("\r", ""));
                xmlList.add(xmlWorkflow);
            }

            return new ResponseEntity<ArrayList<XmlWorkflow>>(xmlList,
                    HttpStatus.OK);
        }

        throw new BusException("ERROR: BUS Not present!");

    }

    @RequestMapping(value = "/bus/phaseInit", method = RequestMethod.POST)
    @ApiOperation(value = "Phase 3-4 Initialization", notes = "This endpoint performs the phase initialization")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class)})
    public ResponseEntity<String> phaseInit(HttpServletResponse response)
            throws IOException, JsonRequestException, JSONException,
            BusRequestException, ParseException, BusException,
            JsonRequestConflictException {

        systemStateRepository.deleteAll();
        refreshUsers();
        refreshMetrics();

        return ResponseEntity.status(HttpStatus.OK).body("Done.");

    }

    /**
     * This method refresh the list of system users.
     *
     * @throws JsonRequestException
     * @throws JSONException
     * @throws IOException
     * @throws BusRequestException
     * @throws ParseException
     * @throws BusException
     * @throws JsonRequestConflictException
     */
    private void refreshUsers() throws JsonRequestException, JSONException,
            IOException, BusRequestException, ParseException, BusException,
            JsonRequestConflictException {

        JsonRequestActiviti jsonRequest = new JsonRequestActiviti(hostSettings);

        // Retrieve Users from Activiti
        List<UserActiviti> listUsersActiviti;
        listUsersActiviti = getUsersFromActiviti(jsonRequest);

        // Retrieve Groups from Activiti
        List<GroupActiviti> listGroupActiviti = getGroupsFromActiviti(jsonRequest);

        // Retrieve Users from BUS/file
        List<User> users = getUsers(jsonRequest);

        if (listUsersActiviti.size() == 0) {
            // Add users to activity, case: no users in Activiti yet
            createUsersActivitiFirstTime(users, jsonRequest);
        } else {
            // Add users to activity, existing users in Activiti
            createUsersActiviti2(users, listUsersActiviti, jsonRequest);

        }

        // Create array of Group from BUS/link
        ArrayList<String> arrayGroups = createGroupsArray(users);

        // Add Groups to Activiti
        createGroupsActiviti(arrayGroups, jsonRequest, listGroupActiviti, users);

    }

    /**
     * This method refresh the list of metrics. The metrics are pulled from the
     * bus, and the list of metrics in the local MongoDB is replaced.
     *
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws JSONException
     * @throws IOException
     * @throws BusRequestException
     * @throws BusException
     * @throws ParseException
     */
    private void refreshMetrics() throws JsonParseException,
            JsonMappingException, JSONException, IOException,
            BusRequestException, BusException, ParseException {

        // Retrieve Metrics from BUS/link
        List<Metric> metrics = getInputMetrics();

        // Delete Metrics from MongoDb
        deleteAllPreviousMetricsFromMongoDb();

        // Save new version of the metric in MongoDb
        saveMetricsIntoMongoDb(metrics);

        if (hostSettings.isDebug()) {
            printMetrics(metrics);
        }

        systemStateRepository.deleteAll();
        if (metrics.size() > 0) {
            systemStateRepository.save(new SystemState(1));
        }

    }

    /**
     * This method get the Users from Activiti keeping in consideration the
     * Activiti paging policy
     *
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @return The whole list of users that are in Activiti DB
     * @throws JsonRequestException
     */
    private List<UserActiviti> getUsersFromActiviti(
            JsonRequestActiviti jsonRequest) throws JsonRequestException {

        // Rest call to retrieve Users from Activiti
        @SuppressWarnings("unchecked")
        List<UserActiviti> listUserOnActiviti = (List<UserActiviti>) jsonRequest
                .getList(hostSettings.getActivitiRestEndpointIdentityUsers(),
                        UserActivitiList.class);

        // return a List<UserActiviti>
        return listUserOnActiviti;

    }

    /**
     * This method get the Groups from Activiti keeping in consideration the
     * Activiti paging policy
     *
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     */
    private List<GroupActiviti> getGroupsFromActiviti(
            JsonRequestActiviti jsonRequest) throws JsonRequestException {

        // Rest call to retrieve Groups from Activiti
        @SuppressWarnings("unchecked")
        List<GroupActiviti> listGroupOnActiviti = (List<GroupActiviti>) jsonRequest
                .getList(hostSettings.getActivitiRestEndpointIdentityGroups(),
                        GroupActivitiList.class);

        // return a List<GroupActiviti>
        return listGroupOnActiviti;

    }

    /**
     * This method delete all metrics in the local Mongo DB.
     */
    private void deleteAllPreviousMetricsFromMongoDb() {
        metricRepository.deleteAll();
    }

    /**
     * This method save all metrics retrieved with the Rest call to the bus.
     *
     * @param metrics The metric list to be saved
     */
    private void saveMetricsIntoMongoDb(List<Metric> metrics) {

        for (Metric m : metrics) {
            Metric savedMetric = metricRepository.save(m);

            if (hostSettings.isDebug()) {
                LOG.error(savedMetric.getName());
            }
        }

    }

    /**
     * This method parse the roles of a single user
     *
     * @param role The string that represents the list of roles of a single user
     * @return A list of strings that represents the list of roles of a single
     * user
     */
    private List<String> parseRoles(String role) {
        if (role == null)
            return new ArrayList<String>();
        String tempRole = role.replaceAll("\\[", "").replaceAll("\\]", "");
        String roles[] = tempRole.split(",");
        ArrayList<String> roleList = new ArrayList<String>();
        for (String r : roles) {
            roleList.add(r.trim());
        }
        return roleList;
    }

    /**
     * This method get the users from the bus
     *
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @return The list of users taken from bus
     * @throws IOException
     * @throws BusRequestException
     * @throws ParseException
     * @throws JSONException
     * @throws BusException
     */
    private List<User> getUsers(JsonRequestActiviti jsonRequest)
            throws IOException, BusRequestException, ParseException,
            JSONException, BusException {

        String address = "";
        List<User> users = new ArrayList<User>();

        if (hostSettings.isBus() && hostSettings.getBusUri() != null
                && !hostSettings.getBusUri().isEmpty()) {

            // Bus ready
            ObjectMapper mapper = new ObjectMapper();
            BusMessage busMessage;
            String responseFromBus;

            // Bus address
            address = hostSettings.getBusUri();

            JSONObject jsonGetUsers = new JSONObject();
            jsonGetUsers.put("objIdLocalToPhase", "");
            jsonGetUsers.put("typeObj", "user");
            jsonGetUsers.put("instance", "");
            jsonGetUsers.put("busVersion", "");

            busMessage = new BusMessage(BusMessage.OPERATION_GETUSERS, "test",
                    jsonGetUsers.toString());
            responseFromBus = busMessage.send(address);
            users = mapper.readValue(responseFromBus, mapper.getTypeFactory()
                    .constructCollectionType(List.class, User.class));

            // return List<User> users obtained from Bus
            return users;

        }

        // Bus not ready

        // Address to myjson
        address = "https://api.myjson.com/bins/26uks";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
                address, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
                });

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            new BusRequestException(responseEntity.getStatusCode().toString());
        }

        // return List<User> users obtained from myjson
        return responseEntity.getBody();
    }

    /**
     * This method create a list of roles(groups) of the users, without
     * duplicates
     *
     * @param users The list of users taken from bus
     * @return The list of roles(groups) of the users, without duplicates
     */
    private ArrayList<String> createGroupsArray(List<User> users) {

        ArrayList<String> arrayGroups = new ArrayList<String>();

        for (User u : users) {

            // Retrieve roles from BUS/Link
            List<String> rolesOfUserFromInput = parseRoles(u.getRole());

            // if arrayGroups is Empty, add all roles of User u to arrayGroups
            if (arrayGroups.size() == 0) {

                for (String role : rolesOfUserFromInput)
                    arrayGroups.add(role);
            } else {

                // arrayGroups is not Empty, add all roles of User without
                // duplicates
                int cont = 0;
                for (String roleOfUserFromInput : rolesOfUserFromInput) {

                    for (String r : arrayGroups) {
                        if (r.equals(roleOfUserFromInput)) {
                            cont++;
                            break;
                        }

                    }

                    if (cont == 0) {

                        arrayGroups.add(roleOfUserFromInput);

                    }
                    cont = 0;

                }

            }

        }

        return arrayGroups;

    }

    /**
     * This method create groups in Activiti and assign each user to the correct
     * group
     *
     * @param arrayGroups       The list of roles(groups) of the users, without duplicates
     * @param jsonRequest       A dedicated tool used to do a correct rest call.
     * @param listGroupActiviti The list of groups in Activiti
     * @param users             The list of users taken from bus
     * @throws JsonRequestException
     * @throws JsonRequestConflictException
     */
    private void createGroupsActiviti(ArrayList<String> arrayGroups,
                                      JsonRequestActiviti jsonRequest,
                                      List<GroupActiviti> listGroupActiviti, List<User> users)
            throws JsonRequestException, JsonRequestConflictException {

        if (listGroupActiviti.size() != 0) {
            emptyGroups(listGroupActiviti, jsonRequest);
            if (arrayGroups.size() != 0) {
                // ArrayGroups not empty
                createGroups2(arrayGroups, jsonRequest, listGroupActiviti);
                assignUsersToGroups(users, jsonRequest);
            }

        } else if (arrayGroups.size() != 0) {
            // ArrayGroups not empty, listGroupActiviti empty
            createGroupsFirstTime(arrayGroups, jsonRequest);
            assignUsersToGroups(users, jsonRequest);

        }

    }

    /**
     * This method create users in Activiti when Activiti has no users yet
     *
     * @param users       The list of users taken from bus
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     * @throws JsonRequestConflictException
     */
    private void createUsersActivitiFirstTime(List<User> users,
                                              JsonRequestActiviti jsonRequest) throws JsonRequestException,
            JsonRequestConflictException {

        for (User u : users) {

            createUser(u, jsonRequest);

        }

    }

    /**
     * This method assign users to groups in Activiti
     *
     * @param users       The list of users taken from bus
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     */
    private void assignUsersToGroups(List<User> users,
                                     JsonRequestActiviti jsonRequest) throws JsonRequestException {
        for (User u : users) {
            for (String role : parseRoles(u.getRole())) {
                // create request body
                PostAssignUserToGroup postAssignUserToGroup = new PostAssignUserToGroup();
                postAssignUserToGroup.setUserId(u.getUsername());

                try {
                    jsonRequest.post(
                            hostSettings
                                    .getActivitiRestEndpointIdentityGroups()
                                    + "/" + role + "/members",
                            postAssignUserToGroup, String.class);
                } catch (JsonRequestConflictException
                        | HttpClientErrorException e) {
                    // User already is in that group
                    // nothing
                    continue;
                }
            }
        }

    }

    /**
     * This method get the list of metrics from the bus
     *
     * @return The list of metrics taken from bus
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws BusRequestException
     * @throws JSONException
     * @throws BusException
     * @throws ParseException
     */
    private List<Metric> getInputMetrics() throws JsonParseException,
            JsonMappingException, IOException, BusRequestException,
            JSONException, BusException, ParseException {

        String address = "";
        if (hostSettings.isBus() && hostSettings.getBusUri() != null
                && !hostSettings.getBusUri().isEmpty()) {

            ObjectMapper mapper;
            BusMessage busMsg;
            String responseFromBus;
            List<Metric> listM = new ArrayList<Metric>();

            JSONObject readBody = new JSONObject();
            readBody.put("objIdLocalToPhase", "");
            readBody.put("typeObj", Metric.class.getSimpleName());
            readBody.put("instance", "");
            readBody.put("busVersion", "");
            readBody.put("tags", "[]");

            busMsg = new BusMessage(BusMessage.OPERATION_READ, "phase2",
                    readBody.toString());

            address = hostSettings.getBusUri();
            responseFromBus = busMsg.send(address);

            mapper = new ObjectMapper();
            try {
                BusReadResponse b = mapper.readValue(responseFromBus,
                        BusReadResponse.class);
                if (!b.getErr().equals("0")) {
                    LOG.error("err: " + b.getErr() + " message: " + b.getMsg());
                } else {
                    LOG.error("Unexpected error, it should never reach this point!");
                }
            } catch (JsonParseException | JsonMappingException e) {
                // No error occured!
                List<BusReadResponse> lbr = mapper.readValue(
                        responseFromBus,
                        mapper.getTypeFactory().constructCollectionType(
                                List.class, BusReadResponse.class));
                for (BusReadResponse element : lbr) {
                    Metric m = mapper.treeToValue(element.getPayload(),
                            Metric.class);
                    listM.add(m);
                }
            }
            return listM;
        }

        // bus non presente
        address = "https://api.myjson.com/bins/1new7";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Metric>> responseEntityMetric = restTemplate
                .exchange(address, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Metric>>() {
                        });

        if (responseEntityMetric.getStatusCode() != HttpStatus.OK) {
            throw new BusRequestException(responseEntityMetric.getStatusCode()
                    .toString());
        }

        return responseEntityMetric.getBody();

    }

    private void printMetrics(List<Metric> metrics) {
        // parte in comune ai due casi
        for (Metric metric : metrics) {
            System.out.println(metric.toString());
        }

    }

    /**
     * This method create the users in Activiti
     *
     * @param users             The list of users taken from bus
     * @param listUsersActiviti The list of users in Activiti
     * @param jsonRequest       A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     * @throws JsonRequestConflictException
     */
    private void createUsersActiviti2(List<User> users,
                                      List<UserActiviti> listUsersActiviti,
                                      JsonRequestActiviti jsonRequest) throws JsonRequestException,
            JsonRequestConflictException {

        String infoUserBus, infoUserActiviti;

        for (User userBus : users) {
            int cont = 0;
            infoUserBus = userBus.getUsername();

            // For each user in Activiti, check if he is present, and if he has
            // changed his password
            for (ListIterator<UserActiviti> iter = listUsersActiviti
                    .listIterator(); iter.hasNext(); ) {
                UserActiviti userActiviti = iter.next();
                infoUserActiviti = userActiviti.getId();

                if (infoUserBus.equals(infoUserActiviti)) {
                    // User userBus is present in Activiti

                    // Check if he has changed his password
                    if (!userBus.getPassword().equals(
                            userActiviti.getPassword())) {

                        // Update password
                        updatePasswordUser(userActiviti, jsonRequest, userBus);
                        UserActiviti tempUserActivity = new UserActiviti();
                        tempUserActivity.setId(userBus.getUsername());
                        tempUserActivity.setFirstName(userBus.getName());
                        tempUserActivity.setLastName(userBus.getSurname());
                        tempUserActivity.setPassword(userBus.getPassword());

                        iter.set(tempUserActivity);
                    }

                    break;
                }

                cont++;
            }

            if (cont == listUsersActiviti.size()) {

                ListIterator<UserActiviti> iter2 = listUsersActiviti
                        .listIterator();
                // User userBus is not present in Activiti
                createUser(userBus, jsonRequest);
                UserActiviti tempUserActivity = new UserActiviti();
                tempUserActivity.setId(userBus.getUsername());
                tempUserActivity.setFirstName(userBus.getName());
                tempUserActivity.setLastName(userBus.getSurname());
                tempUserActivity.setPassword(userBus.getPassword());
                iter2.add(tempUserActivity);

            }

        }

    }

    /**
     * This method create a single user in Activiti
     *
     * @param u           The user
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     * @throws JsonRequestConflictException
     */
    private void createUser(User u, JsonRequestActiviti jsonRequest)
            throws JsonRequestException, JsonRequestConflictException {

        // create request body
        UserActiviti userActiviti = new UserActiviti();
        userActiviti.setId(u.getUsername());
        userActiviti.setFirstName(u.getName());
        userActiviti.setLastName(u.getSurname());
        userActiviti.setPassword(u.getPassword());

        // send request and parse result
        jsonRequest.post(hostSettings.getActivitiRestEndpointIdentityUsers(),
                userActiviti, UserActiviti.class);
    }

    /**
     * This method update the password of a user in Activiti
     *
     * @param userActiviti The user in Activiti
     * @param jsonRequest  A dedicated tool used to do a correct rest call.
     * @param u            The user
     * @throws JsonRequestException
     */
    private void updatePasswordUser(UserActiviti userActiviti,
                                    JsonRequestActiviti jsonRequest, User u)
            throws JsonRequestException {

        // create request body
        UserActiviti tempActivitiUser = new UserActiviti();
        tempActivitiUser.setPassword(u.getPassword());

        jsonRequest.put(hostSettings.getActivitiRestEndpointIdentityUsers()
                + "/" + u.getUsername(), tempActivitiUser, UserActiviti.class);
    }

    /**
     * This method remove all the associations between groups and users in
     * Activiti
     *
     * @param listGroupActiviti The list of users in Activiti
     * @param jsonRequest       A dedicated tool used to do a correct rest call.
     * @return
     * @throws JsonRequestException
     */
    private ResponseEntity<String> emptyGroups(
            List<GroupActiviti> listGroupActiviti,
            JsonRequestActiviti jsonRequest) throws JsonRequestException {

        String groupName;
        List<UserActiviti> usersGroup;

        for (GroupActiviti group : listGroupActiviti) {
            usersGroup = null;
            groupName = group.getName();
            if (!groupName.equals("Admin") && !groupName.equals("User")) {

                usersGroup = getUsersGroup(group, jsonRequest);
                if (usersGroup.size() != 0) {
                    // there is at least one user for this group "group"
                    for (UserActiviti u : usersGroup) {
                        System.out.println("gruppo " + group.getId()
                                + " utente" + u.getId());
                        removeUserFromGroup(u, group, jsonRequest);

                    }
                }

            }

        }
        return null;

    }

    /**
     * This method get the users related to a specific group
     *
     * @param group       The group in Activiti
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @return
     * @throws JsonRequestException
     */
    private List<UserActiviti> getUsersGroup(GroupActiviti group,
                                             JsonRequestActiviti jsonRequest) throws JsonRequestException {

        Map<String, String> queryParams = new LinkedHashMap<String, String>();
        queryParams.put("memberOfGroup", group.getId());

        @SuppressWarnings("unchecked")
        List<UserActiviti> listUserOnActiviti = (List<UserActiviti>) jsonRequest
                .getList(hostSettings.getActivitiRestEndpointIdentityUsers(),
                        UserActivitiList.class, queryParams);

        return listUserOnActiviti;
    }

    /**
     * This method remove user from a group in Activiti
     *
     * @param u           The user
     * @param g           The group in Activiti
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     */
    private void removeUserFromGroup(UserActiviti u, GroupActiviti g,
                                     JsonRequestActiviti jsonRequest) throws JsonRequestException {
        String restAddress = hostSettings
                .getActivitiRestEndpointIdentityGroups()
                + "/{groupId}/members/{userId}";
        String groupId = g.getId();
        String userId = u.getId();
        URI expanded = new UriTemplate(restAddress).expand(groupId, userId);
        String url;
        try {
            url = URLDecoder.decode(expanded.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            url = expanded.toString();
        }
        jsonRequest.delete(url);
        return;

    }

    /**
     * This method create the groups in Activiti, when there are no groups yet
     *
     * @param arrayGroups The groups
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     * @throws JsonRequestConflictException
     */
    private void createGroupsFirstTime(ArrayList<String> arrayGroups,
                                       JsonRequestActiviti jsonRequest) throws JsonRequestException,
            JsonRequestConflictException {
        for (String group : arrayGroups) {

            createGroup(group, jsonRequest);
        }
        return;

    }

    /**
     * This method create the groups in Activiti
     *
     * @param arrayGroups        The groups
     * @param jsonRequest        A dedicated tool used to do a correct rest call.
     * @param listGroupsActiviti The list of groups in Activiti
     * @throws JsonRequestException
     * @throws JsonRequestConflictException
     */
    private void createGroups2(ArrayList<String> arrayGroups,
                               JsonRequestActiviti jsonRequest,
                               List<GroupActiviti> listGroupsActiviti)
            throws JsonRequestException, JsonRequestConflictException {

        @SuppressWarnings("unused")
        String infoUser = "", infoGroupActiviti = "";
        for (String g : arrayGroups) {
            int cont = 0;
            // infoUser= g.getFirstName()+g.getLastName();
            for (ListIterator<GroupActiviti> iter = listGroupsActiviti
                    .listIterator(); iter.hasNext(); ) {
                GroupActiviti groupActiviti = iter.next();

                infoGroupActiviti = groupActiviti.getName();

                if (g.equals(infoGroupActiviti)) {

                    break;
                }

                cont++;
            }
            if (cont == listGroupsActiviti.size()) {
                // Group g not present in groupActiviti
                createGroup2(g, jsonRequest);

                ListIterator<GroupActiviti> iter2 = listGroupsActiviti
                        .listIterator();
                GroupActiviti tempGroupActivity = new GroupActiviti();
                tempGroupActivity.setId(g);
                tempGroupActivity.setName(g);
                tempGroupActivity.setType(g);

                iter2.add(tempGroupActivity);

            }

        }

    }

    /**
     * This method create a group in Activiti
     *
     * @param group       The group
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     * @throws JsonRequestConflictException
     */
    private void createGroup2(String group, JsonRequestActiviti jsonRequest)
            throws JsonRequestException, JsonRequestConflictException {

        GroupActiviti tempGroupActiviti = new GroupActiviti();
        tempGroupActiviti.setId(group);
        tempGroupActiviti.setName(group);
        tempGroupActiviti.setType(group);

        jsonRequest.post(hostSettings.getActivitiRestEndpointIdentityGroups(),
                tempGroupActiviti, GroupActiviti.class);
    }

    /**
     * This method create a group in Activiti
     *
     * @param group       The group
     * @param jsonRequest A dedicated tool used to do a correct rest call.
     * @throws JsonRequestException
     * @throws JsonRequestConflictException
     */
    private void createGroup(String group, JsonRequestActiviti jsonRequest)
            throws JsonRequestException, JsonRequestConflictException {

        GroupActiviti tempGroupActiviti = new GroupActiviti();
        tempGroupActiviti.setId(group);
        tempGroupActiviti.setName(group);
        tempGroupActiviti.setType(group);

        jsonRequest.post(hostSettings.getActivitiRestEndpointIdentityGroups(),
                tempGroupActiviti, GroupActiviti.class);
    }

    /**
     * Check if the workflow to be saved is eligible.
     *
     * @param workflowToBeSaved Workflow that has to saved on BUS
     * @throws IllegalSaveWorkflowRequestBodyException If the workflow to be saved is not eligible.
     */
    private void checkRequestBody(PostWorkflowToBeSaved workflowToBeSaved)
            throws IllegalSaveWorkflowRequestBodyException {

        if (workflowToBeSaved.getModelId() == null
                || workflowToBeSaved.getModelId().isEmpty()
                || workflowToBeSaved.getName() == null
                || workflowToBeSaved.getName().isEmpty()) {

            throw new IllegalSaveWorkflowRequestBodyException();
        }
    }

}
