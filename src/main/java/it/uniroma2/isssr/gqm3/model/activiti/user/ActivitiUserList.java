package it.uniroma2.isssr.gqm3.model.activiti.user;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ActivitiUserList</p>
 *	
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p> 
 * <p>Class description:
 * 
 * Entity che non è altro che una lista di ActivitiUser
 * E' necessaria per trattare i risultati del Json
 * restituito da Activiti Rest
 * 
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */
public class ActivitiUserList {
	private List<ActivitiUser> activitiUserList = new ArrayList<ActivitiUser>();

	public List<ActivitiUser> getActivitiUserList() {
		return activitiUserList;
	}

	public void setActivitiUserList(List<ActivitiUser> activitiUserList) {
		this.activitiUserList = activitiUserList;
	}
	
	public void addActivitiUser(ActivitiUser user){
		activitiUserList.add(user);
	}
}