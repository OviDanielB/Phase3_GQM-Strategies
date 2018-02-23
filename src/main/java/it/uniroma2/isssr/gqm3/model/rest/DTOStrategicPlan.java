package it.uniroma2.isssr.gqm3.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import it.uniroma2.isssr.gqm3.model.Attribute;
import it.uniroma2.isssr.gqm3.model.StrategyWorkflowRelation;

import java.util.ArrayList;

/**
 * <p>Title: DTOStrategicPlan</p>
 * <p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: Dipartimento di Ingegneria Informatica, Università degli studi di Roma
 * Tor Vergata, progetto di ISSSR, gruppo 3: Fabio Alberto Coira,
 * Federico Di Domenicantonio, Daniele Capri, Giuseppe Chiapparo, Gabriele Belli,
 * Luca Della Gatta</p>
 * <p>
 * <p>Class description:
 * <p>
 * DTO relativa alla classe StrategicPlan. Quando ricevo il Json (ad es. nella POST)
 * in DTOStrategicPlan salverò i relativi campi
 *
 * @author Daniele Capri
 * @version 1.0
 */

public class DTOStrategicPlan extends DTO {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @JsonInclude(Include.NON_NULL)
    private String id;

    @JsonInclude(Include.NON_NULL)
    private ArrayList<String> strategyId;


    @JsonInclude(Include.NON_NULL)
    private ArrayList<StrategyWorkflowRelation> strategyWorkflowIds;

    /**
     * The name.
     */
    @JsonInclude(Include.NON_NULL)
    private String name;


    /**
     * The description.
     */
    @JsonInclude(Include.NON_NULL)
    private String description;

    @JsonInclude(Include.NON_NULL)
    private String organizationalunit;

    /**
     * The attributes.
     */
    @JsonInclude(Include.NON_NULL)
    private ArrayList<Attribute> attributes;

    /**
     * The version.
     */
    @JsonInclude(Include.NON_NULL)
    private float version;


    /**
     * The description.
     */
    @JsonInclude(Include.NON_NULL)
    private String release;


    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(ArrayList<String> strategyId) {
        this.strategyId = strategyId;
    }


    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizationalunit() {
        return organizationalunit;
    }

    public void setOrganizationalunit(String organizationalunit) {
        this.organizationalunit = organizationalunit;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public float getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(float version) {
        this.version = version;
    }

    /**
     * Gets the releaseDate.
     *
     * @return the releaseDate
     */
    public String getRelease() {
        return release;
    }

    /**
     * Sets the releaseDate.
     *
     * @param release the new release
     */
    public void setRelease(String release) {
        this.release = release;
    }


    /**
     * Gets the attributes.
     *
     * @return the attributes
     */
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Sets the attributes.
     *
     * @param attributes the new attributes
     */
    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }


    public ArrayList<StrategyWorkflowRelation> getStrategyWorkflowIds() {
        return strategyWorkflowIds;
    }

    public void setStrategyWorkflowIds(ArrayList<StrategyWorkflowRelation> strategyWorkflowIds) {
        this.strategyWorkflowIds = strategyWorkflowIds;
    }

}
