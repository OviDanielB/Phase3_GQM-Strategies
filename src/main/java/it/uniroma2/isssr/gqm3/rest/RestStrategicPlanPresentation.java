package it.uniroma2.isssr.gqm3.rest;

import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.service.StrategicPlanService;
import it.uniroma2.isssr.gqm3.model.rest.DTOMetaWorkflow;
import it.uniroma2.isssr.gqm3.model.rest.DTOStrategicPlan;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseMetaWorkflow;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseSWRelation;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategicPlan;
import it.uniroma2.isssr.integrazione.BusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/strategicPlan/")
public class RestStrategicPlanPresentation {

    /**
     * The strategic plan service.
     */
    @Autowired
    StrategicPlanService strategicPlanService;

    /**
     * Creates the strategic plan.
     *
     * @param dtoStrategicPlan the dto strategic plan
     * @return the response entity
     */
    @RequestMapping(value = "/createStrategicPlan", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseStrategicPlan> createStrategicPlan(@RequestBody DTOStrategicPlan dtoStrategicPlan) {

        return strategicPlanService.createStrategicPlan(dtoStrategicPlan.getStrategyId(), dtoStrategicPlan.getName(), dtoStrategicPlan.getDescription(), dtoStrategicPlan.getVersion(), dtoStrategicPlan.getRelease());

    }

    /**
     * Gets the strategic plans.
     *
     * @return the strategic plans
     */
    @RequestMapping(value = "/getStrategicPlans", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseStrategicPlan> getStrategicPlans(/*@RequestParam(value = "role", required=false) String role*/) {

//			switch (role) {
//			case "Workflow Developer":
//				break;
//			case "Validator":
//				break;
//			case "Execution Manager":
//				break;
//
//			default:
//				return strategicPlanService.getStrategicPlans();
//			}
        return strategicPlanService.getStrategicPlans();

    }

    /**
     * Gets the strategic plan.
     *
     * @param id the id
     * @return the strategic plan
     */
    @RequestMapping(value = "/getStrategicPlan", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseStrategicPlan> getStrategicPlan(@RequestParam("id") String id) {

        return strategicPlanService.getStrategicPlan(id);

    }
    /*
    @RequestMapping(value = "/createAttribute", method = RequestMethod.POST)
	public ResponseEntity<DTOResponseAttribute> createAttribute(@RequestBody DTOAttribute dtoAttribute) {

		return strategicPlanService.createAttribute(dtoAttribute.getName(), dtoAttribute.getType() ,dtoAttribute.getValue());
		
	}
	*/

    /**
     * Update strategic plan.
     *
     * @param dtoStrategicPlan the dto strategic plan
     * @return the response entity
     */
    @RequestMapping(value = "/updateStrategicPlan", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseStrategicPlan> updateStrategicPlan(@RequestBody DTOStrategicPlan dtoStrategicPlan) {

        return strategicPlanService.updateStrategicPlan(dtoStrategicPlan.getId(), dtoStrategicPlan.getName(), dtoStrategicPlan.getDescription(), dtoStrategicPlan.getAttributes(), dtoStrategicPlan.getVersion(), dtoStrategicPlan.getRelease());

    }

    @RequestMapping(value = "/deleteStrategicPlan", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseStrategicPlan> deleteStrategicPlan(@RequestParam("id") String id) {

        return strategicPlanService.deleteStrategicPlan(id);

    }

    /**
     * Gets the strategic plan.
     *
     * @param id the id
     * @return the strategic plan
     */
    @RequestMapping(value = "/getMetaWorkflows", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseSWRelation> getStrategicMetaWorkflows(@RequestParam("id") String id) {

        return strategicPlanService.getMetaWorkflows(id);

    }


    /**
     * Gets the strategic plan.
     *
     * @return the strategic plan
     */
    @RequestMapping(value = "/setMetaWorkflow", method = RequestMethod.POST)
    public ResponseEntity<DTOResponseSWRelation> setMetaWorkflows(@RequestBody DTOMetaWorkflow dtoMetaWorkflow) throws IllegalCharacterRequestException, BusinessWorkflowNotCreatedException, ProcessDefinitionNotFoundException, JsonRequestException, MetaWorkflowNotStartedException, JsonRequestConflictException, MetaWorkflowNotDeployedException, ActivitiEntityAlreadyExistsException, ModelXmlNotFoundException, BusRequestException, BusException, IllegalSaveWorkflowRequestBodyException, IOException {

        return strategicPlanService.setMetaWorkflow(dtoMetaWorkflow.getStrategcPlanId(), dtoMetaWorkflow.getStrategyId(), dtoMetaWorkflow.getName(), dtoMetaWorkflow.getUpdatedStrategy());

    }

    @RequestMapping(value = "/getStrategiesOfStrategicPlan", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseMetaWorkflow> getStrategiesOgStrategicPlan(@RequestParam("id") String id) {
        return strategicPlanService.getStrategiesOfStrategicPlan(id);
    }

    @RequestMapping(value = "/getMetaworkflowOfStrategicPlan", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseMetaWorkflow> getMetaworkflowOfStrategicPlan(@RequestParam("id") String id) {
        return strategicPlanService.getMetaworkflowOfStrategicPlan(id);
    }

    @RequestMapping(value = "/getStrategyWorkflowRelationOfStrategicPlan", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseMetaWorkflow> getStrategyWorkflowRelationOfStrategicPlan(@RequestParam("id") String id) {
        return strategicPlanService.getStrategyWorkflowRelationOfStrategicPlan(id);
    }


    @RequestMapping(value = "/getStrategyWorkflowData", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseSWRelation> getStrategicPlan(@RequestParam("strategicPlanId") String strategicPlanId, @RequestParam("strategyId") String strategyId) {

        return strategicPlanService.getStrategyWorkflowData(strategicPlanId, strategyId);

    }

    @RequestMapping(value = "/getStrategyWithWorkflow", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseMetaWorkflow> getStrategyWithWorkflow(@RequestParam("strategicPlanId") String strategicPlanId) {

        return strategicPlanService.getStrategyWithWorkflow(strategicPlanId);

    }

    @RequestMapping(value = "/getStrategiesWithOrganizationalUnitOfStrategicPlan", method = RequestMethod.GET)
    public ResponseEntity<DTOResponseMetaWorkflow> getStrategiesWithOrganizationalUnitOfStrategicPlan(@RequestParam("id") String id) {
        return strategicPlanService.getStrategiesWithOrganizationalUnitOfStrategicPlan(id);
    }

	

	@RequestMapping(value = "/updateStrategiesOfStrategicPlan", method = RequestMethod.POST)
	public ResponseEntity<DTOResponseStrategicPlan> updateStrategiesOfStrategicPlan(@RequestBody DTOStrategicPlan dtoStrategicPlan) {
		return strategicPlanService.updateStrategiesOfStrategicPlan(dtoStrategicPlan.getId(), dtoStrategicPlan.getStrategyWorkflowIds());
	}

}
