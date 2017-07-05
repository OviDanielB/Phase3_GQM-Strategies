package it.uniroma2.isssr.gqm3.hermes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.uniroma2.isssr.HostSettings;
import it.uniroma2.isssr.gqm3.model.CollectedData;
import it.uniroma2.isssr.gqm3.model.MeasureTask;
import it.uniroma2.isssr.gqm3.model.Strategy;
import it.uniroma2.isssr.gqm3.model.rest.DTOPhase56;
import it.uniroma2.isssr.gqm3.model.rest.DTOStrategyFrom1;
import it.uniroma2.isssr.gqm3.model.rest.response.DTOResponseStrategy;
import it.uniroma2.isssr.gqm3.repository.StrategyRepository;
import it.uniroma2.isssr.integrazione.BusException;
import it.uniroma2.isssr.integrazione.BusMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: Bus2Fase32Implementation
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: Dipartimento di Ingegneria Informatica, Università degli studi di
 * Roma Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira, Federico
 * Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli, Luca
 * Della Gatta
 * </p>
 * 
 * <p>
 * Class description: Classe in cui sono implementati i metodi "fittizi" del
 * bus, cioè ci serve per avere un punto di elaborazione per simulare il bus...
 * Speriamo non per sempre.. ahah
 * 
 * Poiché per ora è necessario comunque accedere ai dati salvati sul Mongo
 * locale per richiamare le Strategie quello che si fa è scrivere questo metodo
 * provvisoriamente
 * </p>
 * 
 * @author Fabio Alberto Coira
 * @version 1.0
 *
 */

@Component
@ComponentScan(basePackages ={"it.uniroma2.gqm3141.repository"})

public class Bus2fase32Implementation implements Bus2fase32{

	/** The strategy repository. */
	@Autowired
	StrategyRepository strategyRepository;
	@Autowired
    MongoTemplate mongoTemplate;
	@Autowired
	HostSettings hostSettings;
	
	
		
			
			public ResponseEntity<DTOResponseStrategy> getStrategies(){
				List<Strategy> strategies = strategyRepository.findAll();
				DTOResponseStrategy dtoRS= new DTOResponseStrategy();
				dtoRS.setStrategies(strategies);
				return new ResponseEntity<DTOResponseStrategy>(HttpStatus.OK);
				
			}
			
	
			public List<Strategy> getStrategiesList(){
				
				List<Strategy> strategies = strategyRepository.findAll();
				return strategies;
			}
			
