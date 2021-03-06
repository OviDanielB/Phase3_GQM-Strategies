package it.uniroma2.isssr.gqm3.service.implementation;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.BusRequestException;
import it.uniroma2.isssr.gqm3.dto.bus.BusReadResponse;
import it.uniroma2.isssr.gqm3.model.*;
import it.uniroma2.isssr.gqm3.tools.BusObjectTypes;
import it.uniroma2.isssr.integrazione.BusException;
import it.uniroma2.isssr.integrazione.BusMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/**
 * @author emanuele
 *         TODO: Modificare tutti gli oggetti che utilizzano questa classe nel seguente modo:
 *         Se un operazione su questa classe fallisce, eliminare gli aggiornamenti fatti sul db mongo locale per non creare incoerenza
 */
@Service
public class BusService2Phase4Implementation {

    @Autowired
    HostSettings hostSettings;

    public ResponseEntity saveWorkflowData(WorkflowData workflowData) throws IOException, BusException, BusRequestException {

        JSONObject payload = new JSONObject();
        payload.put("_id", workflowData.get_id());
        payload.put("businessWorkflowName", workflowData.getBusinessWorkflowName());
        payload.put("metaWorkflowName", workflowData.getMetaWorkflowName());
        payload.put("metaWorkflowProcessInstanceId", workflowData.getMetaWorkflowProcessInstanceId());
        payload.put("businessWorkflowModelId", workflowData.getBusinessWorkflowModelId());
        payload.put("businessWorkflowProcessDefinitionId", workflowData.getBusinessWorkflowProcessDefinitionId());
        payload.put("businessWorkflowProcessInstanceId", workflowData.getBusinessWorkflowProcessInstanceId());
        payload.put("measureTasksList", workflowData.getMeasureTasksList().toString());
        payload.put("ended", workflowData.isEnded().toString());
        String pl = payload.toString();

        JSONObject jo = new JSONObject();
        jo.put("objIdLocalToPhase", workflowData.get_id());
        jo.put("typeObj", BusObjectTypes.WORKFLOW_DATA);
        jo.put("instance", workflowData.getBusinessWorkflowName());
        jo.put("tags", "[]");
        jo.put("payload", pl);
//        JSONObject payload = new JSONObject();
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonWorkflowData = "";
//        try {
//            jsonWorkflowData = mapper.writeValueAsString(workflowData);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        String encoded = java.util.Base64.getEncoder().encodeToString(
//                jsonWorkflowData.getBytes("utf-8"));
//        payload.put("object", encoded);
//
//
//        JSONObject jo = new JSONObject();
//        jo.put("objIdLocalToPhase", workflowData.get_id());
//        jo.put("typeObj", WorkflowData.class.getSimpleName());
//        jo.put("instance", workflowData.get_id());
//        jo.put("payloadEncode", "base64");
//        jo.put("tags", "[]");
//        jo.put("payload", payload.toString());

        BusMessage busMessage = new BusMessage(BusMessage.OPERATION_UPDATE,
                hostSettings.getPhaseName(), jo.toString());

        String busResponse = busMessage.send(hostSettings.getBusUri());
        BusReadResponse busResponseParsed;

        ObjectMapper responseMapper = new ObjectMapper();
        busResponseParsed = responseMapper.readValue(busResponse, BusReadResponse.class);

        if (!busResponseParsed.getErr().equals("0")) {
            try {
                busMessage = new BusMessage(BusMessage.OPERATION_CREATE, hostSettings.getPhaseName(), jo.toString());
            } catch (BusException e) {
                e.printStackTrace();
            }
            busResponse = busMessage.send(hostSettings.getBusUri());
            busResponseParsed = responseMapper.readValue(busResponse,
                    BusReadResponse.class);
            if (!busResponseParsed.getErr().equals("0"))
                throw new BusRequestException(busResponseParsed.getErr());

        }
        return new ResponseEntity<String>(busResponse, HttpStatus.OK);
    }

