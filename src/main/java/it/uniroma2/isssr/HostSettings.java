package it.uniroma2.isssr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file necessary for setting following field (see also
 * application.properites):
 * <ul>
 * <li>back-end address, port, username and password</li>
 * <li>mongoDB endpoint</li>
 * <li>Activiti rest endpoint</li>
 * <li>bus endpoint</li>
 * </ul>
 */

@Configuration
public class HostSettings {

	@Value("${host.address}")
	private static String address;

	@Value("${host.port}")
	private static String port;

	@Value("${host.activiti.address}")
	public static String activitiAddress;

	@Value("${host.activiti.port}")
	private static String activitiPort;

	@Value("${host.activiti.username}")
	private static String activitiUsername;

	@Value("${host.activiti.password}")
	private static String activitiPassword;

	@Value("${host.activiti.modeler.uri}")
	private static String activitiModelerUri;

	@Value("${host.activiti.explorer.endpoint}")
	private static String activitiExplorerEndpoint;

	@Value("${host.activiti.rest.endpoint.models}")
	private static String activitiRestEndpointModels;

	@Value("${host.activiti.rest.endpoint.deployments}")
	private static String activitiRestEndpointDeployments;

	@Value("${host.activiti.rest.endpoint.processdefinitions}")
	private static String activitiRestEndpointProcessDefinitions;

	@Value("${host.activiti.rest.endpoint.processistances.model.suffix}")
	private static String activitiRestEndpointProcessDefinitionsModelSuffix;

	@Value("${host.activiti.rest.endpoint.processistances.image.suffix}")
	private static String activitiRestEndpointProcessDefinitionsImageSuffix;

	@Value("${host.activiti.rest.endpoint.processistances}")
	private static String activitiRestEndpointProcessInstances;

	@Value("${host.activiti.rest.endpoint.executions}")
	private static String activitiRestEndpointExecutions;

	@Value("${host.activiti.rest.endpoint.query.executions}")
	private static String activitiRestEndpointQueryExecutions;

	@Value("${host.activiti.rest.endpoint.tasks}")
	private static String activitiRestEndpointTasks;

	@Value("${host.activiti.rest.endpoint.tasks.variables.suffix}")
	private static String activitiRestEndpointTasksVariablesSuffix;

	@Value("${host.activiti.rest.endpoint.query.tasks}")
	private static String activitiRestEndpointQueryTasks;

	@Value("${host.activiti.rest.endpoint.history.historictaskinstances}")
	private static String activitiRestEndpointHistoryHistoricTaskInstances;

	@Value("${host.activiti.rest.endpoint.identity.groups}")
	private static String activitiRestEndpointIdentityGroups;

	@Value("${host.activiti.rest.endpoint.identity.users}")
	private static String activitiRestEndpointIdentityUsers;

	@Value("${host.activiti.rest.endpoint}")
	private static String activitiRestEndpoint;

	@Value("${host.activiti.task.variable.state}")
	private static String activitiTaskVariableState;

	@Value("${host.activiti.task.variable.responsible}")
	private static String activitiTaskVariableResponsible;

	@Value("${host.activiti.task.variable.assignee}")
	private static String activitiTaskVariableAssignee;

	@Value("${host.activiti.task.variable.errormessage}")
	private static String activitiTaskVariableErrormessage;

	@Value("${host.activiti.task.variable.idcollecteddata}")
	private static String activitiTaskVariableIdCollectedData;

	@Value("${host.metaworkflow.prefix}")
	private static String metaworkflowPrefix;

	@Value("${host.metaworkflow.suffix}")
	private static String metaworkflowSuffix;

	@Value("${host.metaworkflow.path}")
	private static String metaworkflowPath;

	@Value("${host.metaworkflow.processidentifiername}")
	private static String metaworkflowProcessIdentifierName;

	@Value("${host.metaworkflow.name}")
	private static String metaworkflowName;

	@Value("${host.debug}")
	private static boolean debug;

	@Value("${host.mongodb.username}")
	private static String mongodbUsername;

	@Value("${host.mongodb.password}")
	private static String mongodbPassword;

