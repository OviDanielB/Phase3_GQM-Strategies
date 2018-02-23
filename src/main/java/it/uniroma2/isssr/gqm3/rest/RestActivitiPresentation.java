package it.uniroma2.isssr.gqm3.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.Exception.*;
import it.uniroma2.isssr.gqm3.activiti.ActivitiInterationImplementation;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.ProcessInstance;
import it.uniroma2.isssr.gqm3.model.rest.response.activiti.DTOResponseActivitiProcess;
import it.uniroma2.isssr.gqm3.model.rest.response.activiti.DTOResponseActivitiTask;
import it.uniroma2.isssr.gqm3.service.ActivitiProcessService;
import it.uniroma2.isssr.gqm3.service.ActivitiTaskService;
import it.uniroma2.isssr.gqm3.service.ActivitiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: RestActivitiPresentation</p>
 * <p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>
 * <p>Class description:
 * <p>
 * Classe che si occupa di esporre i servizi Rest con cui l'applicazione comunica
 * con Activiti, attraverso Activiti-Rest, al mondo esterno.
 * Tra i vari servizi esposti, fondamentalmente si annoverano:
 * - quelli relativi agli utenti, di cui in ogni caso ci si è serviti di quelli dell'altra applicazione,
 * la gqm3141.
 * - quelli relativi agli User Tasks;
 * - quelli relativi ai processi;
 * - quelli relativi alle form-properties.
 * </p>
 *
 * @author Fabio Alberto Coira
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/activiti/")
public class RestActivitiPresentation {

    /**
     * Iniezione attraverso l'annotazione autowired della dipendenza da Service
     * che si occupa di offrire una implementazione dei servizi rest offerti
     * di gestione ed interazione con i task di Activiti.
     */
    @Autowired
    ActivitiTaskService activitiTaskService;

    /**
     * Iniezione attraverso l'annotazione autowired della dipendenza da Service
     * che si occupa di offrire una implementazione dei servizi rest offerti
     * di gestione ed interazione con i processi di Activiti.
     */
    @Autowired
    ActivitiProcessService activitiProcessService;

    @Autowired
    ActivitiInterationImplementation activitiInterationImplementation;

    /**
     * Iniezione attraverso l'annotazione autowired della dipendenza da Service
     * che si occupa di offrire una implementazione dei servizi rest offerti
     * di gestione ed interazione con gli utenti di Activiti.
     */
    @Autowired
    ActivitiUserService activitiUserService;

    @Autowired
    ActivitiProcessService processService;

    @Autowired
    HostSettings hostSettings;

    /********************************************************************************************/
    /**
     * SERVIZI REST DI ACTIVITI
     * <p>
     * <p>
     * Servizio Rest utilizzato per richiedere l'elenco di tutti gli Users
     * di Activiti-Explorer. E' stato il primo servizio offerto per testare una
     * interazione con Activiti, al tempo in cui non esisteva una web application
     * per la grafica condivisa, e si pensava di dover gestire autonomamente gli
     * utenti all'interno dell'applicazione. Tale operazione è stata offerta
     * dal gruppo gqm3141, ma per sviluppi di questa applicazione, potrebbe
     * esser utile mantenere tale servizio rest.
     *
     * @return Lista degli utenti presenti all'interno di Activiti
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() throws JsonParseException, JsonMappingException, IOException, ActivitiGetException {
        return activitiUserService.getUsers();
    }


    /**
     * Servizio Rest utilizzato per richiedere l'elenco di tutti gli User
     * Tasks presenti nel MySQL di Activiti-Explorer assegnati all'utente {username}
     * <p>
     * Sempre nella logica degli User Task, è un servizio pensato per un utente loggato
     * all'interno dell'applicazione, che vuole poter visualizzare unicamente i suoi
     * User Task per poterli completare.
     *
     * @param username : Lo username dell'utente di cui si vuole i task
     * @return : La lista degl User Task di cui in Activiti è assignee l'utente username
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    @RequestMapping(value = "/userTasks/{username}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getUserTasks(@PathVariable("username") String username) throws IOException, ActivitiGetException, JsonRequestException {
        return activitiTaskService.getUserTasks(username);

    }

    /**
     * Servizio Rest che restituisce tutte le informazioni relative allo User Task
     * di Activiti. E' un utilissimo servizio esposto, soprattutto per
     * conoscere il taskDefitionKey di quello User Task, di cui il taskId non
     * è altri che il numero di istanza a runtime (che quindi non è una informazione
     * permanente, ma precaria, che si perderà al completamento del task).
     * E' anche utile per conoscere l'assignee di quel task.
     *
     * @param taskId: id dell'istanza a runtime dello User Task di Activiti
     * @return Un body che contiene tutte le info relative a quel task.
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    @RequestMapping(value = "/userTaskByTaskId/{taskId}",
            method = RequestMethod.GET)
    public ResponseEntity<DTOResponseActivitiTask> getUserTaskByTaskId(
            @PathVariable("taskId") String taskId) throws IOException, ActivitiGetException, JsonRequestException {
        return activitiTaskService.getUserTaskByTaskId(taskId);

    }

    /**
     * Servizio Rest per richiedere la lista degli User Task assegnati ad un certo
     * CandidateGroup. Questo servizio rest è pensato per conoscere un task
     * di cui poter fare il claim nell'interfaccia comune, se magari quel task
     * non dovesse avere un assignee, ma se però, come si è pensato potesse
     * verificarsi, esso abbia un candidate group associato, in funzione del ruolo
     * di quell'utente di gqm. (E' qui si rende fortemente necessaria l'informazione
     * relativa al mapping degli utenti di gqm su quelli di Activiti, offerta
     * dal gruppo gqm3141)
     *
     * @param candidateGroup : ovvero un gruppo di Activiti a cui è assegnato uno User Task
     * @return Lista degli user tasks assegnati ad un certo candidateGroup
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws ActivitiGetException
     */
    @RequestMapping(value = "/userTasksByCandidateGroup/{candidateGroup}",
            method = RequestMethod.GET)
    public ResponseEntity<DTOResponseActivitiTask> userTasksByCandidateGroup(
            @PathVariable("candidateGroup") String candidateGroup) throws IOException, ActivitiGetException, JsonRequestException {
        return activitiTaskService.getUserTasksByCandidateGroup(candidateGroup);

    }

