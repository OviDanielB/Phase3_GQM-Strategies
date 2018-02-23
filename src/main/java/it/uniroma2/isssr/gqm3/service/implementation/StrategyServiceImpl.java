
package it.uniroma2.isssr.gqm3.service.implementation;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.EntityNotFoundException;
import it.uniroma2.isssr.gqm3.model.StrategicPlan;
import it.uniroma2.isssr.gqm3.model.StrategyWorkflowRelation;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.model.rest.DTOStrategyFrom2;
import it.uniroma2.isssr.gqm3.repository.StrategicPlanRepository;
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
import java.util.Objects;

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

    @Autowired
    StrategicPlanRepository strategicPlanRepository;

    /**
     * The bus interation implementation.
     */
    @Autowired
    BusInterationImplementation busInterationImplementation;

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

        // strategie attualmente salvate su mongodb
        List<Strategy> actualStrategies = strategyRepository.findAll();

        // strategie presenti sul bus
        List<DTOStrategyFrom2> upToDateStr = bus2Fase3.getStrategiesF2();

        // aggiorno i valori nuovi
        for (DTOStrategyFrom2 dtoSF2 : upToDateStr) {

            Query query = new Query();
            query.addCriteria(Criteria.where("idF2").is(dtoSF2.getId()));
            List<Strategy> mongoStrategy = mongoTemplate.find(query, Strategy.class);

            if (dtoSF2.getRevisited() == HostSettings.state.NEW.getValue() && !alreadyExist(actualStrategies, dtoSF2)) {

                Strategy newStrategy = new Strategy(dtoSF2.getName(), dtoSF2.getDescription(),
                        dtoSF2.getOrganizationalUnit(), dtoSF2.getOrganizationalUnitId(), dtoSF2.getRevisited(),
                        dtoSF2.getVersion(), 0);
                newStrategy.setIdF2(dtoSF2.getId());
                strategyRepository.save(newStrategy);

            } else if (dtoSF2.getRevisited() == HostSettings.state.MODIFIED.getValue() && !mongoStrategy.isEmpty()
                    && !alreadyExist(actualStrategies, dtoSF2)) {

                Strategy toUpdate = mongoStrategy.get(0);

                if (toUpdate.getVersion() < dtoSF2.getVersion()) {

                    toUpdate.setId(null);
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

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Boolean alreadyExist(List<Strategy> actualStrategies, DTOStrategyFrom2 dtoSF2) {

        Boolean alreadyPresent = false;
        for (Strategy strategy : actualStrategies)
            if (Objects.equals(strategy.getIdF2(), dtoSF2.getId()) && strategy.getVersion() == dtoSF2.getVersion()) {
                alreadyPresent = true;
                break;
            }

        return alreadyPresent;
    }

//    private void updateWorkflowData(Strategy strategy) throws Exception {
//
//        List<StrategicPlan> strategicPlans = strategicPlanRepository.findByOrganizationalunit(strategy.getOrganizationalunit());
//        if (strategicPlans == null || strategicPlans.isEmpty())
//            throw new Exception();
//
//        StrategicPlan strategicPlan = strategicPlans.get(0);
//        List<StrategyWorkflowRelation> strategyWorkflowRelations = strategicPlan.getStrategyWorkflowIds();
//
//        for (StrategyWorkflowRelation strategyWorkflowRelation : strategyWorkflowRelations) {
//            if (Objects.equals(strategy.getIdF2(), strategyWorkflowRelation.getStrategy().getIdF2())) {
//
//                /* let unmodified just the modelId */
//                WorkflowData workflowData = strategyWorkflowRelation.getWorkflow();
//                workflowData.setBusinessWorkflowName(null);
//                workflowData.setBusinessWorkflowProcessDefinitionId(null);
//                workflowData.setBusinessWorkflowProcessInstanceId(null);
//                workflowData.setMetaWorkflowName(null);
//                workflowData.setMetaWorkflowProcessInstanceId(null);
//            }
//        }
//    }

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