	@Value("${host.mongodb.database}")
	private static String mongodbDatabase;

	@Value("${host.mongodb.host}")
	private static String mongodbHost;

	@Value("${host.mongodb.port}")
	private static Integer mongodbPort;

	@Value("${host.webapp.endpoint.backend}")
	private static String webappBackendEndpoint;

	@Value("${host.webapp.endpoint.frontend}")
	private static String webappFrontendEndpoint;

	@Value("${host.webapp.endpoint.deploy}")
	private static String deployEndpoint;

	@Value("${host.webapp.endpoint.start}")
	private static String startEndpoint;

	@Value("${host.webapp.endpoint.export}")
	private static String exportEndpoint;

	@Value("${host.webapp.endpoint.measure-planning}")
	private static String measurePlanningEndpoint;

	@Value("${host.webapp.endpoint.validation-planning}")
	private static String validationPlanningEndpoint;

	@Value("${host.webapp.endpoint.workflowdata-plans-uri}")
	private static String workflowDataPlansUri;

	@Value("${host.bus}")
	private static Boolean bus;

	@Value("${host.bus.address}")
	private static String busAddress;

	@Value("${host.bus.port}")
	private static String busPort;

	@Value("${host.bus.endpoint}")
	private static String busEndpoint;

	@Value("${host.bus.phase.name}")
	private static String phaseName;

	@Value("${host.bus.phase.typeObject.user.create}")
	private static String userCreateTypeObject;
	
	@Value("${host.bus.phase.typeObject.user.delete}")
	private static String userDeleteTypeObject;

	public String getWebappBackendUri() {
		return getConnectionUrl() + webappBackendEndpoint;
	}

	public String getWebappFrontendUri() {
		return getConnectionUrl() + webappFrontendEndpoint;
	}

	public String getDeployEndpoint() {
		return deployEndpoint;
	}

	public String getStartEndpoint() {
		return startEndpoint;
	}

	public String getExportEndpoint() {
		return exportEndpoint;
	}

	public String getMeasurePlanningEndpoint() {
		return measurePlanningEndpoint;
	}

	public String getValidationPlanningEndpoint() {
		return validationPlanningEndpoint;
	}

	public boolean isDebug() {
		return debug;
	}

	public String getAddress() {
		return address;
	}

	public String getPort() {
		return port;
	}

	public String getConnectionUrl() {
		return address + ":" + port;
	}

	public String getActivitiRestConnectionUrl() {
		return activitiAddress + ":" + activitiPort + activitiRestEndpoint;
	}

	public String getActivitiExplorerConnectionUrl() {
		return activitiAddress + ":" + activitiPort + activitiExplorerEndpoint;
	}

	public String getActivitiExplorerEndpoint() {
		return activitiExplorerEndpoint;
	}

	public String getActivitiRestEndpoint() {
		return activitiRestEndpoint;
	}

	public String getActivitiRestEndpointModels() {
		return activitiRestEndpointModels;
	}

	public String getActivitiRestEndpointDeployments() {
		return activitiRestEndpointDeployments;
	}

	public String getActivitiRestEndpointProcessDefinitions() {
		return activitiRestEndpointProcessDefinitions;
	}

	public String getActivitiRestEndpointProcessInstances() {
		return activitiRestEndpointProcessInstances;
	}

	public String getActivitiRestEndpointQueryExecutions() {
		return activitiRestEndpointQueryExecutions;
	}

	public String getActivitiRestEndpointExecutions() {
		return activitiRestEndpointExecutions;
	}

	public String getMetaworkflowPrefix() {
		return metaworkflowPrefix;
	}

	public String getMetaworkflowSuffix() {
		return metaworkflowSuffix;
	}

	public String getMetaworkflowPath() {
		return metaworkflowPath;
	}

	public String getMetaworkflowProcessIdentifierName() {
		return metaworkflowProcessIdentifierName;
	}

	public String getMetaworkflowName() {
		return metaworkflowName;
	}

	public String getActivitiUsername() {
		return activitiUsername;
	}

	public String getActivitiPassword() {
		return activitiPassword;
	}

