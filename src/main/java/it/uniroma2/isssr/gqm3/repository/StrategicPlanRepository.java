package it.uniroma2.isssr.gqm3.repository;

import it.uniroma2.isssr.gqm3.model.StrategicPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
* <p>Title: StrategicPlanRepository</p>
*	
* <p>Copyright: Copyright (c) 2016</p>
* <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
* Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
* Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
* Luca Della Gatta</p> 
* 
* <p>Class description:
* 
* The Interface StrategicPlanRepository consente 
* il salvataggio automatico degli strategic plans.
* 
* 
* @author Daniele Capri
* @version 1.0
*
*/

public interface StrategicPlanRepository extends MongoRepository<StrategicPlan, String> {

}
