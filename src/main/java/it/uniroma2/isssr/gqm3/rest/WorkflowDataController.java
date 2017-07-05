package it.uniroma2.isssr.gqm3.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

public interface WorkflowDataController {

	/**
	 * This is the endpoint to obtain datas about locally stored workflows
	 * @param ended if true, the endpoint return worfklowdatas already terminated; if false, only worfklowdatas not terminated; if null return all workflowdatas
	 * @param response The HttpServletResponse
	 * @return
	 */
	@RequestMapping(value = "/workflowdatas", method = RequestMethod.GET)
	public ResponseEntity<?> getWorkflowDatas(@RequestParam Boolean ended, HttpServletResponse response);

}
