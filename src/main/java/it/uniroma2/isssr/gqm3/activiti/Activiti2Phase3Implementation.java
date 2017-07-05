package it.uniroma2.isssr.gqm3.activiti;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.model.FlowElement;
import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormProperty;
import it.uniroma2.isssr.gqm3.model.activiti.form.ActivitiFormVariableProperty;
import it.uniroma2.isssr.gqm3.model.activiti.process.*;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTask;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTaskList;
import it.uniroma2.isssr.gqm3.model.activiti.task.ActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.model.activiti.user.ActivitiUser;
import it.uniroma2.isssr.gqm3.model.rest.DTOActivitiTaskVariable;
import it.uniroma2.isssr.gqm3.tools.JsonRequestActiviti;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: Activiti2Phase3Implementation</p>
 * <p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>
 * <p>Class description:
 * Implementazione dei metodi dell'interfaccia  Activiti2Phase3.
 * Questa classe si occupa di effettuare le richieste
 * della nostra applicazione ad Activiti-Rest e di recuperare
 * il risultato, per poi passarlo alla classe
 * ActivitiInterationImplementation
 * <p>
 * Per interfacciarsi ad Activiti Rest vengono operate
 * delle richieste Http, in particolare si ricorre a quelle
 * con header customizzato (.exchange), poiché per interagire
 * con Activiti Rest è richiesto sempre ad ogni servizio Rest,
 * username e password (per fare questo login ad ogni richiesta
 * si utilizza Basic Authentication)</p>
 *
 * @author Fabio Alberto Coira
 * @version 1.0
 */

@Service("Activiti2fase32")
public class Activiti2Phase3Implementation implements Activiti2Phase3 {

    private final HostSettings hostSettings;

    private JsonRequestActiviti requestActiviti;

//	private final String URL = hostSettings.getActivitiRestConnectionUrl();


    private static final Logger log = LoggerFactory.getLogger(Activiti2Phase3Implementation.class);

    @Autowired
    public Activiti2Phase3Implementation(HostSettings hostSettings) {
        this.hostSettings = hostSettings;
        this.requestActiviti = new JsonRequestActiviti(hostSettings);
    }

    /**
     * @throws ActivitiGetException
     */
    @Override
    public List<ActivitiUser> getUsers()
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException {
        ObjectMapper mapper = new ObjectMapper();

//		  HttpHeaders headers = Gqm32Utils.customHeaderBasicAuth();

        String address = hostSettings.getActivitiRestEndpointIdentityUsers();

        List<ActivitiUser> activitiUserList = new ArrayList<ActivitiUser>();

        ResponseEntity<String> response = null;
        try {
            response = requestActiviti.get(address, String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            //per via della paginazione
            Long size = jsonObject.getLong("size");
            log.warn(size.toString());
            Long start = jsonObject.getLong("start");
            log.warn(start.toString());
            Long total = jsonObject.getLong("total");
            log.warn(total.toString());

            while (total - start > 0) {
                try {
                    response = requestActiviti.get(address + "?start=" + start, String.class);
                    jsonObject = new JSONObject(response.getBody());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    //ActivitiUserList activitiUser = new ActivitiUserList();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        activitiUserList.add(mapper.readValue(explrObject.toString(), ActivitiUser.class));
                    }
                    start = start + size;
                } catch (JsonRequestException e) {
                    e.printStackTrace();
                }
            }
        } catch (JsonRequestException e) {
            e.printStackTrace();
        }

        return activitiUserList;
    }

    /**
     * @throws ActivitiGetException Descrizione del metodo
     */
    @Override
    public List<ActivitiTask> getTasks()
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
        ObjectMapper mapper = new ObjectMapper();

        String url = hostSettings.getActivitiRestEndpointTasks();

