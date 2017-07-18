package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.uniroma2.isssr.gqm3.dto.activiti.entity.TaskVariable;

import java.util.List;

/**
 * Created by trillaura on 17/07/17.
 */
@JsonPropertyOrder({
        "action",
        "variables"
})
public class PostActionTask {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("action")
    private String action;
    @JsonProperty("variables")
    private List<TaskVariable> variables;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<TaskVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<TaskVariable> variables) {
        this.variables = variables;
    }
}
