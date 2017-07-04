package it.uniroma2.isssr.gqm3.controller.implementation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.uniroma2.isssr.gqm3.controller.WorkflowDataController;
import it.uniroma2.isssr.gqm3.dto.ErrorResponse;
import it.uniroma2.isssr.gqm3.model.WorkflowData;
import it.uniroma2.isssr.gqm3.repository.WorkflowDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(value = "Workflow Data Controller", description = "Workflow Data API")
public class WorkflowDataControllerImplementation implements
		WorkflowDataController {

	@Autowired
	private WorkflowDataRepository workflowDataRepository;

	@RequestMapping(value = "/workflowdatas", method = RequestMethod.GET)
	@ApiOperation(value = "Get Workflow Datas", notes = "This endpoint retrieves all datas related to a workflow")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "See error code and message", response = ErrorResponse.class) })
	public ResponseEntity<List<WorkflowData>> getWorkflowDatas(
			@RequestParam(value = "ended", required = false) Boolean ended,
			HttpServletResponse response) {
		List<WorkflowData> workflowDatas;
		if (ended == null)
			workflowDatas = workflowDataRepository.findAll();
		else
			workflowDatas = workflowDataRepository.findByEnded(ended);
		return new ResponseEntity<List<WorkflowData>>(workflowDatas,
				HttpStatus.OK);
	}

}
