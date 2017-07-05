/*
 * @autor Daniele Capri
 */
package it.uniroma2.isssr.gqm3.repository;

import it.uniroma2.isssr.gqm3.model.MetaWorkflow;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
* <p>Title: MetaWorklowRepository</p>
*	
* <p>Copyright: Copyright (c) 2016</p>
* <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
* Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
* Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
* Luca Della Gatta</p> 
* 
* <p>Class description:
* 
* Interfaccia per il salvataggio in MongoDb dei metaworkflow
* 
* 
* @author Fabio Alberto Coira
* @version 1.0
*
*/
public interface MetaWorkflowRepository extends MongoRepository<MetaWorkflow, String> {


}