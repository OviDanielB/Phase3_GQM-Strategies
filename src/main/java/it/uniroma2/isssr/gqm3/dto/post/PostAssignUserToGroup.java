package it.uniroma2.isssr.gqm3.dto.post;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"userId"
})
public class PostAssignUserToGroup {

@JsonProperty("userId")
private String userId;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The userId
*/
@JsonProperty("userId")
public String getUserId() {
return userId;
}

/**
* 
* @param userId
* The userId
*/
@JsonProperty("userId")
public void setUserId(String userId) {
this.userId = userId;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}