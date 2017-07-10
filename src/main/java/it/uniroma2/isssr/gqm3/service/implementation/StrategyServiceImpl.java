
package it.uniroma2.isssr.gqm3.service.implementation;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.service.StrategyService;
import it.uniroma2.isssr.gqm3.hermes.Bus2fase3;
import it.uniroma2.isssr.gqm3.hermes.BusInteration;
import it.uniroma2.isssr.gqm3.hermes.BusInterationImplementation;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.rest.DTOStrategyFrom1;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategy;
import it.uniroma2.isssr.gqm3.repository.StrategyRepository;
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

    public ResponseEntity updateStrategyF1() {
        // devo cancellare le strategy che non sono presenti nel DB, rinnovare
        // quelle aggiornate e creare le strategie nuove
        List<Strategy> actualStrategies = strategyRepository.findAll();
        List<DTOStrategyFrom1> upToDateStr = bus2Fase3.getStrategiesF1();
        // elimino le strategy non esistenti
        for (Strategy strategy : actualStrategies) {
            // DTOStrategyFrom1 dtos2 = new DTOStrategyFrom1();
            // dtos2.setId(strategy.getIdF1());
            for (DTOStrategyFrom1 dtoStrategyFrom1 : upToDateStr) {
                if (!dtoStrategyFrom1.getId().equals(strategy.getIdF1())) {
//					strategyRepository.delete(strategy);
                    break;
                }
            }
        }
        // aggiorno i valori nuovi
        // TODO devo controllare se la version è la stessa o è più recente
        for (DTOStrategyFrom1 dtoSF2 : upToDateStr) {
            if (dtoSF2.getRevisited() == HostSettings.state.NEW.getValue()
                    || dtoSF2.getRevisited() == HostSettings.state.MODIFIED.getValue()) {
                Query query = new Query();
                query.addCriteria(Criteria.where("idF1").is(dtoSF2.getId()));
                List<Strategy> mongoStrategy = mongoTemplate.find(query, Strategy.class);
                if (mongoStrategy.isEmpty()) {
                    // se è vuoto la crea
                    Strategy newStrategy = new Strategy(dtoSF2.getTitle(), dtoSF2.getDescription(),
                            dtoSF2.getOrganizationalUnitName(), dtoSF2.getOrganizationalUnitId(), dtoSF2.getRevisited(),
                            dtoSF2.getVersion(), 0);
                    newStrategy.setIdF1(dtoSF2.getId());
                    strategyRepository.save(newStrategy);
                } else {
                    Strategy toUpdate = mongoStrategy.get(0); // deve esserne
                    // presente solo
                    // uno
                    // mettere un
                    // controllo di
                    // univocità
                    if (toUpdate.getVersion() < dtoSF2.getVersion()) {
                        toUpdate.setName(dtoSF2.getTitle());
                        toUpdate.setDescription(dtoSF2.getDescription());
                        toUpdate.setOrganizationalunit(dtoSF2.getOrganizationalUnitName());
                        toUpdate.setOrganizationalunitId(dtoSF2.getOrganizationalUnitId());
                        toUpdate.setStatus(dtoSF2.getRevisited());
                        toUpdate.setVersion(dtoSF2.getVersion());

                    }
                }

            }
            // else{
            // if(dtoSF2.getRevisited()==gqm32Properties.state.MODIFIED){
            // Query query = new Query();
            // query.addCriteria(Criteria.where("name").is(dtoSF2.getTitle()));
            // List<Strategy> mongoStrategy = mongoTemplate.find(query,
            // Strategy.class);
            // if(mongoStrategy.isEmpty())
            // {
            // Strategy newStrategy= new Strategy(dtoSF2.getTitle(),
            // dtoSF2.getDescription(),
            // dtoSF2.getOrganizationalUnitName(),
            // dtoSF2.getOrganizationalUnitId(),
            // dtoSF2.getRevisited(), dtoSF2.getVersion(), 0);
            // strategyRepository.save(newStrategy);
            // }else{
            // Strategy toUpdate=mongoStrategy.get(0);
            // toUpdate.setDescription(dtoSF2.getDescription());
            // toUpdate.setOrganizationalUnit(dtoSF2.getOrganizationalUnitName());
            // toUpdate.setOrganizationalUnitId(dtoSF2.getOrganizationalUnitId());
            // toUpdate.setStatus(dtoSF2.getRevisited());
            // //Inconsistenza dice di aver modificato la strategy invece non
            // esisteva
            //
            // }
            // }
            // }
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
