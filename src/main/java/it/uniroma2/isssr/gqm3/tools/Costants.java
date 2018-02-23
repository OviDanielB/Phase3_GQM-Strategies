package it.uniroma2.isssr.gqm3.tools;

/**
 * 
 *
 */
public class Costants {
	
	public static String STATE_ONE = "1";
	public static String STATE_TWO = "2";
	public static String STATE_THREE = "3";
	
	public static String OPERATION_EQUALS = "equals";
	
	public static String STRING_TYPE = "string";
	
	public static String VALIDATOR = "Validator";

		
	public static String MEASURE_TASK_DOCUMENTATION_1 = "The task ";
	public static String MEASURE_TASK_DOCUMENTATION_2 = " needs to be measured.<br>Click here: ";
	public static String MEASURE_TASK_DOCUMENTATION_3 = "<br>Insert measure value. <br> Click on 'Save' button.";
	
	public static String VALIDATION_TASK_DOCUMENTATION_1 = "The task ";
	public static String VALIDATION_TASK_DOCUMENTATION_2 = " needs to be validated.<br>Click here: ";
	public static String VALIDATION_TASK_DOCUMENTATION_3 = "<br>Insert validation. <br> Click on 'Save' button.";
	
	public static String REPEAT_MEASURE_TASK_DOCUMENTATION_1 = "For the task ";
	public static String REPEAT_MEASURE_TASK_DOCUMENTATION_2 = " is needed to repeat the measure.<br>Click here: ";
	public static String REPEAT_MEASURE_TASK_DOCUMENTATION_3 = "<br>Insert measure value. <br> Click on 'Save' button.";
	
	public static String DATA_COLLECTOR_PAGE = "/measurement-collection.html?taskId=";
	public static String DATA_COLLECTOR_PAGE_1 = "&name=";
	public static String DATA_COLLECTOR_PAGE_2 = "&runtimeTaskId=";
	
	public static String VALIDATOR_PAGE = "/execute-validation.html?runtimeTaskId=";
	public static String VALIDATOR_PAGE_1 = "&taskId=";

	public static String REPEAT_MEASURE_TASK_PAGE = "/measurement-repeat.html?taskId=";
	public static String REPEAT_MEASURE_TASK_PAGE_1 = "&name=";
	public static String REPEAT_MEASURE_TASK_PAGE_2 = "&errorMessage=";
	public static String REPEAT_MEASURE_TASK_PAGE_3 = "&collectedDataId=";

	public static String[] ISSUE_MESSAGES = {"validationIssue", "measurementIssue", "workflowIssue", "strategicIssue"};
	public static String VALIDATION_ISSUE_MESSAGE = "validationIssue";
	public static String MEASUREMENT_ISSUE_MESSAGE = "measurementIssue";
	public static String BUSINESS_WORKFLOW_ISSUE_MESSAGE = "workflowIssue";
	public static String STRATEGIC_PLAN_ISSUE_MESSAGE = "strategicIssue";
		
	public static String REALS = "reals";
	public static String INTEGERS = "integers";

}
