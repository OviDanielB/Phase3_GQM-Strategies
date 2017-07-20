package it.uniroma2.isssr.gqm3.service.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.service.StrategicPlanService;
import it.uniroma2.isssr.gqm3.service.StrategyService;
import it.uniroma2.isssr.integrazione.BusException;
import it.uniroma2.isssr.integrazione.BusMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import it.uniroma2.isssr.gqm3.model.Attribute;
import it.uniroma2.isssr.gqm3.model.StrategicPlan;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.StrategyWorkflowRelation;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseAttribute;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseMetaWorkflow;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseSWRelation;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategicPlan;
import it.uniroma2.isssr.gqm3.repository.AttributeRepository;
import it.uniroma2.isssr.gqm3.repository.MetaWorkflowRepository;
import it.uniroma2.isssr.gqm3.repository.SWRRepository;
import it.uniroma2.isssr.gqm3.repository.StrategicPlanRepository;
import it.uniroma2.isssr.gqm3.repository.StrategyRepository;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;

/**
 * <p>
 * Title: StrategicPlanServiceImpl
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
 * <p>
 * Class description: Implementazione dello Strategic Plan Service ...
 *
 * @author Daniele Capri
 * @version 1.0
 */

@Service("StrategicPlanService")
public class StrategicPlanServiceImpl implements StrategicPlanService {

    /**
     * The strategy repository.
     */
    @Autowired
    StrategyRepository strategyRepository;

    /**
     * The strategy repository.
     */
    @Autowired
    StrategicPlanRepository strategicPlanRepository;

    /**
     * The attribute repository.
     */
    @Autowired
    AttributeRepository attributeRepository;

    /**
     * The s wrr repository.
     */
    @Autowired
    SWRRepository sWRRRepository;

    /**
     * The strategy repository.
     */
    @Autowired
    MetaWorkflowRepository mwRepository;

    @Autowired
    WorkflowDataRepository wdRepository;

    /**
     * The mongo template.
     */
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    HostSettings hostSettings;

    @Autowired
    BusService2Phase4Implementation busService2Phase4Implementation;

    @Autowired
    WorkflowServiceImplementation workflowServiceImplementation;

    @Autowired
    StrategyService strategyService;