        ResponseEntity<String> response = requestActiviti.get(url, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());
        //per via della paginazione
        Long size = jsonObject.getLong("size");
        log.warn(size.toString());
        Long start = jsonObject.getLong("start");
        log.warn(start.toString());
        Long total = jsonObject.getLong("total");
        log.warn(total.toString());
        List<ActivitiTask> activitiTaskList = new ArrayList<ActivitiTask>();
        while (total - start > 0) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            response = requestActiviti.get(url + "?start=" + start, String.class);
            jsonObject = new JSONObject(response.getBody());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                activitiTaskList.add(mapper.readValue(explrObject.toString(), ActivitiTask.class));
            }
            start = start + size;
        }
        return activitiTaskList;

    }

    /**
     * @throws ActivitiGetException
     */
    @Override
    public List<ActivitiTask> getUserTasks(String username)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
        ObjectMapper mapper = new ObjectMapper();

        String address = hostSettings.getActivitiRestEndpointTasks() + "?assignee=" + username;

        ResponseEntity<String> response = requestActiviti.get(address, String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        List<ActivitiTask> activitiTaskList = new ArrayList<ActivitiTask>();

        //per via della paginazione
        Long size = jsonObject.getLong("size");
        log.warn(size.toString());
        Long start = jsonObject.getLong("start");
        log.warn(start.toString());
        Long total = jsonObject.getLong("total");
        log.warn(total.toString());
        while (total - start > 0) {
            jsonObject = new JSONObject(response.getBody());
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            response = requestActiviti.get(address + "&start=" + start, String.class);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                activitiTaskList.add(mapper.readValue(explrObject.toString(), ActivitiTask.class));
            }
            start = start + size;

        }

        return activitiTaskList;
    }

    @Override
    public byte[] getProcessInstanceState(String id)
            throws IOException {
        BufferedImage image = null;
        try {
            URL url = new URL(hostSettings.getActivitiRestEndpointProcessInstances() + id + "/diagram");
            URLConnection uc = url.openConnection();
            uc.setRequestProperty("Authorization",
                    "Basic " + new String(Base64.encodeBase64(("kermit" + ":" + "kermit").getBytes())));
            image = ImageIO.read(uc.getInputStream());
        } catch (IOException e) {
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);

        // 1. download img from http://internal-picture-db/id.jpg ...
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    @Override
    public ActivitiProcessDefinition getProcessDefinition(String username, String password, String id)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        String URL = hostSettings.getActivitiRestEndpointProcessDefinitions() + id;
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response =
                template.exchange(URL, HttpMethod.GET, request, String.class);

        JSONObject jsnobject = null;
        try {
            jsnobject = new JSONObject(response.getBody());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ActivitiProcessDefinition processDefinition = new ActivitiProcessDefinition();
        processDefinition = mapper.readValue(jsnobject.toString(), ActivitiProcessDefinition.class);

        return processDefinition;
    }

    @Override
    public ActivitiProcessInvolvedPeople getInvolvedPeople(String username, String password, String id)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        String URL = hostSettings.getActivitiRestEndpointProcessInstances() + id + "/identitylinks";
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response =
                template.exchange(URL, HttpMethod.GET, request, String.class);

        //JSONObject jsnobject = new JSONObject(response.getBody());
        //JSONArray jsonArray = jsnobject.getJSONArray("data");
        JSONArray jsonArray = null;
        List<ActivitiProcessInvolvedPeople> activitiProcessInvolvedPeopleList = null;
        try {
            jsonArray = new JSONArray(response.getBody());

            activitiProcessInvolvedPeopleList = new ArrayList<ActivitiProcessInvolvedPeople>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                activitiProcessInvolvedPeopleList.add(mapper.readValue(explrObject.toString(), ActivitiProcessInvolvedPeople.class));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return activitiProcessInvolvedPeopleList.get(0);

    }

    @Override
    public List<ActivitiProcessDef> getProcess(String username, String password)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        String URL = hostSettings.getActivitiRestEndpointProcessInstances();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response =
                template.exchange(URL, HttpMethod.GET, request, String.class);
        ActivitiProcessList activitiProcessList = null;
        JSONObject jsnobject;
        try {
            jsnobject = new JSONObject(response.getBody());

            JSONArray jsonArray = jsnobject.getJSONArray("data");
            activitiProcessList = new ActivitiProcessList();


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                ActivitiProcessDefinition processDefinition = new ActivitiProcessDefinition();
                ActivitiProcessInvolvedPeople activitiProcessInvolvedPeople = new ActivitiProcessInvolvedPeople();
                ActivitiProcess activitiProcess = new ActivitiProcess();
                ActivitiProcessDef activitiProcessDef = new ActivitiProcessDef();
                activitiProcess = mapper.readValue(explrObject.toString(), ActivitiProcess.class);
                processDefinition = getProcessDefinition(username, password, activitiProcess.getProcessDefinitionId());
                activitiProcessInvolvedPeople = getInvolvedPeople(username, password, activitiProcess.getId());
                activitiProcessDef.setActivityId(activitiProcess.getActivityId());
                activitiProcessDef.setBusinessKey(activitiProcess.getBusinessKey());
                activitiProcessDef.setCompleted(activitiProcess.getCompleted());
                activitiProcessDef.setEnded(activitiProcess.getEnded());
                activitiProcessDef.setId(activitiProcess.getId());
                activitiProcessDef.setName(processDefinition.getName());
                activitiProcessDef.setProcessDefinitionId(activitiProcess.getProcessDefinitionId());
                activitiProcessDef.setProcessDefinitionUrl(activitiProcess.getProcessDefinitionUrl());
                activitiProcessDef.setSuspended(activitiProcess.getSuspended());
                activitiProcessDef.setTenantId(activitiProcess.getTenantId());
                activitiProcessDef.setUrl(activitiProcess.getUrl());
                activitiProcessDef.setVariables(activitiProcess.getVariables());
                activitiProcessDef.setDescription(processDefinition.getDescription());
                activitiProcessDef.setVersion(processDefinition.getVersion());
                activitiProcessDef.setActivitiProcessAuthor(activitiProcessInvolvedPeople.getUser());

                activitiProcessList.addActivitiProcess(activitiProcessDef);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return activitiProcessList.getActivitiProcessList();

    }


    /**
     * Metodo che dato un id di uno user task di Activiti restituisce una lista di variabili
     * del form associato, da completare (Quindi se lo User Task ha un form mi dà l'elenco delle
     * variabili, altrimenti non mi dà nulla)
     *
     * @param
     * @return
     * @throws JsonMappingException
     * @throws JsonParseException
     * @throws ActivitiGetException
     * @throws JSONException
     * @ throws IOException
     */
    @Override
    public List<ActivitiFormProperty> getFormPropertiesTaskById(
            String taskId) throws JsonParseException, JsonMappingException, IOException, JSONException, ActivitiGetException, JsonRequestException {
        // TODO Auto-generated method stub
        ObjectMapper mapper = new ObjectMapper();
        String URL = hostSettings.getActivitiRestEndpoint() + "/service/form/form-data?taskId=" + taskId;

        JSONObject jsonObject = new JSONObject(requestActiviti.get(URL, String.class).getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("formProperties");
        List<ActivitiFormProperty> activitiFormPropertiesList =
                new ArrayList<ActivitiFormProperty>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);
            activitiFormPropertiesList.add
                    (mapper.readValue(explrObject.toString(), ActivitiFormProperty.class));
        }

        return activitiFormPropertiesList;

    }

    /**
     * Servizio Rest che mi restituisce la lista delle variabili di uno UserTask di Activiti
     *
     * @throws ActivitiGetException
     * @throws JSONException
     */
    @Override
    public List<ActivitiTaskVariable> getTaskVariablesFromRuntimeTaskId(String taskId, String scope) throws JsonParseException, JsonMappingException, IOException, JSONException, ActivitiGetException, JsonRequestException {
        // TODO Auto-generated method stub
        ObjectMapper mapper = new ObjectMapper();
        String URL = hostSettings.getActivitiRestEndpointTasks() + taskId +
                "/variables?scope=" + scope;

        JSONArray jsonArray = new JSONArray(requestActiviti.get(URL, String.class).getBody());
        List<ActivitiTaskVariable> activitiTaskVariableList =
                new ArrayList<ActivitiTaskVariable>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);
            activitiTaskVariableList.add
                    (mapper.readValue(explrObject.toString(), ActivitiTaskVariable.class));
        }
        return activitiTaskVariableList;
    }

    /**
     * n.b. in Activiti è errore inserire nello stesso Json sia
     * variabili con Scope local che global => da fare separatamente
     */
    @Override
    public void createTaskVariables(String taskId,
                                    List<ActivitiTaskVariable> activitiTaskVariables) throws JsonRequestConflictException, JsonRequestException {
        // TODO Auto-generated method stub

        //JSONObject request = new JSONObject();
        //per migliorare la complete salvando le variabili basta passare una lista di variabili
        //e settare questa variabile qui come JSONArray della roba passata

        JSONArray request = new JSONArray();
        for (ActivitiTaskVariable taskVariable : activitiTaskVariables) {
            JSONObject taskVariableJSON = new JSONObject();
            taskVariableJSON.put("name", taskVariable.getName());
            taskVariableJSON.put("scope", taskVariable.getScope());
            taskVariableJSON.put("type", taskVariable.getType());
            taskVariableJSON.put("value", taskVariable.getValue());

            request.put(taskVariableJSON);
        }
        String URL = hostSettings.getActivitiRestEndpointTasks() + taskId + "/variables";

        // send request and parse result
        ResponseEntity<String> response = requestActiviti.post(URL, request, String.class);
        //il controllo è gestito dall'exception handler
        if (response.getStatusCode() == HttpStatus.OK)
            return;
    }

    /**
     * Servizio Rest che consente di ottenere i tasks
     * di Activiti assegnati ad uno specifico candidateGroup
     *
     * @param candidateGroup
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    public List<ActivitiTask> getUserTasksByCandidateGroup(String candidateGroup)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
        // TODO Auto-generated method stub
        ObjectMapper mapper = new ObjectMapper();
        String URL = hostSettings.getActivitiRestEndpointTasks() + "?candidateGroup=" + candidateGroup;
        ResponseEntity<String> response = requestActiviti.get(URL, String.class);
        JSONObject jsnobject = new JSONObject(response.getBody());
        JSONArray jsonArray = jsnobject.getJSONArray("data");
        ActivitiTaskList activitiTaskList = new ActivitiTaskList();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);
            activitiTaskList.addActivitiTask(
                    mapper.readValue(explrObject.toString(), ActivitiTask.class));
        }

        return activitiTaskList.getActivitiTaskList();
    }

    /**
     * Restituisce un oggetto che elenca le informazioni relative ad uno User Task
     *
     * @param taskId
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    @Override
    public ActivitiTask getUserTaskByTaskId(String taskId)
            throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
        // TODO Auto-generated method stub
        ObjectMapper mapper = new ObjectMapper();
        String URL = hostSettings.getActivitiRestEndpointTasks() + taskId;
        ResponseEntity<String> response = requestActiviti.get(URL, String.class);
        JSONObject jsnobject = new JSONObject(response.getBody());
        ActivitiTask activitiTask = mapper.readValue(jsnobject.toString(),
                ActivitiTask.class);
        return activitiTask;
    }

    @Override
    public List<FlowElement> getFlowElementsList(String username, String password, String businessWorkflowProcessDefinitionId) throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
        String URL = hostSettings.getActivitiRestEndpointProcessDefinitions() +
                businessWorkflowProcessDefinitionId + "/model";

        ResponseEntity<String> response = requestActiviti.get(URL, String.class);
        JSONObject jsnobject = new JSONObject(response.getBody());
        JSONArray jsonArray = jsnobject.getJSONArray("processes");
        List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
        List<FlowElement> flowelements = new ArrayList<FlowElement>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsnObject = jsonArray.getJSONObject(i);
             /*FlowElement flowElement  = mapper.readValue(jsnObject.toString(),
                     FlowElement.class);
			 flowElements.add(flowElement);*/
            jsonObjectList.add(jsnObject);


        }
        if (jsonObjectList.size() > 1) {
            //errore
        }
        JSONArray jsonArrayFlowElements = jsonObjectList.get(0).getJSONArray("flowElements");

        for (int i = 0; i < jsonArrayFlowElements.length(); i++) {
            JSONObject jsnFlowElement = jsonArrayFlowElements.getJSONObject(i);

            String name;
            try {
                name = jsnFlowElement.getString("name");
            } catch (JSONException e) {
                name = null;
            }
            ;
            String id = jsnFlowElement.getString("id");
            FlowElement flowelement = new FlowElement();
            flowelement.setName(name);
            flowelement.setId(id);
            flowelements.add(flowelement);
            log.warn(flowelement.getName() + " " + flowelement.getId());

			/*
             FlowElement flowElement  = mapper.readValue(jsnFlowElement.toString(),
					 FlowElement.class);
			 flowElements.add(flowElement);*/
        }
		 /*ProcessDefinitionModel processDefinitionModel =
				 mapper.readValue(jsnobject.toString(), 
						 ProcessDefinitionModel.class);
		 List<Process> processes = processDefinitionModel.getProcesses();
		 if(processes.isEmpty()){
			 //errore
		 }
		 if(processes.size()>1){
			 //errore
		 }
		 Process process = processes.get(0);
		 List<FlowElement> flowElements = process.getFlowElements();
		 return flowElements;
		 /*JSONArray jsonArray = jsnobject.getJSONArray("processes");
		 List<Process> listProcess = new ArrayList<Process>();
		 List<FlowElement> listFlowElements = new ArrayList<FlowElement>();
		 for(int i = 0 ; i< jsonArray.length() ; i++){
			 JSONObject objectProcess = jsonArray.getJSONObject(i);
			 listProcess.add(mapper.readValue(objectProcess.toString(),Process.class));
		 }
		 if(listProcess.isEmpty()){
			 //gestire problema
			 log.warn("listProcess vuoto");
		 }
		 
			listFlowElements = listProcess.get(0).getFlowElements();
			return listFlowElements;
		*/
        return flowelements;

        //jsonArray=jsonArray.getJSONArray("flowElements");
    }

    /*{  Esempio di JsonBody per completare un task a partire da un
     * 	 form
         "taskId": "3120877",
        "properties" : [{
                      "id" : "new_property_1",
                      "value" : "pippo"
                    },
                    {
                      "id": "new_property_2",
                      "value": "value1"

                    },
                    {
                      "id" : "new_property_3",
                      "value" : "11-20-2016 22:33"
                    },
                    {
                      "id" : "new_property_4",
                      "value" : true
                    },
                    {
                      "id" : "new_property_5",
                      "value" : 1
                    }]
}
     *
     *
     */
    @Override
    public void submitFormDataAndCompleteTask(String taskId, List<ActivitiFormVariableProperty> activitiFormVariableProperties) throws ActivitiPostException, JsonRequestConflictException, JsonRequestException {
        // TODO Auto-generated method stub

        JSONArray properties = new JSONArray();
        for (ActivitiFormVariableProperty activitiFormVariableProperty : activitiFormVariableProperties) {
            JSONObject propertyObject = new JSONObject();
            propertyObject.put("id", activitiFormVariableProperty.getId());
            propertyObject.put("value", activitiFormVariableProperty.getValue());
            properties.put(propertyObject);
            log.warn(activitiFormVariableProperty.getId() + " " + activitiFormVariableProperty.getValue());
        }
        JSONObject requestObject = new JSONObject();
        requestObject.put("taskId", taskId);
        requestObject.put("properties", properties);
        String URL = hostSettings.getActivitiRestEndpoint() + "/service/form/form-data/";
        log.warn(requestObject.toString());
        // send request and parse result
        ResponseEntity<String> response = requestActiviti.post(URL, requestObject, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return;
        }
    }

    public ActivitiTaskVariable updateActivitiTaskVariable(String id, String name,
                                                           DTOActivitiTaskVariable dtoActivitiTaskVariable) throws
            JsonParseException, JsonMappingException, IOException, ActivitiPutException, JsonRequestException {
        // TODO Auto-generated method stub
        ObjectMapper mapper = new ObjectMapper();
        JSONObject requestObject = new JSONObject();
        requestObject.put("name", dtoActivitiTaskVariable.getName());
        requestObject.put("scope", dtoActivitiTaskVariable.getScope());
        requestObject.put("type", dtoActivitiTaskVariable.getType());
        requestObject.put("value", dtoActivitiTaskVariable.getValue());
        String URL = hostSettings.getActivitiRestEndpointTasks()
                + "/" + id + "/variables/" + name;

        ResponseEntity<String> response = requestActiviti.put(URL, requestObject, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsnobject = new JSONObject(response.getBody());
            ActivitiTaskVariable activitiTaskVariable =
                    mapper.readValue(jsnobject.toString(),
                            ActivitiTaskVariable.class);
            return activitiTaskVariable;
        } else throw new ActivitiPutException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "E' fallita la put per l'aggiornamento di una variabile"
                        + "verso Activiti");
    }


}
	
	
	

