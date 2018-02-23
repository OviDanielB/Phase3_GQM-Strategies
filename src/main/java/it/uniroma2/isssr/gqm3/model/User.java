package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * This class represents a User taken from the bus
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String username;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String surname;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date dob;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String gender;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String pic;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String role;

	public User() {
	}

	/*
	 * public Login(String username, String password, String role){
	 * this.username = username; this.password = password; this.role = role;
	 * 
	 * }
	 */

	public User(String username, String password, String role, String name, String surname, Date dob, String gender,
			String pic) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.gender = gender;
		this.pic = pic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJSONObject(User l) throws IllegalArgumentException, IllegalAccessException {

		JSONObject jo = new JSONObject();

		for (Field f : User.class.getDeclaredFields())
			jo.put(f.getName(), f.get(l));

		return jo;
	}

	public static String listToJSONOBjectList(List<User> logList)
			throws IllegalArgumentException, IllegalAccessException {
		String s = "";
		for (User l : logList)
			s += l.toJSONObject(l).toString();
		return s;
	}

}
