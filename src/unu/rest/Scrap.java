package unu.rest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alexandre Boulgakov
 */
public class Scrap {
	private JSONObject json;
	
	public String getBody() {
		try {
			return json.getString("body");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public Scrap(String json) {
		try {
			this.json = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
			this.json = new JSONObject();
		}
	}
}
