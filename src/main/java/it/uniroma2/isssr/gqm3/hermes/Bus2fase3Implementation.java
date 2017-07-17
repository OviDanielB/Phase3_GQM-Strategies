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
        String response = "", organizationalUnitId = "", organizationalUnitName = "", version = "", id = "";

        DTOStrategyFrom2 ob = null;
        org.json.JSONArray jsonResponse = null;
        JSONObject jsonobj = null;
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
        ObjectMapper obM = new ObjectMapper();
        obM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (jsonResponse != null) {
            for (int i = 0; i < jsonResponse.length(); i++) {
                try {
                    jsonobj = jsonResponse.getJSONObject(i);
                    id = jsonobj.getString("instance");
                    version = jsonobj.getString("busVersion");
                    JSONArray tags = jsonobj.getJSONArray("tags");
                    for (int j = 0; j < tags.length(); j++) {
                        JSONObject singleTag = tags.getJSONObject(j);
                        if (singleTag.getString("key").equals("OrgUnitId")) {
                            organizationalUnitId = singleTag.getString("value");
                        } else {
                            if (singleTag.getString("key").equals("OrgUnitName")) {
                                organizationalUnitName = singleTag.getString("value");
                            }
                        }

                    }
                    String paylodas = jsonobj.getString("payload");
//			paylodas= new String(Base64.getDecoder().decode(paylodas));
                    jsonobj = new JSONObject(paylodas);
                    ob = obM.readValue(jsonobj.toString(), DTOStrategyFrom2.class);
                    ob.setOrganizationalUnitId(organizationalUnitId);
                    ob.setOrganizationalUnitName(organizationalUnitName);
                    ob.setVersion(Integer.parseInt(version));
                    ls.add(ob);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }

            return ls;
        } else {
            return null;
        }
    }

}
