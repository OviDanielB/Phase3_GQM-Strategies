package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.TaskVariable;

import java.util.List;

@JsonPropertyOrder({
        "processDefinitionId",
        "taskToComplete",
        "variables"
})
public class PostCompleteTask {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("processDefinitionId")
    private String processDefinitionId;
    @JsonProperty("taskToComplete")
    private String taskToComplete;
    @JsonProperty("variables")
    private List<TaskVariable> variables;

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getTaskToComplete() {
        return taskToComplete;
    }

    public void setTaskToComplete(String taskToComplete) {
        this.taskToComplete = taskToComplete;
    }

    public List<TaskVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<TaskVariable> variables) {
        this.variables = variables;
    }
}