	public String getMongodbUsername() {
		return mongodbUsername;
	}

	public String getMongodbPassword() {
		return mongodbPassword;
	}

	public String getMongodbDatabase() {
		return mongodbDatabase;
	}

	public String getMongodbHost() {
		return mongodbHost;
	}

	public Integer getMongodbPort() {
		return mongodbPort;
	}

	public String getStrategyPlansUri() {
		return workflowDataPlansUri;
	}

	public String getActivitiModelerUri() {
		return activitiModelerUri;
	}

	public String getActivitiAddress() {
		return activitiAddress;
	}

	public String getActivitiPort() {
		return activitiPort;
	}

	public String getActivitiRestEndpointProcessDefinitionsModelSuffix() {
		return activitiRestEndpointProcessDefinitionsModelSuffix;
	}

	public String getActivitiRestEndpointProcessDefinitionsImageSuffix() {
		return activitiRestEndpointProcessDefinitionsImageSuffix;
	}

	public String getActivitiRestEndpointTasks() {
		return activitiRestEndpointTasks;
	}

	public String getActivitiRestEndpointTasksVariablesSuffix() {
		return activitiRestEndpointTasksVariablesSuffix;
	}

	public String getActivitiRestEndpointQueryTasks() {
		return activitiRestEndpointQueryTasks;
	}

	public String getActivitiRestEndpointHistoryHistoricTaskInstances() {
		return activitiRestEndpointHistoryHistoricTaskInstances;
	}

	public String getActivitiRestEndpointIdentityGroups() {
		return activitiRestEndpointIdentityGroups;
	}

	public String getActivitiRestEndpointIdentityUsers() {
		return activitiRestEndpointIdentityUsers;
	}

	public Boolean getBus() {
		return bus;
	}

	public String getActivitiTaskVariableState() {
		return activitiTaskVariableState;
	}

	public String getActivitiTaskVariableResponsible() {
		return activitiTaskVariableResponsible;
	}

	public String getActivitiTaskVariableAssignee() {
		return activitiTaskVariableAssignee;
	}

	public String getActivitiTaskVariableErrormessage() {
		return activitiTaskVariableErrormessage;
	}

	public String getActivitiTaskVariableIdCollectedData() {
		return activitiTaskVariableIdCollectedData;
	}

	public Boolean isBus() {
		return bus;
	}

	public String getBusAddress() {
		return busAddress;
	}

	public String getWebappBackendEndpoint() {
		return webappBackendEndpoint;
	}

	public String getWebappFrontendEndpoint() {
		return webappFrontendEndpoint;
	}

	public String getBusPort() {
		return busPort;
	}

	public String getBusEndpoint() {
		return busEndpoint;
	}

	public String getBusConnectionUrl() {
		return busAddress + ":" + busPort;
	}

	public String getBusUri() {
		return getBusConnectionUrl() + busEndpoint;
	}

	public String getWorkflowDataPlansUri() {
		return workflowDataPlansUri;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public String getUserCreateTypeObject() {
		return userCreateTypeObject;
	}
	
	public String getUserDeleteTypeObject() {
		return userDeleteTypeObject;
	}

	/** FROM 3242 **/

	@Value("${gqm32.gqm3141-ip}")
	private static String gqm31_ip;

	@Value("${gqm32.gqm3141-port}")
	private static String gqm31_port;

	@Value("${gqm32.gqm3141-endpoint}")
	private static String gqm31_endpoint;

	public String getGqm31_ip() {
		return gqm31_ip;
	}

	public void setGqm31_ip(String ip) {
		gqm31_ip = ip;
	}

	public String getGqm31_port() {
		return gqm31_port;
	}

	public void setGqm31_port(String port) {
		gqm31_port = port;
	}

	public String getGqm31_endpoint() {
		return gqm31_endpoint;
	}

	public void setGqm31_endpoint(String endpoint) {
		gqm31_endpoint = endpoint;
	}

	public String getGqm31_baseurl() {
		return "http://" + gqm31_ip + ':' + gqm31_port + gqm31_endpoint;
	}

}