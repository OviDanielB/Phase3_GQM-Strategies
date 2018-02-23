package it.uniroma2.isssr.gqm3.model.activiti.process;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author luca
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class ActivitiProcessInvolvedPeople {

	private String url;
	private String user;
	private String group;
	private String type;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
