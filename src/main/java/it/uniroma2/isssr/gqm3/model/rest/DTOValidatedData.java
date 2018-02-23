package it.uniroma2.isssr.gqm3.model.rest;

import java.util.ArrayList;

public class DTOValidatedData extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dataId;
	
	private ArrayList<String> data;
	
	private String businessWorkFlowInstanceId;
	
	private String strategyRef;
	
	private String metricRef;
	
	public String getDataId() {
		return dataId;
	}
	
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
	public ArrayList<String> getData() {
		return data;
	}
	public void setData(ArrayList<String> data) {
		this.data = data;
	}
	
	public String getBusinessWorkFlowInstanceId() {
		return businessWorkFlowInstanceId;
	}
	
	public void setBusinessWorkFlowInstanceId(String businessWorkFlowInstanceId) {
		this.businessWorkFlowInstanceId = businessWorkFlowInstanceId;
	}
	public String getStrategyRef() {
		return strategyRef;
	}
	
	public void setStrategyRef(String strategyRef) {
		this.strategyRef = strategyRef;
	}
	
	public String getMetricRef() {
		return metricRef;
	}
	
	public void setMetricRef(String metricRef) {
		this.metricRef = metricRef;
	}	

}
