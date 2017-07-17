
package it.uniroma2.isssr.gqm3.service.implementation;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.model.rest.DTOStrategyFrom2;
import it.uniroma2.isssr.gqm3.service.StrategyService;
import it.uniroma2.isssr.gqm3.hermes.Bus2fase3;
import it.uniroma2.isssr.gqm3.hermes.BusInteration;
import it.uniroma2.isssr.gqm3.hermes.BusInterationImplementation;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategy;
import it.uniroma2.isssr.gqm3.repository.StrategyRepository;
import org.json.simple.parser.Yytoken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Title: Activiti2Phase3Implementation
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: Dipartimento di Ingegneria Informatica, Universita' degli studi di
 * Roma Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, Federico
 * Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli, Luca
 * Della Gatta
 * </p>
 * <p>
 * Class description:
 * <p>
 * Classe che implementa la relativa interfaccia, ed e' annotata con @Service
 * per indicare a Spring che e' un service bean. La dependency injection di
 * strategyRepository è operata attraverso l'annotazione @Autowired. In
 * particolare questa classe si occupa di gestire tutto cio' che riguarda le
 * interazioni con la classe Strategy da parte di altri. E infatti richiama il
 * repository relativo a Strategy.
 *
 * @author Daniele Capri, Fabio Alberto Coira
 * @version 1.0
 */

@Service("StrategyService")
public class StrategyServiceImpl implements StrategyService {

    /**
     * The strategy repository.
     */
    @Autowired
    StrategyRepository strategyRepository;

    /**
     * The bus interation implementation.
     */
    @Autowired
    BusInteration busInteration;

    @Autowired
    Bus2fase3 bus2Fase3;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * The bus interation implementation.
     */
    @Autowired
    BusInterationImplementation busInterationImplementation;


    /**
     * Metodo che restituisce la risposta della chiamata REST. La risposta è un
     * oggetto che contiene sia l'oggetto di ritorno dell'interrogazione del DB
     * (in questo caso la lista di Strategy) e lo status della risposta (200,
     * 0k).
     * <p>
     * Devo fare la modifica qui per richiederla al bus invece di cercare nel
     * nostro DB locale (Per il momento metto questo metodo in "hermes")
     *
     * @return the strategies
     */

    @Override
    public ResponseEntity<DTOResponseStrategy> getStrategies() {
        /*
         *
		 * List<Strategy> strategies = strategyRepository.findAll();
		 *
		 * DTOResponse dtoResponse = new DTOResponse();
		 * dtoResponse.setStrategies(strategies); ResponseEntity<DTOResponse>
		 * responseEntity = new
		 * ResponseEntity<DTOResponse>(dtoResponse,HttpStatus.OK); return
		 * responseEntity;
		 */
        return busInteration.getStrategies();
    }


    @Override
    public ResponseEntity<DTOResponseStrategy> getStrategy() {
        /*
         *
		 * List<Strategy> strategies = strategyRepository.findAll();
		 * 
		 * DTOResponse dtoResponse = new DTOResponse();
		 * dtoResponse.setStrategies(strategies); ResponseEntity<DTOResponse>
		 * responseEntity = new
		 * ResponseEntity<DTOResponse>(dtoResponse,HttpStatus.OK); return
		 * responseEntity;
		 */
        return busInteration.getStrategies();
    }

    public ResponseEntity updateStrategyF2() {

        // strategia attualmente salvate so mongodb
        List<Strategy> actualStrategies = strategyRepository.findAll();

        // strategie presenti sul bus
        List<DTOStrategyFrom2> upToDateStr = bus2Fase3.getStrategiesF2();

        // elimino le strategy presenti su mongodb ma che non sono più presenti sul bus
        for (Strategy strategy : actualStrategies) {

            Boolean notFound = true;
            for (DTOStrategyFrom2 dtoStrategyFrom2 : upToDateStr)
                if (dtoStrategyFrom2.getId().equals(strategy.getIdF2())) {
                    notFound = false;
                }

            if (notFound)
                strategyRepository.delete(strategy);

        }

        if (upToDateStr == null) {
            strategyRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }

        // aggiorno i valori nuovi
        for (DTOStrategyFrom2 dtoSF2 : upToDateStr) {

            if (dtoSF2.getRevisited() == HostSettings.state.NEW.getValue() ||
                    dtoSF2.getRevisited() == HostSettings.state.MODIFIED.getValue()) {

                Query query = new Query();
                query.addCriteria(Criteria.where("idF2").is(dtoSF2.getId()));
                List<Strategy> mongoStrategy = mongoTemplate.find(query, Strategy.class);

                // strategy is marked as MODIFIED but it doesn't exist, so we create it
                if (mongoStrategy.isEmpty()) {
                    Strategy newStrategy = new Strategy(dtoSF2.getName(), dtoSF2.getDescription(),
                            dtoSF2.getOrganizationalUnit(), dtoSF2.getOrganizationalUnitId(), dtoSF2.getRevisited(),
                            dtoSF2.getVersion(), 0);
                    newStrategy.setIdF2(dtoSF2.getId());
                    strategyRepository.save(newStrategy);

                } else {

                    Strategy toUpdate = mongoStrategy.get(0);

                    if (mongoStrategy.size() > 1) {
                        //TODO: resolve unexpected state
                        System.out.println("On the bus there are more than one strategy with the same idF2: " + toUpdate.getIdF2());
                    }

                    if (toUpdate.getVersion() < dtoSF2.getVersion()) {
                        toUpdate.setIdF2(dtoSF2.getId());
                        toUpdate.setName(dtoSF2.getName());
                        toUpdate.setDescription(dtoSF2.getDescription());
                        toUpdate.setOrganizationalunit(dtoSF2.getOrganizationalUnit());
                        toUpdate.setOrganizationalunitId(dtoSF2.getOrganizationalUnitId());
                        toUpdate.setStatus(dtoSF2.getRevisited());
                        toUpdate.setVersion(dtoSF2.getVersion());

                        strategyRepository.save(toUpdate);
                    }
                }

            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DTOResponseStrategy> getStrategiesFree() {
    /*

		List<Strategy> strategies = strategyRepository.findAll();
		
		DTOResponse dtoResponse = new DTOResponse();
		dtoResponse.setStrategies(strategies);
		ResponseEntity<DTOResponse> responseEntity = new ResponseEntity<DTOResponse>(dtoResponse,HttpStatus.OK);
		return responseEntity;
		*/
        return busInterationImplementation.getStrategiesFree();
    }

}
