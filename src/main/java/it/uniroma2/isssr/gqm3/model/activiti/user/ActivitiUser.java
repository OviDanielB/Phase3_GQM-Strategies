package it.uniroma2.isssr.gqm3.model.activiti.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Title: ActivitiUser</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * <p>Class description:
 * 
 * Entity che rappresenta un oggetto User di Activiti,
 * necessario per poter trattare nella nostra applicazione
 * quello che per Activiti è uno User. 
 * Gli attributi di questa entity sono pensati per rispecchiare
 * i campi del Json restituito da Activiti Rest
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivitiUser {
	
	private String id;
	private String firstName;
	private String lastName;
	private String url;
	private String email;
	private String pictureUrl;
	
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}	