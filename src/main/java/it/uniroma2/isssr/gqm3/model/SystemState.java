package it.uniroma2.isssr.gqm3.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents the actual status of the system. The status is 0 if the system is not ready, 1 if ready.
 *
 */
@Document
public class SystemState {
	@Id
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String _id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	int state;
	
	public SystemState(int state){
		this.state=state;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