			public String saveValitatedDataOnBus(String taskId ){
				String processInstanceId="",workflowData="", metric="";
				ArrayList<String> data= new ArrayList<String>();
				Query query = new Query();
				query.addCriteria(Criteria.where("taskId").is(taskId));
				query.addCriteria(Criteria.where("validated").is(true));
				List<CollectedData> collectedData = mongoTemplate.find(query,CollectedData.class);
				if(collectedData.isEmpty())
					return "can't find collected data";
					
				processInstanceId= collectedData.get(0).getWorkflowData().getBusinessWorkflowProcessInstanceId();
				if(processInstanceId==null)
					return "processId is corrupted";
				workflowData = collectedData.get(0).getWorkflowData().getBusinessWorkflowModelId();
				if(workflowData==null)
					return "can't find data regarding the choosen workflow ";
				query = new Query();
				query.addCriteria(Criteria.where("taskId").is(taskId));
				List<MeasureTask> mt = mongoTemplate.find(query,MeasureTask.class);
				if(!mt.isEmpty()){
					metric=mt.get(0).getMetric().getId();
				}
				else{
					return "can't find mesure task";
				}
				for (CollectedData cd : collectedData) {
					data.add(cd.getValue());
				}
				DTOPhase56 dtoPhase56= new DTOPhase56();
				dtoPhase56.setBusinessWorkFlowInstanceId(processInstanceId);
				dtoPhase56.setData(new JSONArray(data.toArray(new String[0])).toString());
				dtoPhase56.setDataId(taskId);
				dtoPhase56.setMetricRef(metric);
				dtoPhase56.setStrategyRef(workflowData);
				

				
				 try {

				JSONObject obj = new JSONObject();
				obj.put("objIdLocalToPhase",dtoPhase56.getDataId());
				obj.put("typeObj","validateData");
				obj.put("instance", dtoPhase56.getDataId() );
				obj.put("tags", "[]" /*"[{\"key\": \"TaskId\", \"value\": \""+dtoPhase56.getDataId()+"\"}]"*/);
				JSONObject dataJSON = new JSONObject(dtoPhase56);
				
				obj.put("payload", dataJSON.toString());
				String s=obj.toString();

				        BusMessage message= new BusMessage(BusMessage.OPERATION_CREATE,"Phase4",obj.toString());
				        String response = message.send(hostSettings.getBusUri());
				        System.out.println(response);
				        String err;
				        try{
				        err= new JSONObject(response).getString("err");}
				        catch(JSONException e){
				        	//il messaggio non ha dato errori
				        	return response;
				        }
				        if(err.equals("ERR4")){
				        	//"The object seems to be already created" provo la update
				        	message= new BusMessage(BusMessage.OPERATION_UPDATE,"Phase4",obj.toString());
					        response = message.send(hostSettings.getBusUri());
					        System.out.println(response);
				        	
				        }
				        return response;
				    } catch (IOException e) {
				        e.printStackTrace();
				        return "It seems bus is not longer avaiable";
				    } catch (BusException e) {
				        e.printStackTrace();
				        return "Error on bus message creation";
				    } catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "Error while creating a json object";
					}
				
				}
				
			
	
	/**
	 * Descrizione del metodo
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @throws
	 */

	public ArrayList<DTOStrategyFrom1> getStrategiesF1() {
		ArrayList<DTOStrategyFrom1> ls= new ArrayList<DTOStrategyFrom1>();
		String response="", organizationalUnitId="",organizationalUnitName="", version="",id="";

		DTOStrategyFrom1 ob=null;
		org.json.JSONArray jsonResponse=null;
		JSONObject jsonobj = null;
		try {
		JSONObject jsonRead = new JSONObject();
		jsonRead.put("objIdLocalToPhase", "");
		jsonRead.put("typeObj", "base64-TerminalStrategy");
		jsonRead.put("instance", "");
		jsonRead.put("busVersion", "");
		jsonRead.put("tags", "[]");
		BusMessage busMessage = new BusMessage(BusMessage.OPERATION_READ, "phase1", jsonRead.toString());
		response = busMessage.send(hostSettings.getBusUri());
		jsonResponse=new org.json.JSONArray(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (BusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		ObjectMapper obM=new ObjectMapper();
		obM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if(jsonResponse!=null){
		for (int i = 0; i < jsonResponse.length(); i++) {
			try {
			jsonobj= jsonResponse.getJSONObject(i);
			id=jsonobj.getString("instance");
			version = jsonobj.getString("busVersion");
			JSONArray tags = jsonobj.getJSONArray("tags");
			for (int j = 0; j < tags.length(); j++) {
				JSONObject singleTag= tags.getJSONObject(j);
				if(singleTag.getString("key").equals("OrgUnitId"))
				{
					organizationalUnitId = singleTag.getString("value");
				}else
				{
					if(singleTag.getString("key").equals("OrgUnitName")){
						organizationalUnitName = singleTag.getString("value");
					}
				}
				
			}
			String paylodas=jsonobj.getString("payload");
//			paylodas= new String(Base64.getDecoder().decode(paylodas));
			jsonobj= new JSONObject(paylodas);
			ob = obM.readValue(jsonobj.toString(), DTOStrategyFrom1.class);
			ob.setOrganizationalUnitId(organizationalUnitId);
			ob.setOrganizationalUnitName(organizationalUnitName);
			ob.setVersion(Integer.parseInt(version));
			ls.add(ob);
			}  catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			
		}

		return ls;
	}
		else{
			return null;
		}
	}
	

	@Override
	public void saveOnBus(String taskId, String processInstanceId) {
		// TODO Auto-generated method stub
		
	}
}
