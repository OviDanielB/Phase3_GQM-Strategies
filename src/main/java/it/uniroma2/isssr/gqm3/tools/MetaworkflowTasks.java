package it.uniroma2.isssr.gqm3.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trillaura on 21/07/17.
 */
public class MetaworkflowTasks {

    public static final String NEW_CONSTRUCTION = "Start the construction of the Business Workflow";
    public static final String MOD_CONSTRUCTION = "Start the modification of the Business Workflow";
    public static final String NEW_POPULATE = "Populate the model of the Business Workflow";
    public static final String MOD_POPULATE = "Modify the model of the Business Workflow";
    public static final String DEPLOY = "Deploy the Business Workflow";
    public static final String NEW_VALIDATION_TASKS = "Plan validation tasks";
    public static final String MOD_VALIDATION_TASKS = "Modify validation tasks";
    public static final String NEW_MEASURE_TASKS = "Plan measure tasks";
    public static final String MOD_MEASURE_TASKS = "Modify measure tasks";
    public static final String START_PROCESS = "Start Business Process";
    public static final String EXPORT_WORKFLOW = "Export Workflow";

    public static List<String> getTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add(MetaworkflowTasks.NEW_CONSTRUCTION);
        tasks.add(MetaworkflowTasks.MOD_CONSTRUCTION);
        tasks.add(MetaworkflowTasks.NEW_POPULATE);
        tasks.add(MetaworkflowTasks.MOD_POPULATE);
        tasks.add(MetaworkflowTasks.DEPLOY);
        tasks.add(MetaworkflowTasks.NEW_VALIDATION_TASKS);
        tasks.add(MetaworkflowTasks.MOD_VALIDATION_TASKS);
        tasks.add(MetaworkflowTasks.NEW_MEASURE_TASKS);
        tasks.add(MetaworkflowTasks.MOD_MEASURE_TASKS);
        tasks.add(MetaworkflowTasks.START_PROCESS);
        tasks.add(MetaworkflowTasks.EXPORT_WORKFLOW);

        return tasks;
    }
}
