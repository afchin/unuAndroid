package unu.rest;

import org.json.JSONObject;

/**
 * @author Alexandre Boulgakov
 */
public class Friend {
	private JSONObject json;
	
	/** User name */
	public String getUserName() {
		return json.optString("username");
	}
	
	/** Email */
	public String getEmail() {
		return json.optString("email");
	}
	
	public Friend(JSONObject json) {
		this.json = json;
	}
}
