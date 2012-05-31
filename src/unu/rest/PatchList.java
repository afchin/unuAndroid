package unu.rest;

import org.json.JSONException;
import org.json.JSONArray;

/**
 * @author Alexandre Boulgakov
 */
public class PatchList {
	private JSONArray json;
	
	public int length() {
		return json.length();
	}
	
	public Patch get(int i) {
		try {
			return new Patch(json.getString(i));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PatchList(String json) {
		try {
			this.json = new JSONArray(json);
		} catch (JSONException e) {
			e.printStackTrace();
			this.json = new JSONArray();
		}
	}
}
