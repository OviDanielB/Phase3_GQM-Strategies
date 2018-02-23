package it.uniroma2.isssr.gqm3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.uniroma2.isssr.gqm3.model.CollectedData;
import it.uniroma2.isssr.gqm3.model.MeasureTask;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "measureTask", "collectedData" })
public class ResponseMeasurementRepeat {

	@JsonProperty("measureTask")
	private MeasureTask measureTask;
	@JsonProperty("collectedData")
	private CollectedData collectedData;

	public ResponseMeasurementRepeat() {
	}

	public ResponseMeasurementRepeat(MeasureTask measureTask, CollectedData collectedData) {
		super();
		this.measureTask = measureTask;
		this.collectedData = collectedData;
	}

	/**
	 * 
	 * @return The measureTask
	 */
	public MeasureTask getMeasureTask() {
		return measureTask;
	}

	/**
	 * 
	 * @param measureTask
	 *            The measureTask
	 */
	public void setMeasureTask(MeasureTask measureTask) {
		this.measureTask = measureTask;
	}

	/**
	 * 
	 * @return The collectedData
	 */
	public CollectedData getCollectedData() {
		return collectedData;
	}

	/**
	 * 
	 * @param collectedData
	 *            The collectedData
	 */
	public void setCollectedData(CollectedData collectedData) {
		this.collectedData = collectedData;
	}

}
