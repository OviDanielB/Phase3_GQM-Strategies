package it.uniroma2.isssr.gqm3.model.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.UUID;

public class DTOLevel3Request extends DTO {
		   
	    private static final long serialVersionUID = 1L;
	   
	    @JsonInclude(Include.NON_NULL)
	    private String tag;
	    @JsonInclude(Include.NON_NULL)
	    private String operation;
	    @JsonInclude(Include.NON_NULL)
	    private String phase;
	    @JsonInclude(Include.NON_NULL)
	    private String data;
	    @JsonInclude(Include.NON_NULL)
	    private UUID id;
	    @JsonInclude(Include.NON_NULL)
	    private String version;
	    @JsonInclude(Include.NON_NULL)
	    private String destinationAdress;
	 
	    public DTOLevel3Request() {
	        super();
	    }
	    public DTOLevel3Request(String tag, String operation, String phase, String destinationAdress, String data, UUID id,
	            String version) {
	        super();
	        this.tag = tag;
	        this.data = data;
	        this.id = id;
	        this.version = version;
	        this.phase=phase;
	        this.operation=operation;
	        this.destinationAdress=destinationAdress;
	    }
	   
	    public String getDestinationAdress() {
	        return destinationAdress;
	    }
	    public void setDestinationAdress(String destinationAdress) {
	        this.destinationAdress = destinationAdress;
	    }
	    public String getOperation() {
	        return operation;
	    }
	    public void setOperation(String operation) {
	        this.operation = operation;
	    }
	    public String getPhase() {
	        return phase;
	    }
	    public void setPhase(String phase) {
	        this.phase = phase;
	    }
	    public String getTag() {
	        return tag;
	    }
	    public void setTag(String tag) {
	        this.tag = tag;
	    }
	    public String getData() {
	        return data;
	    }
	    public void setData(String data) {
	        this.data = data;
	    }
	    public UUID getId() {
	        return id;
	    }
	    public void setId(UUID id) {
	        this.id = id;
	    }
	    public String getVersion() {
	        return version;
	    }
	    public void setVersion(String version) {
	        this.version = version;
	    }
	   
	}
