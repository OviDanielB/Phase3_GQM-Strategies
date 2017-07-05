package it.uniroma2.isssr.gqm3.model.rest.response;

import it.uniroma2.isssr.gqm3.model.MetricTask;
import it.uniroma2.isssr.gqm3.model.rest.DTO;

import java.util.List;

public class DTOResponseMetricTask extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private List<MetricTask> metricTask;

	public List<MetricTask> getMetricTask() {
		return metricTask;
	}

	public void setMetricTask(List<MetricTask> metricTask) {
		this.metricTask = metricTask;
	}
	
	

}
