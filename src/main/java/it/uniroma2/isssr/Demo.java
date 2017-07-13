package it.uniroma2.isssr;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;
import it.uniroma2.isssr.gqm3.model.StrategicPlan;
import it.uniroma2.isssr.gqm3.service.StrategicPlanService;
import it.uniroma2.isssr.integrazione.BusException;
import it.uniroma2.isssr.integrazione.BusMessage;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

/**
 * @author emanuele
 */
public class Demo {

    public static void main(String args[]) throws BusException {

        StrategicPlan strategicPlan = new StrategicPlan(new ArrayList<>(), "name", "description",
                "unit", new ArrayList<>(), 1f, "rel");
        strategicPlan.setId("id");

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule("floatAsString", new Version(1, 0, 0, null, null, null));
        module.addSerializer(new NonTypedScalarSerializerBase<Float>(Float.class) {
            @Override
            public void serialize(Float value, JsonGenerator jgen, SerializerProvider provider)
                    throws IOException, JsonGenerationException {
                jgen.writeString(value.toString());
            }
        });

        mapper.registerModule(module);
        mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        JsonNode node = mapper.valueToTree(strategicPlan);
        String json = node.toString();
        json = json.replaceAll("\\[", "\"\\[");
        json = json.replaceAll("\\]", "\\]\"");


        JSONObject jo = new JSONObject();
        jo.put("objIdLocalToPhase", strategicPlan.getId());
        jo.put("typeObj", StrategicPlan.class.getSimpleName());
        jo.put("instance", strategicPlan.getName());
        jo.put("encode", "JSON");
        jo.put("payloadEncode", "JSON");
        jo.put("tags", "[]");
        jo.put("payload", json);

        System.out.println(jo.toString());
        BusMessage busMessage = new BusMessage(BusMessage.OPERATION_CREATE, "phase3", jo.toString());
        try {
            String busResponse = busMessage.send("http://localhost:8080/Bus/inboundChannel.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
