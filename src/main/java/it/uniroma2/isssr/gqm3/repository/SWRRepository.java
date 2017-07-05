package it.uniroma2.isssr.gqm3.repository;

import it.uniroma2.isssr.gqm3.model.StrategyWorkflowRelation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
* <p>Title: SWRRepository</p>
*	
* <p>Copyright: Copyright (c) 2016</p>
* <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
* Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, 
* Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
* Luca Della Gatta</p> 
* 
* <p>Class description:
* 
* In Spring si utilizzano i repositories per creare un livello di astrazione
* per interagire con la base di dati. Il cuore di questi repositories
* è la classe Repository che offre funzionalità CRUD, e che viene estesa
* dalle altre classi. Nel nostro caso usiamo MongoRepository, che offre dei metodi
* che vengono ereditati e utilizzati all'interno dell'applicazione.
* 
* public interface MongoRepository<T,ID extends Serializable>
* 		extends PagingAndSortingRepository<T,ID>, QueryByExampleExecutor<T>
* 
* List<T> 	findAll() 
*
* @author Daniele Capri
* @version 1.0
*
*/

public interface SWRRepository extends MongoRepository<StrategyWorkflowRelation, String> {

}