    public ResponseEntity saveMeasureTask(MeasureTask measureTask) {


        JSONObject payload = new JSONObject();

        ObjectMapper mapper = new ObjectMapper();
        String jsonMeasureTask = "";
        try {
            jsonMeasureTask = mapper.writeValueAsString(measureTask);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String encoded = null;
        try {
            encoded = java.util.Base64.getEncoder().encodeToString(
                    jsonMeasureTask.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // TODO check if read of this message from phase4 is ok
        payload.put("object", encoded);


        JSONObject jo = new JSONObject();
        jo.put("objIdLocalToPhase", measureTask.get_id());
        jo.put("typeObj", "MeasureTask");
        jo.put("instance", measureTask.get_id());
        jo.put("payloadEncode", "base64");
        jo.put("tags", "[]");
        jo.put("payload", payload.toString());

        System.out.println(jo.toString());
        BusMessage busMessage = null;
        try {
            busMessage = new BusMessage(BusMessage.OPERATION_CREATE, hostSettings.getPhaseName(), jo.toString());
        } catch (BusException e) {
            e.printStackTrace();
        }
        try {
            String busResponse = busMessage.send(hostSettings.getBusUri());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(measureTask, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(measureTask, HttpStatus.CREATED);

    }

    public ResponseEntity saveStrategicPlan(StrategicPlan strategicPlan) {

        /* remove all strategy with no workflow associated */
        Iterator<StrategyWorkflowRelation> swIterator = strategicPlan.getStrategyWorkflowIds().iterator();
        while (swIterator.hasNext()) {
            StrategyWorkflowRelation strategyWorkflowRelation = swIterator.next();
            if (strategyWorkflowRelation.getWorkflow().getBusinessWorkflowProcessInstanceId() == null)
                swIterator.remove();
        }

        JSONObject payload = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        String jsonStrategicPlan = "";
        try {
            jsonStrategicPlan = mapper.writeValueAsString(strategicPlan);

            String encoded = null;
            encoded = java.util.Base64.getEncoder().encodeToString(
                    jsonStrategicPlan.getBytes("utf-8"));
            payload.put("object", encoded);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        JSONObject jo = new JSONObject();
        jo.put("objIdLocalToPhase", strategicPlan.getId());
        jo.put("typeObj", StrategicPlan.class.getSimpleName());
        jo.put("instance", strategicPlan.getName());
        jo.put("payloadEncode", "base64");
        jo.put("tags", "[]");
        jo.put("payload", payload.toString());

        try {
            BusMessage busMessage = new BusMessage(BusMessage.OPERATION_UPDATE, hostSettings.getPhaseName(), jo.toString());
            String busResponse = busMessage.send(hostSettings.getBusUri());

            ObjectMapper responseMapper = new ObjectMapper();
            BusReadResponse busResponseParsed = responseMapper.readValue(busResponse, BusReadResponse.class);

            /* bad update request so we create it */
            if (!busResponseParsed.getErr().equals("0")) {
                busMessage = new BusMessage(BusMessage.OPERATION_CREATE, hostSettings.getPhaseName(), jo.toString());
                busResponse = busMessage.send(hostSettings.getBusUri());
                busResponseParsed = responseMapper.readValue(busResponse, BusReadResponse.class);

                if (!busResponseParsed.getErr().equals("0"))
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);

            }

        } catch (IOException | BusException e) {
            e.printStackTrace();
            return new ResponseEntity<>(strategicPlan, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(strategicPlan, HttpStatus.CREATED);

    }


//    public ResponseEntity<?> saveWorkflowData2(WorkflowData workflowData) throws IOException, BusRequestException, BusException {
//
//        JSONObject pl = new JSONObject();
//        pl.put("_id", workflowData.get_id());
//        pl.put("businessWorkflowName", workflowData.getBusinessWorkflowName());
//        pl.put("metaWorkflowName", workflowData.getMetaWorkflowName());
//        pl.put("metaWorkflowProcessInstanceId", workflowData.getMetaWorkflowProcessInstanceId());
//        pl.put("businessWorkflowModelId", workflowData.getBusinessWorkflowModelId());
//        pl.put("businessWorkflowProcessDefinitionId", workflowData.getBusinessWorkflowProcessDefinitionId());
//        pl.put("businessWorkflowProcessInstanceId", workflowData.getBusinessWorkflowProcessInstanceId());
//
//        ObjectMapper mapper = new ObjectMapper();
//        String measureTaskList = mapper.writeValueAsString(workflowData.getMeasureTasksList());
//        pl.put("measureTasksList", measureTaskList);
//        pl.put("ended", workflowData.isEnded().toString());
//
//
//        JSONObject jo = new JSONObject();
//        jo.put("objIdLocalToPhase", workflowData.get_id());
//        jo.put("typeObj", "WorkflowData");
//        jo.put("instance", workflowData.getMetaWorkflowName());
//        jo.put("tags", "[]");
//        jo.put("payload", pl.toString());
//
//        System.out.println(jo.toString());
//        BusMessage busMessage = new BusMessage(BusMessage.OPERATION_UPDATE, hostSettings.getPhaseName(), jo.toString());
//        System.out.println(hostSettings.getBusUri());
//        String busResponse = busMessage.send(hostSettings.getBusUri());
//        BusReadResponse busResponseParsed;
//
//        ObjectMapper responseMapper = new ObjectMapper();
//        busResponseParsed = responseMapper.readValue(busResponse, BusReadResponse.class);
//
//        if (!busResponseParsed.getErr().equals("0")) {
//            busMessage = new BusMessage(BusMessage.OPERATION_CREATE, hostSettings.getPhaseName(), jo.toString());
//            busResponse = busMessage.send(hostSettings.getBusUri());
//            busResponseParsed = responseMapper.readValue(busResponse,
//                    BusReadResponse.class);
//            if (!busResponseParsed.getErr().equals("0")) {
//                throw new BusRequestException(busResponseParsed.getErr());
//            }
//        }
//
//        return new ResponseEntity<String>(busResponse, HttpStatus.OK);
//    }

//    public ResponseEntity saveMeasureTask(MeasureTask measureTask) throws BusException, JsonProcessingException {
//
//        /* TODO : SERIALIZE METRICS WHENE PHASE 2 WILL HAVE DEFINED THEM*/
//        JSONObject pl = new JSONObject();
//        pl.put("_id", measureTask.get_id());
//        pl.put("metricId", measureTask.getMetric().get_id());
//        pl.put("taskId", measureTask.get_id());
//        pl.put("means", measureTask.getMeans());
//        pl.put("responsible", measureTask.getResponsible());
//        pl.put("source", measureTask.getSource());
//
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule("BooleanAsString", new Version(1, 0, 0, null, null, null));
//        module.addSerializer(new NonTypedScalarSerializerBase<Boolean>(Boolean.class) {
//            @Override
//            public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider)
//                    throws IOException, JsonGenerationException {
//                jgen.writeString(value.toString());
//            }
//        });
//        module.addSerializer(new NonTypedScalarSerializerBase<Boolean>(boolean.class) {
//            @Override
//            public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider)
//                    throws IOException, JsonGenerationException {
//                jgen.writeString(value.toString());
//            }
//        });
//
//        mapper.registerModule(module);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//        JsonNode val = mapper.valueToTree(measureTask.getValidationIdList());
//        pl.put("validationIdList", val);
//
//        JSONObject jo = new JSONObject();
//        jo.put("objIdLocalToPhase", measureTask.get_id());
//        jo.put("typeObj", "MeasureTask");
//        jo.put("instance", measureTask.get_id());
//        jo.put("encode", "JSON");
//        jo.put("payloadEncode", "JSON");
//        jo.put("tags", "[]");
//        jo.put("payload", pl.toString());
//
//        System.out.println(jo.toString());
//        BusMessage busMessage = new BusMessage(BusMessage.OPERATION_CREATE, hostSettings.getPhaseName(), jo.toString());
//        try {
//            String busResponse = busMessage.send(hostSettings.getBusUri());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(measureTask, HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity(measureTask, HttpStatus.CREATED);
//    }

//    public ResponseEntity saveStrategicPlan(StrategicPlan strategicPlan) throws BusException {
//
//
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        SimpleModule module = new SimpleModule("floatAsString", new Version(1, 0, 0, null, null, null));
//        module.addSerializer(new NonTypedScalarSerializerBase<Float>(Float.class) {
//            @Override
//            public void serialize(Float value, JsonGenerator jgen, SerializerProvider provider)
//                    throws IOException, JsonGenerationException {
//                jgen.writeString(value.toString());
//            }
//        });
//
//        mapper.registerModule(module);
//        mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
//        JsonNode node = mapper.valueToTree(strategicPlan);
//        String json = node.toString();
//        json = json.replaceAll("\\[", "\"\\[");
//        json = json.replaceAll("\\]", "\\]\"");
//
//        JSONObject jo = new JSONObject();
//        jo.put("objIdLocalToPhase", strategicPlan.getId());
//        jo.put("typeObj", StrategicPlan.class.getSimpleName());
//        jo.put("instance", strategicPlan.getName());
//        jo.put("encode", "JSON");
//        jo.put("payloadEncode", "JSON");
//        jo.put("tags", "[]");
//        jo.put("payload", node.toString());
//
//        System.out.println(jo.toString());
//        BusMessage busMessage = new BusMessage(BusMessage.OPERATION_CREATE, hostSettings.getPhaseName(), jo.toString());
//        try {
//            String busResponse = busMessage.send(hostSettings.getBusUri());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(strategicPlan, HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity(strategicPlan, HttpStatus.CREATED);
//    }

}