    @Override
    public ResponseEntity<DTOResponseStrategicPlan> createStrategicPlan(ArrayList<String> strategyId, String name,
                                                                        String description, float version, String release) {
        String tempOU = null, tempOU2 = null;

        ArrayList<StrategyWorkflowRelation> strategyResult = new ArrayList<StrategyWorkflowRelation>();
        // controllo che tutte le strategie abbino lo stessa unità
        // organizzativa, altrimenti pulisco la lista e ritorno bad request

        if (strategyId != null)
            for (int i = 0; i < strategyId.size(); i++) {
                Strategy s = strategyRepository.findOne(strategyId.get(i));
                if (i == 0) {
                    tempOU = s.getOrganizationalunit();
                    if (tempOU == null) {
                        strategyResult.clear();
                        break;
                    }
                } else {
                    tempOU2 = s.getOrganizationalunit();
                    if (!tempOU.equals(tempOU2)) {
                        strategyResult.clear();
                        break;
                    }
                }
                StrategyWorkflowRelation swr = new StrategyWorkflowRelation(s);
                // sWRRRepository.save(swr);
                strategyResult.add(swr);

            }

        Query query = new Query();
        query.addCriteria(Criteria.where("organizationalunit").is(tempOU));
        List<StrategicPlan> mongoStrategicPlans = mongoTemplate.find(query, StrategicPlan.class);
        if (!mongoStrategicPlans.isEmpty()) {
            strategyResult.clear();
        }
        if (!strategyResult.isEmpty()) {

            StrategicPlan strategicP = new StrategicPlan(strategyResult, name, description, tempOU, version, release);
            /* save on mongodb */
            strategicPlanRepository.save(strategicP);

            /* save on bus */
            busService2Phase4Implementation.StrategicPlanOperation(strategicP, BusMessage.OPERATION_CREATE);

            DTOResponseStrategicPlan dtoResponse = new DTOResponseStrategicPlan();
            dtoResponse.setId(strategicP.getId());
            dtoResponse.setName(strategicP.getName());
            dtoResponse.setDescription(strategicP.getDescription());
            dtoResponse.setOrganizzationalUnit(tempOU);
            dtoResponse.setStrategyToWorkflowId(strategyResult);
            dtoResponse.setVersion(strategicP.getVersion());
            dtoResponse.setRelease(strategicP.getRelease());

            ResponseEntity<DTOResponseStrategicPlan> responseEntity = new ResponseEntity<DTOResponseStrategicPlan>(
                    dtoResponse, HttpStatus.OK);
            return responseEntity;
        } else {
            DTOResponseStrategicPlan dtoResponse = new DTOResponseStrategicPlan();
            ResponseEntity<DTOResponseStrategicPlan> responseEntity = new ResponseEntity<DTOResponseStrategicPlan>(
                    dtoResponse, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }
    }

    @Override
    public ResponseEntity<DTOResponseStrategicPlan> deleteStrategicPlan(String id) {
        ResponseEntity<DTOResponseStrategicPlan> responseEntity;
        StrategicPlan strategicP = strategicPlanRepository.findOne(id);
        if (strategicP != null) {

            /* delete from local mongodb */
            strategicPlanRepository.delete(strategicP);

            /* delete from bus */
            busService2Phase4Implementation.StrategicPlanOperation(strategicP, BusMessage.OPERATION_DELETE);

            DTOResponseStrategicPlan dtoResponse = new DTOResponseStrategicPlan();
            dtoResponse.setId(strategicP.getId());
            dtoResponse.setName(strategicP.getName());
            dtoResponse.setDescription(strategicP.getDescription());
            dtoResponse.setAttributes(strategicP.getAttributes());
            responseEntity = new ResponseEntity<>(dtoResponse, HttpStatus.OK);
        } else {
            DTOResponseStrategicPlan dtoResponse = new DTOResponseStrategicPlan();
            responseEntity = new ResponseEntity<>(dtoResponse, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    /* phase 4 */
    @Override
    public ResponseEntity<DTOResponseStrategicPlan> getStrategicPlans() {
        List<StrategicPlan> strategicPlans = strategicPlanRepository.findAll();
        DTOResponseStrategicPlan dtoresponse = new DTOResponseStrategicPlan();
        dtoresponse.setStrategicPlans(strategicPlans);
        ResponseEntity<DTOResponseStrategicPlan> responseEntity = new ResponseEntity<DTOResponseStrategicPlan>(
                dtoresponse, HttpStatus.OK);

        return responseEntity;

    }

    @Override
    public List<StrategicPlan> getStrategicPlansList() {
        List<StrategicPlan> strategicPlans = strategicPlanRepository.findAll();
        return strategicPlans;
    }

    @Override
    public ResponseEntity<DTOResponseStrategicPlan> getStrategicPlan(String strategicPlanId) {

        StrategicPlan strategicP = strategicPlanRepository.findOne(strategicPlanId);
        if (strategicP == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        DTOResponseStrategicPlan dtoResponse = new DTOResponseStrategicPlan();
        dtoResponse.setId(strategicP.getId());
        dtoResponse.setName(strategicP.getName());
        dtoResponse.setDescription(strategicP.getDescription());
        dtoResponse.setOrganizzationalUnit(strategicP.getOrganizationalUnit());
        dtoResponse.setVersion(strategicP.getVersion());
        dtoResponse.setRelease(strategicP.getRelease());
        dtoResponse.setStrategyToWorkflowId(strategicP.getStrategyWorkflowIds());
        dtoResponse.setAttributes(strategicP.getAttributes());

        ResponseEntity<DTOResponseStrategicPlan> responseEntity = new ResponseEntity<DTOResponseStrategicPlan>(
                dtoResponse, HttpStatus.OK);
        return responseEntity;

    }

    @Override
    public ResponseEntity<DTOResponseAttribute> createAttribute(String name, String type, String value) {

        Attribute attr = new Attribute(name, type, value);

        attributeRepository.save(attr);
        DTOResponseAttribute dtoResponse = new DTOResponseAttribute();
        dtoResponse.setId(attr.getId());
        dtoResponse.setName(attr.getName());
        dtoResponse.setType(attr.getType());
        dtoResponse.setValue(attr.getValue());
        ResponseEntity<DTOResponseAttribute> responseEntity = new ResponseEntity<DTOResponseAttribute>(dtoResponse,
                HttpStatus.OK);
        return responseEntity;

    }


    @Override
    public ResponseEntity<DTOResponseStrategicPlan> updateStrategicPlan(String id,
                                                                        String name, String description, ArrayList<Attribute> attributes, float version, String release) {

        StrategicPlan p = null;

        p = strategicPlanRepository.findOne(id);

        p.setName(name);
        p.setDescription(description);
        p.setAttributes(attributes);
        p.setVersion(version);
        p.setRelease(release);

        strategicPlanRepository.save(p);

        busService2Phase4Implementation.StrategicPlanOperation(p, BusMessage.OPERATION_UPDATE);

        DTOResponseStrategicPlan dtoResponse = new DTOResponseStrategicPlan();
        dtoResponse.setId(p.getId());
        dtoResponse.setStrategyToWorkflowId(p.getStrategyWorkflowIds());
        dtoResponse.setName(p.getName());
        dtoResponse.setDescription(p.getDescription());
        dtoResponse.setOrganizzationalUnit(p.getOrganizationalUnit());
        dtoResponse.setAttributes(p.getAttributes());
        ResponseEntity<DTOResponseStrategicPlan> responseEntity = new ResponseEntity<DTOResponseStrategicPlan>(
                dtoResponse, HttpStatus.OK);
        return responseEntity;
    }

    @Override
    public ResponseEntity<DTOResponseSWRelation> getMetaWorkflows(String strategicPlanId) {
        StrategicPlan strategicP = strategicPlanRepository.findOne(strategicPlanId);
        DTOResponseSWRelation dtoResponse = new DTOResponseSWRelation();
        ArrayList<StrategyWorkflowRelation> relations = strategicP.getStrategyWorkflowIds();
        for (StrategyWorkflowRelation swr : relations) {
            Strategy strategy = swr.getStrategy();
            WorkflowData metaWorkflow = null;
            if (swr.getWorkflow() != null)
                metaWorkflow = swr.getWorkflow();
            dtoResponse.push(strategy, metaWorkflow);
        }
        ResponseEntity<DTOResponseSWRelation> responseEntity = new ResponseEntity<DTOResponseSWRelation>(dtoResponse,
                HttpStatus.OK);
        return responseEntity;

    }

    @Override
    public ResponseEntity<DTOResponseMetaWorkflow> getStrategiesOfStrategicPlan(String id) {
        StrategicPlan strategicPlan = strategicPlanRepository.findOne(id);

        DTOResponseMetaWorkflow dtoResponse = new DTOResponseMetaWorkflow();

        List<StrategyWorkflowRelation> strategyWorkflowRelationList = strategicPlan.getStrategyWorkflowIds();
        List<Strategy> strategiesList = new ArrayList<Strategy>();
        for (StrategyWorkflowRelation strategyWorkflowRelation : strategyWorkflowRelationList) {
            if (strategyWorkflowRelation.getStrategy() != null) {
                strategiesList.add(strategyWorkflowRelation.getStrategy());
            }

        }

        dtoResponse.setStrategies(strategiesList);
        ResponseEntity<DTOResponseMetaWorkflow> responseEntity = new ResponseEntity<DTOResponseMetaWorkflow>(
                dtoResponse, HttpStatus.OK);
        return responseEntity;
    }

    @Override
    public ResponseEntity<DTOResponseMetaWorkflow> getMetaworkflowOfStrategicPlan(String id) {
        StrategicPlan strategicPlan = strategicPlanRepository.findOne(id);
        DTOResponseMetaWorkflow dtoResponse = new DTOResponseMetaWorkflow();

        List<StrategyWorkflowRelation> strategyWorkflowRelationList = strategicPlan.getStrategyWorkflowIds();

        List<WorkflowData> metaWorkflowList = new ArrayList<WorkflowData>();
        for (StrategyWorkflowRelation strategyWorkflowRelation : strategyWorkflowRelationList) {

            if (strategyWorkflowRelation.getWorkflow() != null) {
                metaWorkflowList.add(strategyWorkflowRelation.getWorkflow());
            }
        }

        dtoResponse.setMetaWorkflowList(metaWorkflowList);
        ResponseEntity<DTOResponseMetaWorkflow> responseEntity = new ResponseEntity<DTOResponseMetaWorkflow>(
                dtoResponse, HttpStatus.OK);

        return responseEntity;
    }

    @Override
    public ResponseEntity<DTOResponseMetaWorkflow> getStrategyWorkflowRelationOfStrategicPlan(String id) {
        StrategicPlan strategicPlan = strategicPlanRepository.findOne(id);
        DTOResponseMetaWorkflow dtoResponse = new DTOResponseMetaWorkflow();
        List<StrategyWorkflowRelation> strategyWorkflowRelationList = strategicPlan.getStrategyWorkflowIds();
        dtoResponse.setStrategyWorkflowRelationList(strategyWorkflowRelationList);

        ResponseEntity<DTOResponseMetaWorkflow> responseEntity = new ResponseEntity<DTOResponseMetaWorkflow>(
                dtoResponse, HttpStatus.OK);
        return responseEntity;
    }

    @Override
    public ResponseEntity<DTOResponseMetaWorkflow> getWorkflowBusinessIdOfStrategicPlan(String id) {

        return null;
    }

    @Override
    public ResponseEntity<DTOResponseSWRelation> setMetaWorkflow(String strategicPlanId, String strategyId,
                                                                 String name) throws ProcessDefinitionNotFoundException, IllegalCharacterRequestException, BusinessWorkflowNotCreatedException, JsonRequestException, ActivitiEntityAlreadyExistsException, MetaWorkflowNotStartedException, JsonRequestConflictException, MetaWorkflowNotDeployedException, ModelXmlNotFoundException, BusRequestException, BusException, IllegalSaveWorkflowRequestBodyException, IOException {
        ResponseEntity<DTOResponseSWRelation> responseEntity;
        StrategicPlan strategicP = strategicPlanRepository.findOne(strategicPlanId);
        Strategy strategy = strategyRepository.findOne(strategyId);
        // se ho trovato strategia e strategic plan avvio il metaworkflow poi lo
        // associo alla strategia
        // TODO fare il check se l'unità organizzativa è la stessa
        //// _______________________________________________________________________

//        JSONObject obj = new JSONObject();
//        obj.put("name", name);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
////		Map<String, String> params = new HashMap<String, String>();
////		params.put("name", name);
//        RestTemplate template = new RestTemplate();
//        String URL = hostSettings.getGqm31_baseurl() + "/workflows/create";
//        HttpEntity<String> request = new HttpEntity<>(obj.toString(), headers);


        ResponseEntity response = workflowServiceImplementation.createWorkflow(name);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            JSONObject jsnobject;
            String metaId = "", businessModelId = "";
            try {
                jsnobject = new JSONObject(response.getBody().toString());

                metaId = jsnobject.getString("metaWorkflowProcessInstanceId");
                businessModelId = jsnobject.getString("businessWorkflowModelId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // jsnobject.getString(key);
            // trattare poi la risposta
            // ____________________________________________________________________________

            WorkflowData wd = wdRepository.findByBusinessWorkflowModelId(businessModelId).get(0);
            strategicP.setMetaId(strategy, wd);
            strategicPlanRepository.save(strategicP);
            DTOResponseSWRelation dtoResponse = new DTOResponseSWRelation();
            dtoResponse.push(strategy, wd);
            responseEntity = new ResponseEntity<DTOResponseSWRelation>(dtoResponse, HttpStatus.OK);
        } else {
            DTOResponseSWRelation dtoResponse = new DTOResponseSWRelation();
            responseEntity = new ResponseEntity<DTOResponseSWRelation>(dtoResponse, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;

    }

    @Override
    public ResponseEntity<DTOResponseStrategicPlan> getStrategyToWorkflow(String id) {
        return null;
    }

    @Override
    public ResponseEntity<DTOResponseStrategicPlan> updateStrategicPlan(ArrayList<String> strategyId, String name,
                                                                        String description, ArrayList<Attribute> attributes, float version, String release) {
        return null;
    }

    public ResponseEntity<DTOResponseSWRelation> getStrategyWorkflowData(String strategicPlanId, String strategyId) {
        DTOResponseSWRelation dtoResponse = new DTOResponseSWRelation();
        ResponseEntity<DTOResponseSWRelation> response = null;
        StrategicPlan strategicPlan = strategicPlanRepository.findOne(strategicPlanId);
        if (strategicPlan != null) {
            Strategy strategy = strategyRepository.findOne(strategyId);
            if (strategy != null) {
                WorkflowData workflowData = strategicPlan.getSWFromStrategy(strategy);
                if (workflowData != null) {
                    dtoResponse.setStrategy(strategy);
                    dtoResponse.setWorkflow(workflowData);
                    response = new ResponseEntity<DTOResponseSWRelation>(dtoResponse, HttpStatus.OK);
                    return response;
                } else {
                    dtoResponse.setStrategy(strategy);
                    dtoResponse.setWorkflow(null);
                    dtoResponse.setError("Can't find workflowData associated with the selectionated strategy. Maybe it doesn't exist");
                    response = new ResponseEntity<DTOResponseSWRelation>(dtoResponse, HttpStatus.NOT_FOUND);
                    return response;
                }
            } else {
                dtoResponse.setError("Can't find strategy inside the strategic plan");
                response = new ResponseEntity<DTOResponseSWRelation>(dtoResponse, HttpStatus.NOT_FOUND);
                return response;
            }
        } else {
            dtoResponse.setError("Strategic Plan not fount");
            response = new ResponseEntity<DTOResponseSWRelation>(dtoResponse, HttpStatus.NOT_FOUND);
            return response;
        }
    }

    @Override
    public ResponseEntity<DTOResponseMetaWorkflow> getStrategyWithWorkflow(String strategicPlanId) {

        strategyService.updateStrategyF2();

        ArrayList<Strategy> result = new ArrayList<Strategy>();
        DTOResponseMetaWorkflow dtoResponseswRelation = new DTOResponseMetaWorkflow();
        ResponseEntity<DTOResponseMetaWorkflow> response = null;
        StrategicPlan strategicPlan = strategicPlanRepository.findOne(strategicPlanId);
        if (strategicPlan != null) {
            ArrayList<StrategyWorkflowRelation> swr = strategicPlan.getStrategyWorkflowIds();
            for (StrategyWorkflowRelation strategyWorkflowRelation : swr) {
                if (strategyWorkflowRelation.getWorkflow() != null) {
                    WorkflowData wd = strategyWorkflowRelation.getWorkflow();
                    result.add(strategyWorkflowRelation.getStrategy());
                }
            }
            dtoResponseswRelation.setStrategies(result);
            response = new ResponseEntity<DTOResponseMetaWorkflow>(dtoResponseswRelation, HttpStatus.OK);
            return response;
        } else {
            dtoResponseswRelation.setError("strategicPlan not found");
            response = new ResponseEntity<DTOResponseMetaWorkflow>(dtoResponseswRelation, HttpStatus.NOT_FOUND);
            return response;
        }


    }

    @Override
    public ResponseEntity<DTOResponseMetaWorkflow> getStrategiesWithOrganizationalUnitOfStrategicPlan(String id) {
        StrategicPlan strategicPlan = strategicPlanRepository.findOne(id);
        DTOResponseMetaWorkflow dtoResponse = new DTOResponseMetaWorkflow();
        List<StrategyWorkflowRelation> strategyWorkflowRelationList = strategicPlan.getStrategyWorkflowIds();

        String orgUnit = strategicPlan.getOrganizationalUnit();

        List<Strategy> strategies = strategyRepository.findAll();

		/*elimino dalla lista le strategie con unità organizzativa differente da quella dello strategic plan*/


        for (int i = 0; i < strategies.size(); i++) {
            if (strategies.get(i).getOrganizationalunit().equals(orgUnit)) {
            } else {
                strategies.remove(i);
                i--;
            }
        }

        for (int x = 0; x < strategies.size(); x++) {

            for (int j = 0; j < strategyWorkflowRelationList.size(); j++) {

                if (strategies.get(x).getId().equals(strategyWorkflowRelationList.get(j).getStrategy().getId())) {
                    strategies.remove(x);
                    x--;
                    break;
                }
            }
        }

        //strategia da costruire e inserire nella lista
        for (int y = 0; y < strategies.size(); y++) {
            StrategyWorkflowRelation strategyWorkflowRelation = new StrategyWorkflowRelation();
            strategyWorkflowRelation.set_id(null);
            strategyWorkflowRelation.setWorkflow(null);
            strategyWorkflowRelation.setStrategy(strategies.get(y));
            strategyWorkflowRelationList.add(strategyWorkflowRelation);

        }
        dtoResponse.setStrategyWorkflowRelationList(strategyWorkflowRelationList);
        ResponseEntity<DTOResponseMetaWorkflow> responseEntity = new ResponseEntity<DTOResponseMetaWorkflow>(
                dtoResponse, HttpStatus.OK);
        return responseEntity;
    }


    @Override
    public ResponseEntity<DTOResponseStrategicPlan> updateStrategiesOfStrategicPlan(String id,
                                                                                    ArrayList<StrategyWorkflowRelation> strategyToWorkflowId) {

        StrategicPlan p = null;

        p = strategicPlanRepository.findOne(id);

        p.setStrategyWorkflowIds(strategyToWorkflowId);

        strategicPlanRepository.save(p);
        DTOResponseStrategicPlan dtoResponse = new DTOResponseStrategicPlan();
        dtoResponse.setId(p.getId());
        dtoResponse.setStrategyToWorkflowId(p.getStrategyWorkflowIds());
        dtoResponse.setName(p.getName());
        dtoResponse.setDescription(p.getDescription());
        dtoResponse.setOrganizzationalUnit(p.getOrganizationalUnit());
        dtoResponse.setAttributes(p.getAttributes());
        ResponseEntity<DTOResponseStrategicPlan> responseEntity = new ResponseEntity<DTOResponseStrategicPlan>(
                dtoResponse, HttpStatus.OK);
        return responseEntity;
    }

}
