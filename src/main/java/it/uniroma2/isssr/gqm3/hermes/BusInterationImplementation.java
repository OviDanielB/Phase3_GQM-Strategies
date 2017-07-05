package it.uniroma2.isssr.gqm3.hermes;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.service.StrategicPlanService;
import it.uniroma2.isssr.gqm3.model.StrategicPlan;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategy;
import it.uniroma2.isssr.gqm3.repository.StrategicPlanRepository;
import it.uniroma2.isssr.gqm3.repository.StrategyRepository;
import it.uniroma2.isssr.integrazione.BusException;
import it.uniroma2.isssr.integrazione.BusMessage;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: BusInterationImplementation</p>
 * <p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>Class description:
 * Classe che si pone da intermediaria tra la nostra applicazione
 * e il bus hermes. Qui vengono richiamati i metodi del bus e vengono
 * elaborati i loro valori di ritorno per adattarli ai nostri
 *
 * @author Fabio Alberto Coira
 * @version 1.0
 */


@Component
public class BusInterationImplementation implements BusInteration {

    /**
     * The bus2fase32 implementation.
     */
    @Autowired
    Bus2fase32Implementation bus2fase32Implementation;

    @Autowired
    StrategyRepository strategyRepository;

    @Autowired
    HostSettings hostSettings;

    /**
     * The Strategic Plan Service.
     */
    @Autowired
    StrategicPlanService strategicPlanService;

    /**
     * The strategy repository.
     */
    @Autowired
    StrategicPlanRepository strategicPlanRepository;

    /**
     * Ritorna la lista dele strategie
     *
     * @param
     * @return
     * @throws
     */


    @Override
    public ResponseEntity<DTOResponseStrategy> getStrategies() {
        // TODO Auto-generated method stub
        List<Strategy> strategies = bus2fase32Implementation.getStrategiesList();
        DTOResponseStrategy dtoResponse = new DTOResponseStrategy();
        dtoResponse.setStrategies(strategies);
        ResponseEntity<DTOResponseStrategy> responseEntity = new ResponseEntity<DTOResponseStrategy>(dtoResponse, HttpStatus.OK);
        return responseEntity;
    }
    /*
	@Override
	public ResponseEntity<DTOResponseStrategy> getStrategies() {
		// TODO Auto-generated method stub

		List<Strategy> strategies =null;// bus2fase32Implementation.getStrategies();
		DTOResponseStrategy dtoResponse =null;// new DTOResponseStrategy();
		dtoResponse.setStrategies(strategies);
		ResponseEntity<DTOResponseStrategy> responseEntity = new ResponseEntity<DTOResponseStrategy>(dtoResponse,HttpStatus.OK);
		return responseEntity;
	}
    */

    /**
     * Ritorna la lista delle strategie con unità organizzativa non ancora utilizzata
     *
     * @param
     * @return
     * @throws
     */

    @Override
    public ResponseEntity<DTOResponseStrategy> getStrategiesFree() {
        // TODO Auto-generated method stub
        List<Strategy> strategies = bus2fase32Implementation.getStrategiesList();
        //List<StrategicPlan> strategicPlans = strategicPlanService.getStrategicPlansList();
        List<StrategicPlan> strategicPlans = strategicPlanRepository.findAll();
        List<String> organizationalunitSP = new ArrayList<String>();

        for (int i = 0; i < strategicPlans.size(); i++) {
            organizationalunitSP.add(strategicPlans.get(i).getOrganizationalUnit()); //Lista delle unità organizzative utilizzate in Strategic Plan esistenti
        }

        //Rimozione delle strategie con unità organizzativa già utlizzata
        for (int k = 0; k < strategies.size(); k++) {

            for (int j = 0; j < organizationalunitSP.size(); j++) {

                if (strategies.get(k).getOrganizationalunit().equals(organizationalunitSP.get(j))) {

                    strategies.remove(k);
                    k--;
                    break;

                }
            }
        }

        DTOResponseStrategy dtoResponse = new DTOResponseStrategy();
        dtoResponse.setStrategies(strategies);
        ResponseEntity<DTOResponseStrategy> responseEntity = new ResponseEntity<DTOResponseStrategy>(dtoResponse, HttpStatus.OK);
        return responseEntity;
    }


    /* (non-Javadoc)
     * @see it.uniroma2.isssr.gqm3.hermes.BusInteration#getStrategies()
     */
    @Override
    public ResponseEntity<DTOResponseStrategy> getValidationOps() {
        // TODO da implementare!
        List<Strategy> strategies = null;// bus2fase32Implementation.getStrategies();
        DTOResponseStrategy dtoResponse = new DTOResponseStrategy();
        dtoResponse.setStrategies(strategies);
        ResponseEntity<DTOResponseStrategy> responseEntity = new ResponseEntity<DTOResponseStrategy>(dtoResponse, HttpStatus.OK);
        return responseEntity;
    }

    @Override
    public String alertPhase2WrongStrategy(String strategyId) {

        Strategy strategy = strategyRepository.findOne(strategyId);
        DTOResponseStrategy dtoStrategy = new DTOResponseStrategy();
        String json = "{  \"subject\" : \" Alert on terminal strategy \", \"body\" : \"Please modify strategy with the id: " + strategy.getIdF1() + "\"  };";
        if (strategy != null) {
            dtoStrategy.setStrategyid(strategy.getId());
            dtoStrategy.setStrategyDescription(strategy.getDescription());
            dtoStrategy.setStrategyName(strategy.getName());
            dtoStrategy.setError("Please modify strategy with the id: " + strategy.getIdF1());
            try {
                BusMessage message = new BusMessage(BusMessage.OPERATION_MESSAGE, "phase3", "phase1", json);
                String response = message.send(hostSettings.getBusUri());
                return response;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "It cannot stringify dto object to a json string";
            } catch (BusException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "Error caused from bus interation";
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "IO Error sending message";
            }

        } else {
            return null;
        }
    }

}
