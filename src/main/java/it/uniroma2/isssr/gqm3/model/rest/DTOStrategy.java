package it.uniroma2.isssr.gqm3.model.rest;

import it.uniroma2.isssr.gqm3.model.StrategyState;

import java.util.List;

public class DTOStrategy extends DTO {

    private static final long serialVersionUID = 1L;

    private long id;
    private String title;
    private String description;
    private List<String> descendentGoals;
    private List<Long> subStrategies;
    private long projectId;
    private long ascendantStrategyId;
    private long ascendantGoalId;
    private OrgUnitDTO scope;
    private StrategyState state;
    private long ascendentGoalId;


    public DTOStrategy() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getSubStrategies() {
        return subStrategies;
    }

    public void setSubStrategies(List<Long> subStrategies) {
        this.subStrategies = subStrategies;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public void setAscendantStrategyId(long ascendantStrategyId) {
        this.ascendantStrategyId = ascendantStrategyId;
    }

    public void setAscendantGoalId(long ascendantGoalId) {
        this.ascendantGoalId = ascendantGoalId;
    }

    public void setAscendentGoalId(long ascendentGoalId) {
        this.ascendentGoalId = ascendentGoalId;
    }

    public List<String> getDescendentGoals() {
        return descendentGoals;
    }

    public void setDescendentGoals(List<String> descendentGoals) {
        this.descendentGoals = descendentGoals;
    }


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getAscendantStrategyId() {
        return ascendantStrategyId;
    }

    public void setAscendantStrategyId(Long ascendantStrategyId) {
        this.ascendantStrategyId = ascendantStrategyId;
    }

    public Long getAscendantGoalId() {
        return ascendantGoalId;
    }

    public void setAscendantGoalId(Long ascendantGoalId) {
        this.ascendantGoalId = ascendantGoalId;
    }

    public OrgUnitDTO getScope() {
        return scope;
    }

    public void setScope(OrgUnitDTO scope) {
        this.scope = scope;
    }

    public StrategyState getState() {
        return state;
    }

    public void setState(StrategyState state) {
        this.state = state;
    }

    public Long getAscendentGoalId() {
        return ascendentGoalId;
    }

    public void setAscendentGoalId(Long ascendentGoalId) {
        this.ascendentGoalId = ascendentGoalId;
    }
}