    /**
     * Servizio per richiedere lo stato attuale di un processo in esecuzione.
     * In questo servizio era di particolare interesse per un task executor
     * conoscere la posizione del task all'interno di un processo. In generale
     * esso restituisce lo stato di un processo di Activiti attraverso una immagine
     * png, quindi effettivamente ha una portata più ampia.
     *
     * @param id : id di runtime del processo di cui si vuole conoscere lo stato corrente
     * @return Una immagine in formato png dello stato del processo
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/processInstanceState/{id}",
            method = RequestMethod.GET)
    public HttpEntity<byte[]> getProcessInstanceState(@PathVariable("id") String id) throws IOException {
        return activitiProcessService.getProcessInstanceState(id);
    }


    /*****************************************************************************
     * SERVIZI CONCORDATI CON I GRUPPO GQM3141
     * La necessità è quella di sospendere uno User Task per quanto riguarda la
     * fase 4 di GQM+Strategies. Un Data Collector deve potersi inserire
     * su quel task se è un Task di misura. Tale servizio è stato concordato
     * in quanto un Task Executor, attore della fase 3.2/4.2 deve poter completare
     * uno User Task per mandare avanti l'esecuzione di un Workflow di Activiti,
     * ma se il Task è un MeasureTask allora deve poter settare delle variabili
     * di stato, così da segnalare all'altra fase l'ok per la gestione di quel tipo
     * di task.
     * Infine deve essere sempre compito di un utente della fase 3.2/4.2 di GQM+Strategies
     * il Validator, di poter, nella fase 4, recuperare le List<ActivitiFormVariableProperty> salvate
     * in una VariableActiviti indicizzate dal taskId di runtime, per poter completare
     * finalmente tale UserTask
     *
     *********************************************************************************/


    /**
     * Servizio di redirezione sulla pagina di Activiti-Explorer
     * per effettuare il login
     *
     * @param response
     * @param username
     * @param password
     * @throws IOException
     */
    @RequestMapping(value = "/activitiExplorerLogin/{username}/{password}", method = RequestMethod.GET)
    public void activitiExplorerLogin(HttpServletResponse response, @PathVariable("username") String username,
                                      @PathVariable("password") String password) throws IOException {
        response.sendRedirect(hostSettings.getActivitiExplorerConnectionUrl() + "/?username=" + username
                + "&password=" + password);
    }

    /**
     * Servizio di redirezione sulla pagina del Modeler di Activiti-Explorer
     * per editare il model di id {id}
     *
     * @param response
     * @param id
     * @throws IOException
     */
    @RequestMapping(value = "/activitiExplorerModeler/{id}", method = RequestMethod.GET)
    public void activitiExplorerModeler(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
        // TODO complete task start and go to POPULATE METAWORKFLOW

        response.sendRedirect(hostSettings.getActivitiExplorerConnectionUrl() + "/modeler.html?modelId=" + id);
    }

    /**
     * Restituisce i processi attivi
     *
     * @param username
     * @param password
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/processes/{username}/{password}",
            method = RequestMethod.GET)
    public ResponseEntity<DTOResponseActivitiProcess> getProcess(@PathVariable("username") String username,
                                                                 @PathVariable("password") String password) throws JsonParseException, JsonMappingException, IOException {
        return processService.getProcess(username, password);

    }

    @RequestMapping(value = "/instances",
            method = RequestMethod.GET)
    public ProcessInstance getProcessInstanceFromKey(@RequestParam("processDefinitionKey") String processDefinitionKey) throws JsonParseException, JsonMappingException, IOException, ActivitiGetException, JsonRequestException {
        return activitiInterationImplementation.getProcessInstanceFromKey(processDefinitionKey);

    }

    @RequestMapping(value = "/cleanAll", method = RequestMethod.GET)
    public ResponseEntity<?> cleanActiviti() throws ActivitiGetException, IOException, JsonRequestException {
        return activitiInterationImplementation.cleanAll();
    }

    /***********************************************************************************************/

}
