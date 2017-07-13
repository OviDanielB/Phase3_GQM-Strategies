package it.uniroma2.isssr.gqm3.rest;

import it.uniroma2.isssr.gqm3.service.StrategyService;
import it.uniroma2.isssr.gqm3.hermes.Bus2fase3;
import it.uniroma2.isssr.gqm3.hermes.BusInterationImplementation;
import it.uniroma2.isssr.gqm3.model.rest.DTO;
import it.uniroma2.isssr.gqm3.model.rest.DTOLevel3Request;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify/")
public class RestPresentationBus {
    @Autowired
    StrategyService strategyService;
    @Autowired
    Bus2fase3 bus2Fase3;
    @Autowired
    BusInterationImplementation busInterationImplementation;

    @RequestMapping(value = "/eventonstrategy", method = RequestMethod.POST)
    public ResponseEntity<DTOLevel3Request> createStrategy(@RequestBody DTOLevel3Request dtoLevel3Request) {

        String data = dtoLevel3Request.getData();
        JSONObject message;
        String typeobj = "";
        try {
            message = new JSONObject(data);
            typeobj = message.getString("typeObj");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (typeobj.equals("base64-TerminalStrategy")) {
            strategyService.updateStrategyF1();
            return new ResponseEntity<DTOLevel3Request>(dtoLevel3Request, HttpStatus.OK);
        }

        return new ResponseEntity<DTOLevel3Request>(dtoLevel3Request, HttpStatus.BAD_REQUEST);

    }


    @RequestMapping(value = "/alertPhase2", method = RequestMethod.GET)
    public ResponseEntity<DTO> sendNotification(@RequestParam("id") String id) {
        String result = busInterationImplementation.alertPhase2WrongStrategy(id);
        DTO dto = new DTO();
        dto.setMessage(result);
        return new ResponseEntity<DTO>(dto, HttpStatus.OK);

    }

    @RequestMapping(value = "/tryStrategy", method = RequestMethod.GET)
    public ResponseEntity tryStrategy() {
        ResponseEntity result = strategyService.updateStrategyF1();
        DTO dto = new DTO();
        dto.setMessage("done");
        return result;

    }

}
