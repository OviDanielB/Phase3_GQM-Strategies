package it.uniroma2.isssr.gqm3.hermes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.rest.DTOStrategyFrom2;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategy;
import it.uniroma2.isssr.gqm3.repository.StrategyRepository;
import it.uniroma2.isssr.integrazione.BusException;
import it.uniroma2.isssr.integrazione.BusMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: Bus2Fase32Implementation
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: Dipartimento di Ingegneria Informatica, Università degli studi di
 * Roma Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, Federico
 * Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli, Luca
 * Della Gatta
 * </p>
 * <p>
 * <p>
 * Class description: Classe in cui sono implementati i metodi "fittizi" del
 * bus, cioè ci serve per avere un punto di elaborazione per simulare il bus...
 * Speriamo non per sempre.. ahah
 * <p>
 * Poiché per ora è necessario comunque accedere ai dati salvati sul Mongo
 * locale per richiamare le Strategie quello che si fa è scrivere questo metodo
 * provvisoriamente
 * </p>
 *
 * @author Fabio Alberto Coira
 * @version 1.0
 */

@Component
@ComponentScan(basePackages = {"it.uniroma2.gqm3141.repository"})

public class Bus2fase3Implementation implements Bus2fase3 {

    /**
     * The strategy repository.
     */
    @Autowired
    StrategyRepository strategyRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    HostSettings hostSettings;


    public ResponseEntity<DTOResponseStrategy> getStrategies() {
        List<Strategy> strategies = strategyRepository.findAll();
        DTOResponseStrategy dtoRS = new DTOResponseStrategy();
        dtoRS.setStrategies(strategies);
        return new ResponseEntity<DTOResponseStrategy>(HttpStatus.OK);

    }


    public List<Strategy> getStrategiesList() {

        List<Strategy> strategies = strategyRepository.findAll();
        return strategies;
    }


    public ArrayList<DTOStrategyFrom2> getStrategiesF2() {
        ArrayList<DTOStrategyFrom2> ls = new ArrayList<>();
        String response;

        org.json.JSONArray jsonResponse;
        JSONObject jsonobj;
        try {
            JSONObject jsonRead = new JSONObject();
            jsonRead.put("objIdLocalToPhase", "");
            jsonRead.put("typeObj", "base64-TerminalStrategy");
            jsonRead.put("instance", "");
            jsonRead.put("busVersion", "");
            jsonRead.put("tags", "[]");

            BusMessage busMessage = new BusMessage(BusMessage.OPERATION_READ, "phase2", jsonRead.toString());
            response = busMessage.send(hostSettings.getBusUri());
            jsonResponse = new org.json.JSONArray(response);

        } catch (IOException | JSONException | BusException e) {
            e.printStackTrace();
            return null;
        }

        for (int i = 0; i < jsonResponse.length(); i++) {

            try {

                jsonobj = jsonResponse.getJSONObject(i);

                String payloads = jsonobj.getString("payload");
                System.out.println(payloads);

                DTOStrategyFrom2 ob = new DTOStrategyFrom2();
                JSONObject jsonPayload = new JSONObject(payloads);
                ob.setId(Long.toString(jsonPayload.getLong("id")));
                ob.setName(jsonPayload.getString("title"));
                ob.setDescription(jsonPayload.getString("description"));
                JSONObject scope = jsonPayload.getJSONObject("scope");
                ob.setOrganizationalUnit(scope.getString("name"));
                ob.setOrganizationalUnitId(Long.toString(scope.getLong("id")));
                JSONObject state = jsonPayload.getJSONObject("state");
                ob.setRevisited(state.getInt("value"));

                ob.setVersion(jsonobj.getInt("busVersion"));

                ls.add(ob);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return ls;
    }

}
