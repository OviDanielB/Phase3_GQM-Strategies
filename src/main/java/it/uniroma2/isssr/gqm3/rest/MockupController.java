package it.uniroma2.isssr.gqm3.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.BusRequestException;
import it.uniroma2.isssr.gqm3.dto.bus.BusReadResponse;
import it.uniroma2.isssr.gqm3.model.Metric;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.rest.DTOMetric;
import it.uniroma2.isssr.gqm3.model.rest.DTOStrategyFrom2;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseMetric;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategy;
import it.uniroma2.isssr.gqm3.repository.MetricRepository;
import it.uniroma2.isssr.gqm3.repository.StrategyRepository;
import it.uniroma2.isssr.integrazione.BusException;
import it.uniroma2.isssr.integrazione.BusMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class MockupController {

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private StrategyRepository strategyRepository;

    @Autowired
    private HostSettings hostSettings;

    @RequestMapping(value = "/createMetric", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseMetric> createMetric(@RequestBody DTOMetric metricDto) {

        Metric metric = new Metric();
        metric.setOrdered(metricDto.getOrdered());
        metric.setName(metricDto.getName());
        metric.setVersion(metricDto.getVersion());
        metric.setReleaseNote(metricDto.getReleaseNote());
        metric.setUnit(metricDto.getUnit());
        metric.setScaleType(metricDto.getScaleType());
        metric.setId(metricDto.getId());
        metric.setDescription(metricDto.getDescription());
        metric.setHasMax(metricDto.getHasMax());
        metric.setHasMin(metricDto.getHasMin());
        metric.setSet(metricDto.getSet());
        metric.setMin(metricDto.getMin());
        metric.setMax(metricDto.getMax());

        /* save on local mongodb */
        metricRepository.save(metric);

        DTOResponseMetric dtoResponse = new DTOResponseMetric();
        dtoResponse.setName(metric.getName());
        dtoResponse.setDescription(metric.getDescription());
        dtoResponse.setId(metric.getId());

        ResponseEntity<DTOResponseMetric> responseEntity = new ResponseEntity<>(dtoResponse,
                HttpStatus.OK);

        return responseEntity;
    }

    @RequestMapping(value = "/strategy/createStrategy", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseStrategy> createStrategy(@RequestBody DTOStrategyFrom2 dtoStrategy) throws BusException {

        Strategy strategy = new Strategy(dtoStrategy.getName(), dtoStrategy.getDescription(), dtoStrategy.getOrganizationalUnit(),
                dtoStrategy.getVersion(), 0);
        strategy.setOrganizationalunitId(dtoStrategy.getOrganizationalUnitId());
        strategy.setIdF2(dtoStrategy.getId());
        strategy.setStatus(dtoStrategy.getRevisited());

        /* save on mongo */
//        strategyRepository.save(strategy);

        /* save on bus as if we are phase 2 */
        JSONObject payload = new JSONObject();
        payload.put("id", strategy.getIdF2());
        payload.put("name", strategy.getName());
        payload.put("description", strategy.getDescription());
        payload.put("organizationalUnitId", strategy.getOrganizationalunitId());
        payload.put("organizationalUnit", strategy.getOrganizationalunit());
        payload.put("version", strategy.getVersion());
        payload.put("status", strategy.getStatus());
        String p = payload.toString();

        System.out.println(payload.toString());
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objIdLocalToPhase", strategy.getIdF2());
            jsonObject.put("typeObj", "base64-TerminalStrategy");
            jsonObject.put("instance", strategy.getName());
            jsonObject.put("tags", "[]");
            jsonObject.put("payload", p);

            BusMessage busMessage = new BusMessage(
                    BusMessage.OPERATION_UPDATE, "phase2", jsonObject.toString());
            System.out.println(jsonObject.toString());
            String busResponse = busMessage.send(hostSettings.getBusUri());
            BusReadResponse busResponseParsed;

            ObjectMapper responseMapper = new ObjectMapper();
            busResponseParsed = responseMapper.readValue(busResponse, BusReadResponse.class);
            if (!busResponseParsed.getErr().equals("0")) {
                try {
                    busMessage = new BusMessage(BusMessage.OPERATION_CREATE, "phase2", jsonObject.toString());
                } catch (BusException e) {
                    e.printStackTrace();
                }
                busResponse = busMessage.send(hostSettings.getBusUri());
                busResponseParsed = responseMapper.readValue(busResponse,
                        BusReadResponse.class);
                if (!busResponseParsed.getErr().equals("0")) {
                    try {
                        throw new BusRequestException(busResponseParsed.getErr());
                    } catch (BusRequestException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }

        DTOResponseStrategy dtoResponse = new DTOResponseStrategy();
        dtoResponse.setStrategyid(strategy.getId());
        dtoResponse.setStrategyName(strategy.getName());
        dtoResponse.setStrategyDescription(strategy.getDescription());

        return new ResponseEntity<DTOResponseStrategy>(dtoResponse,
                HttpStatus.OK);

    }

}
